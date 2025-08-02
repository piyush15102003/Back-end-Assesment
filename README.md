# Clinic Appointment Management System

This is a basic appointment management system for a small clinic with a multi-doctor setup where patients can book appointments with available doctors.

## Setup Instructions
1. **Clone the Repository**
   ```bash
   git clone https://github.com/piyush15102003/Back-end-Assesment.git
   cd Back-End-Assessment-Piyush-Pandey
   ```

2. **Start the Application**
   ```bash
   ./mvnw spring-boot:run
   ```
   This will start the application at `http://localhost:8080`.


3. **Import the Postman Collection**
   Use the provided `Clinic_Appointment_System.postman_collection.json` to load the API collection into Postman for testing.

## Sample Requests

### Using Postman

- **Get All Doctors:**
  - **Method:** GET
  - **URL:** `http://localhost:8080/doctors`

- **Book an Appointment:**
  - **Method:** POST
  - **URL:** `http://localhost:8080/appointments`
  - **Body:**
    ```json
    {
      "doctorId": 1,
      "patientId": 1,
      "slot": "2025-08-03T09:00:00"
    }
    ```

### Using Curl

- **Get All Doctors:**
  ```bash
  curl -X GET "http://localhost:8080/doctors"
  ```

- **Book an Appointment:**
  ```bash
  curl -X POST "http://localhost:8080/appointments" -H "Content-Type: application/json" -d '{"doctorId": 1, "patientId": 1, "slot": "2025-08-03T09:00:00"}'
  ```


## Design Decisions

- **Thread-Safe In-Memory Storage**: Used `ConcurrentHashMap` for storing doctors, patients, and appointments to ensure thread safety during concurrent access.

- **Double Booking Prevention**: Synchronized methods and validation checks to prevent double booking of slots for both doctors and patients.

- **Input Validation**: Utilized Java Bean Validation API to enforce rules for input data and ensure data integrity.

- **Custom Exception Handling**: Implemented `@ExceptionHandler` for graceful error handling and custom exceptions to provide meaningful error messages to the client.

- **Swagger Integration**: Added Swagger for API documentation, making it easier for developers to test and understand the system.

- **Structured Package Organization**: Followed a clean architecture pattern (`controller`, `service`, `model`, `repository`, `exception`, `config`) to maintain separation of concerns and modular code design.

---

With these functionalities and decisions, the system ensures a coherent and efficient flow from registering entities to managing appointments.
