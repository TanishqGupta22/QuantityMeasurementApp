const http = require('http');

// Test function to make HTTP requests
function testEndpoint(method, path, data = null) {
    return new Promise((resolve, reject) => {
        const options = {
            hostname: 'localhost',
            port: 8081,
            path: path,
            method: method,
            headers: {
                'Content-Type': 'application/json',
            }
        };

        const req = http.request(options, (res) => {
            let body = '';
            res.on('data', (chunk) => {
                body += chunk;
            });
            res.on('end', () => {
                resolve({
                    statusCode: res.statusCode,
                    body: body
                });
            });
        });

        req.on('error', (err) => {
            reject(err);
        });

        if (data) {
            req.write(JSON.stringify(data));
        }
        req.end();
    });
}

// Test authentication flow
async function testAuth() {
    console.log('Testing Authentication Flow...\n');

    try {
        // Step 1: Create a new user
        console.log('1. Creating a new user...');
        const username = 'testuser' + Date.now();
        const userData = {
            username: username,
            password: "testpass123",
            email: username + "@test.com"
        };
        
        const signup = await testEndpoint('POST', '/api/v1/quantities/auth/signup', userData);
        console.log(`   Status: ${signup.statusCode}`);
        console.log(`   Body: ${signup.body}\n`);

        // Step 2: Try to login with the created user
        console.log('2. Testing login with created user...');
        const loginData = {
            username: username,
            password: "testpass123"
        };
        
        const login = await testEndpoint('POST', '/auth/login', loginData);
        console.log(`   Status: ${login.statusCode}`);
        console.log(`   Body: ${login.body}\n`);

        // Step 3: Test with wrong password
        console.log('3. Testing login with wrong password...');
        const wrongLoginData = {
            username: username,
            password: "wrongpass"
        };
        
        const wrongLogin = await testEndpoint('POST', '/auth/login', wrongLoginData);
        console.log(`   Status: ${wrongLogin.statusCode}`);
        console.log(`   Body: ${wrongLogin.body}\n`);

        // Step 4: Test with non-existent user
        console.log('4. Testing login with non-existent user...');
        const nonExistentData = {
            username: "nonexistentuser",
            password: "testpass123"
        };
        
        const nonExistentLogin = await testEndpoint('POST', '/auth/login', nonExistentData);
        console.log(`   Status: ${nonExistentLogin.statusCode}`);
        console.log(`   Body: ${nonExistentLogin.body}\n`);

    } catch (error) {
        console.error('Error:', error.message);
    }
}

testAuth();
