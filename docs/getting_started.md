# Local Development Setup Guide

This guide explains how to get the local development environment up and running for both the Java Gradle backend (with PostgreSQL) and the React frontend.

---

## 🛠 Prerequisites

Ensure you have the following installed on your machine before starting:
* **Java Development Kit (JDK)**: Version 17 or higher recommended.
* **Node.js**: Version 18 or higher (includes `npm`).
* **PostgreSQL / pgAdmin**: A local instance running with a created database.

---

## 💾 1. Database Setup

1. Open **pgAdmin** and connect to your local PostgreSQL server.
2. Create a new database for the project (e.g., `my_project_db`).
3. Update the database credentials in your backend configuration if necessary.

---

## ☕ 2. Backend Setup (Java Gradle)

Navigate to your backend project directory to build and run the application.

```bash
# Navigate to the backend directory (if nested)
cd backend

# Build the project and download dependencies
./gradlew build

# Run the Spring Boot / Java application locally
./gradlew bootRun
```

*Note: For Windows machines, use `gradlew.bat` instead of `./gradlew`.*

---

## ⚛️ 3. Frontend Setup (React)

Open a new terminal window to keep the backend server running, then navigate to your frontend directory.

```bash
# Navigate to the frontend directory
cd frontend

# Install project dependencies
npm install

# Start the local development server
npm start
```

*Note: If your project uses Vite instead of Create React App, run `npm run dev` instead.*

---

## 🔗 Project Entry Points

Once both services are running successfully, you can access them at:
* **Frontend Client:** [http://localhost:3000](http://localhost:3000) (or `http://localhost:5173` for Vite)
* **Backend API Documentation/Base:** [http://localhost:8080](http://localhost:8080)
* **pgAdmin Web Interface:** [http://localhost:80](http://localhost:80) (or your custom dashboard port)