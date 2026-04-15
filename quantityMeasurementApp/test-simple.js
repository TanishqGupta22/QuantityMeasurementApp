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

// Test all endpoints
async function runTests() {
    console.log('Testing API endpoints...\n');

    try {
        // Test 1: Health check
        console.log('1. Testing health endpoint...');
        const health = await testEndpoint('GET', '/actuator/health');
        console.log(`   Status: ${health.statusCode}`);
        console.log(`   Body: ${health.body}\n`);

        // Test 2: Simple GET
        console.log('2. Testing GET /api/v1/quantities...');
        const get = await testEndpoint('GET', '/api/v1/quantities');
        console.log(`   Status: ${get.statusCode}`);
        console.log(`   Body: ${get.body}\n`);

        // Test 3: Convert operation
        console.log('3. Testing convert operation...');
        const convertData = {
            input1: {
                value: 1,
                unit: "METER"
            },
            input2: null,
            meta: {
                measurementType: "LengthUnit",
                operationType: "CONVERT",
                resultUnit: "CENTIMETERS"
            }
        };
        const convert = await testEndpoint('POST', '/api/v1/quantities/perform', convertData);
        console.log(`   Status: ${convert.statusCode}`);
        console.log(`   Body: ${convert.body}\n`);

        // Test 4: Login
        console.log('4. Testing login...');
        const loginData = {
            username: "testuser",
            password: "testpass"
        };
        const login = await testEndpoint('POST', '/auth/login', loginData);
        console.log(`   Status: ${login.statusCode}`);
        console.log(`   Body: ${login.body}\n`);

        // Test 5: Signup
        console.log('5. Testing signup...');
        const signupData = {
            username: "newuser" + Date.now(),
            password: "newpass123",
            email: "newuser@test.com"
        };
        const signup = await testEndpoint('POST', '/api/v1/quantities/auth/signup', signupData);
        console.log(`   Status: ${signup.statusCode}`);
        console.log(`   Body: ${signup.body}\n`);

    } catch (error) {
        console.error('Error:', error.message);
    }
}

runTests();
