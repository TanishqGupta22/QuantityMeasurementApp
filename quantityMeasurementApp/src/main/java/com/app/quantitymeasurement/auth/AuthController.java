package com.app.quantitymeasurement.auth;

import com.app.quantitymeasurement.dto.GoogleTokenRequest;
import com.app.quantitymeasurement.entity.User;
import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.security.jwt.JwtUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin(originPatterns = {
        "http://localhost:*",
        "http://127.0.0.1:*"
})
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.security.oauth2.client.registration.google.client-id:}")
    private String googleClientId;

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        return jwtUtil.generateToken(user.getUsername());
    }

    @PostMapping("/google")
    public String googleLogin(@RequestBody GoogleTokenRequest req) throws Exception {

        if (req == null || req.getIdToken() == null || req.getIdToken().isEmpty()) {
            throw new RuntimeException("idToken is required");
        }

        if (googleClientId == null || googleClientId.isEmpty() || googleClientId.contains("YOUR_")) {
            throw new RuntimeException("Google client-id is not configured in application.properties");
        }

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance()
        ).setAudience(Collections.singletonList(googleClientId)).build();

        GoogleIdToken idToken = verifier.verify(req.getIdToken());
        if (idToken == null) {
            throw new RuntimeException("Invalid Google token");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        String username = email; // use email as username for your system

        userRepository.findByUsername(username).orElseGet(() -> {
            User u = new User();
            u.setUsername(username);
            u.setEmail(email);
            u.setPassword(""); // not used for Google accounts
            u.setRole("ROLE_USER");
            return userRepository.save(u);
        });

        return jwtUtil.generateToken(username);
    }
}