# SwachhDrishti — Backend API

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

REST API backend for **SwachhDrishti** — a smart citizen garbage
reporting system built as a Final Year B.Tech CSE project.

Citizens report garbage via a Flutter app. This backend handles
authentication, stores reports, runs AI-based severity scoring
through Google Cloud Vision API, awards reward coins, and serves
a municipal hotspot map.

---

## Features

- JWT authentication (signup, login, token validation)
- Garbage report submission with multipart image upload
- Google Cloud Vision API integration for garbage classification
- AI severity scoring (1–5) and garbage type detection
- Reward coin system — verified reports earn Swachh-Coins
- Municipal hotspot map using GPS clustering queries
- Leaderboard ranked by coins earned
- City-wide statistics dashboard

---

## Tech stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.5.14 |
| Database | MySQL 8.0 with Spring Data JPA |
| Security | Spring Security + JWT (jjwt) |
| AI | Google Cloud Vision API |
| Build | Maven |

---

## Project structure

```
src/main/java/com/swachhdrishti/
├── config/          # Security config, CORS
├── controller/      # HTTP endpoints (AuthController, ReportController)
├── service/         # Business logic (AuthService, ReportService, VisionService)
├── repository/      # JPA repositories
├── entity/          # JPA entities (User, Report)
├── dto/             # Request/Response data transfer objects
└── util/            # JwtUtil
```

---

## Getting started

### Prerequisites

- Java 17+
- MySQL 8.0 running locally
- Maven 3.8+
- Google Cloud account with Vision API enabled

### Setup

1. Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/swachh-drishti-backend.git
cd swachh-drishti-backend
```

2. Create your local config file
```bash
cp src/main/resources/application.properties.example \
   src/main/resources/application.properties
```

3. Edit `application.properties` and fill in:
    - Your MySQL username and password
    - A JWT secret (any 64+ character random string)
    - Your Google Vision API key

4. Create the database (Spring will auto-create the tables)
```sql
CREATE DATABASE swachhdrishti;
```

5. Run the application
```bash
mvn spring-boot:run
```

The API will start on `http://localhost:8080`

---

## API endpoints

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | /api/auth/signup | No | Register new user |
| POST | /api/auth/login | No | Login, returns JWT |
| GET | /api/auth/me | Yes | Get current user profile |
| POST | /api/reports | Yes | Submit garbage report with image |
| GET | /api/reports/myreports | Yes | Get current user's reports |
| GET | /api/reports/hotspots | Yes | Get GPS hotspot clusters |
| GET | /api/reports/stats | Yes | Get city-wide statistics |
| GET | /api/users/leaderboard | Yes | Get top users by coins |

---

## Environment variables

See `application.properties.example` for all required configuration.
Never commit `application.properties` — it contains secrets.

---

## Author

**YOUR_NAME** — B.Tech CSE, YOUR_COLLEGE_NAME (2025)

- GitHub: awaneetdecoder(https://github.com/awaneetdecoder)
- LinkedIn: Awaneet Mishra(https://www.linkedin.com/in/awaneet-mishra-6b6b952b0/)
- Project demo: [YouTube link or APK link]

---

## License

MIT — free to use for educational purposes.