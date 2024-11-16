#!/bin/bash

# Base URLs for the services
USER_SERVICE_URL="http://localhost:8080/user-service"
SHORTENER_SERVICE_URL="http://localhost:8080/url-shortener"

# Function to register a new user
register_user() {
    echo "Registering user..."
    response=$(curl -s -w "\n%{http_code}" -X POST "${USER_SERVICE_URL}/users/register" \
        -H "Content-Type: application/json" \
        -d '{
              "username": "testuser",
              "password": "password123",
              "email": "testuser@example.com"
            }')

    # Parse the response
    body=$(echo "$response" | head -n 1)
    status=$(echo "$response" | tail -n 1)

    echo "Response: $body"
    echo "Status Code: $status"
}

# Function to login the user and get the JWT token
get_jwt_token() {
    echo "Logging in to get JWT token..."
    response=$(curl -s -w "\n%{http_code}" -X POST "${USER_SERVICE_URL}/users/login" \
        -H "Content-Type: application/json" \
        -d '{
              "username": "testuser",
              "password": "password123"
            }')

    body=$(echo "$response" | head -n 1)
    status=$(echo "$response" | tail -n 1)

    echo "Response: $body"
    echo "Status Code: $status"

    # Extract token from the response
    JWT_TOKEN=$(echo "$body" | jq -r '.token')
    echo "JWT Token: $JWT_TOKEN"
}

# Function to shorten a URL
shorten_url() {
    echo "Shortening URL..."
    response=$(curl -s -w "\n%{http_code}" -X POST "${SHORTENER_SERVICE_URL}/api/shorten" \
        -H "Content-Type: application/json" \
        -H "Authorization: Bearer $JWT_TOKEN" \
        -d '{
              "longUrl": "https://www.google.com"
            }')

    body=$(echo "$response" | head -n 1)
    status=$(echo "$response" | tail -n 1)

    echo "Response: $body"
    echo "Status Code: $status"

    # Extract the shortened URL from the response
    SHORT_URL=$(echo "$body" | jq -r '.shortUrl')
    echo "Shortened URL: $SHORT_URL"
}

# Function to test redirection using the shortened URL
test_redirection() {
    echo "Testing redirection..."
    response=$(curl -s -w "\n%{http_code}" -X GET "$SHORT_URL" \
        -H "Authorization: Bearer $JWT_TOKEN")

    status=$(echo "$response" | tail -n 1)
    echo "Redirection Status Code: $status"
}

# Run the tests
register_user
get_jwt_token
shorten_url
test_redirection
