# Academic Management System

The Academic Management System (AMS) is a comprehensive solution designed to manage academic operations. This repository contains both the web-based backend application and the Android mobile application.

## Project Structure

- `AMS/`: The backend server and web application built with Python (Flask) and PostgreSQL. It serves the web portal and provides RESTful APIs for the mobile app.
- `AMS Android/`: The mobile client application for the system, built for Android (Java). It consumes the APIs provided by the backend and includes Razorpay for payment processing.

## Prerequisites

To run this project locally, you will need the following installed on your system:
- **Python 3.8+**
- **PostgreSQL** database server
- **Android Studio** (for the mobile application)

---

## Backend Setup (Flask Application)

1. **Navigate to the Backend Directory:**
   ```bash
   cd AMS
   ```

2. **Database Configuration:**
   - Ensure your PostgreSQL server is running.
   - Create a database (e.g., `ams`).
   - Import the provided database schema using the `db.sql` file:
     ```bash
     psql -U postgres -d ams < db.sql
     ```
   - Update the `.env` file in the `AMS` directory with your database credentials:
     ```env
     DB_HOST=localhost
     DB_PORT=5432
     DB_USER=postgres
     DB_PASSWORD=your_password
     DB_NAME=ams
     SECRET_KEY=your_secret_key
     PORT=5000
     ```

3. **Install Dependencies:**
   Install the required Python packages. (You can optionally set up a virtual environment first).
   ```bash
   pip install flask python-dotenv psycopg2-binary
   ```
   *(Note: Adjust the package names if additional dependencies are used in the project).*

4. **Run the Application:**
   ```bash
   python src/app.py
   ```
   The backend server will start on `http://0.0.0.0:5000`.

---

## Android App Setup

1. **Open the Project:**
   Launch Android Studio and select **Open**. Navigate to the `AMS Android/` directory and open it.

2. **Configure Backend URL:**
   Locate the network or API configuration file within the Android project (typically under `app/src/main/java/...`). Update the base URL to point to your backend server. 
   - *Note: If running on an emulator, `localhost` on your machine translates to `10.0.2.2` on the emulator.*
   - Example: `http://10.0.2.2:5000/api/`

3. **Sync & Build:**
   Wait for Gradle to sync the project and download all necessary dependencies (including Volley and Razorpay SDK).

4. **Run the App:**
   Select your target device or emulator and click the **Run** button in Android Studio.

## Technologies Used

### Backend
- **Python** & **Flask**
- **PostgreSQL** (Database)

### Mobile Application
- **Android** (Java)
- **Volley** (Network Requests)
- **Razorpay** (Payment Gateway Integration)
