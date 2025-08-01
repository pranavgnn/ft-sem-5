# Fintech Lab Spring Boot Application

A Spring Boot application with authentication system implementing JWT-based security with session management.

## Features

- User registration and authentication using Spring Security
- BCrypt password hashing
- Session-based authentication
- Protected and open routes
- MySQL database integration
- Input validation
- Global exception handling

## API Endpoints

### Open Routes (accessible to everyone)

- `GET /api/calculator` - Calculator page (open to all users)

### Authentication Routes (only for non-authenticated users)

- `POST /api/auth/signup` - User registration
- `POST /api/auth/login` - User login

### Protected Routes (only for authenticated users)

- `POST /api/auth/logout` - User logout
- `GET /api/test` - Test page (protected route)

## Request/Response Examples

### User Registration

```http
POST /api/auth/signup
Content-Type: application/json

{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123"
}
```

Response:

```json
{
  "message": "User registered successfully",
  "username": "testuser",
  "success": true
}
```

### User Login

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "testuser",
  "password": "password123"
}
```

Response:

```json
{
  "message": "Login successful",
  "username": "testuser",
  "success": true
}
```

### User Logout

```http
POST /api/auth/logout
```

Response:

```json
{
  "message": "Logout successful",
  "username": null,
  "success": true
}
```

## Project Structure

```
src/main/java/com/fintech/
├── controller/          # REST controllers
│   ├── AuthController.java
│   └── AppController.java
├── service/            # Business logic services
│   ├── AuthService.java
│   └── CustomUserDetailsService.java
├── entity/             # JPA entities
│   └── User.java
├── repository/         # Data access layer
│   └── UserRepository.java
├── config/             # Configuration classes
│   ├── SecurityConfig.java
│   └── JpaAuditConfig.java
├── dto/                # Data transfer objects
│   ├── SignupRequest.java
│   ├── LoginRequest.java
│   └── AuthResponse.java
├── exception/          # Exception handling
│   ├── GlobalExceptionHandler.java
│   ├── UserAlreadyExistsException.java
│   └── InvalidCredentialsException.java
└── FintechLabApplication.java
```

## Configuration

The application is configured via `application.properties`:

- **Database**: MySQL (localhost:3306/ft-sem5)
- **Server Port**: 8081
- **JWT Secret**: Configured for future JWT implementation
- **Logging**: Debug level for security and application packages

## Security Features

1. **Password Hashing**: BCrypt with salt
2. **Session Management**: Spring Security session management
3. **Route Protection**:
   - Open routes: `/api/calculator`
   - Auth routes (non-authenticated only): `/api/auth/login`, `/api/auth/signup`
   - Protected routes (authenticated only): `/api/auth/logout`, `/api/test`
4. **Input Validation**: Bean validation for request DTOs
5. **Exception Handling**: Global exception handler for consistent error responses

## Running the Application

1. Make sure MySQL is running and the database `ft-sem5` exists
2. Update database credentials in `application.properties` if needed
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. The application will start on `http://localhost:8081`

## Testing with PowerShell

### Register a new user:

```powershell
$body = @{username="testuser"; email="test@example.com"; password="password123"} | ConvertTo-Json
Invoke-RestMethod -Uri "http://localhost:8081/api/auth/signup" -Method POST -Body $body -ContentType "application/json"
```

### Login:

```powershell
$body = @{username="testuser"; password="password123"} | ConvertTo-Json
Invoke-RestMethod -Uri "http://localhost:8081/api/auth/login" -Method POST -Body $body -ContentType "application/json" -SessionVariable session
```

### Access protected route:

```powershell
Invoke-RestMethod -Uri "http://localhost:8081/api/test" -Method GET -WebSession $session
```

### Logout:

```powershell
Invoke-RestMethod -Uri "http://localhost:8081/api/auth/logout" -Method POST -WebSession $session
```
