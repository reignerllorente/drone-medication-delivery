# 📦 The Drone – Medication Delivery System

## 🚁 Overview

**The Drone** is a RESTful Java Spring Boot application for managing a fleet of drones used to deliver medications.  
The system supports registering drones, loading medications, checking battery levels, and automating state transitions using a scheduler.

---

## 🛠️ How to Build & Run

### 🔧 Prerequisites
- Java 8+
- Maven

### ▶️ Build and Run

```bash
# Clone the repository
git clone https://github.com/reignerllorente/drone-medication-delivery.git
cd drone-medication-delivery

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### 📂 H2 Console (for in-memory DB access)
- URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:drone_db`
- User: `sa` | Password: _(leave blank)_

---

## 📡 REST API Endpoints

| Method | Endpoint                             | Description                                  |
|--------|--------------------------------------|----------------------------------------------|
| POST   | `/api/drones`                        | Register a new drone                         |
| GET    | `/api/drones/available`              | Get list of drones available for loading     |
| GET    | `/api/drones/{droneId}/medications` | Get loaded medications for a drone           |
| POST   | `/api/drones/{droneId}/load`         | Load medications onto a drone                |
| GET    | `/api/drones/{droneId}/battery`      | Check battery level of a drone               |

---

## 🧪 Sample Data

On application start, the in-memory database is empty unless populated.  
You can preload some test data via the H2 console or add a `CommandLineRunner`.

Example drone JSON for registration:

```json
{
  "serialNumber": "DRONE-001",
  "model": "Lightweight",
  "weightLimit": 300,
  "batteryCapacity": 100,
  "state": "IDLE"
}
```

Example medication JSON for loading:

```json
[
  {
    "name": "Med_A-123",
    "weight": 150,
    "code": "CODE123_A"
  },
  {
    "name": "Med_B_456",
    "weight": 100,
    "code": "CODE456_B"
  }
]
```

---

## 📬 Postman / cURL Usage

### ✅ Register Drone
```bash
curl -X POST http://localhost:8080/api/drones   -H "Content-Type: application/json"   -d '{
    "serialNumber": "DRONE-002",
    "model": "Cruiserweight",
    "weightLimit": 500,
    "batteryCapacity": 100,
    "state": "IDLE"
  }'
```

### ✅ Load Drone
```bash
curl -X POST http://localhost:8080/api/drones/1/load   -H "Content-Type: application/json"   -d '[
    {
      "name": "PainRelief_500",
      "weight": 200,
      "code": "MED_500"
    }
  ]'
```

### ✅ Get Loaded Medications
```bash
curl http://localhost:8080/api/drones/1/medications
```

### ✅ Check Battery Level
```bash
curl http://localhost:8080/api/drones/1/battery
```

### ✅ Available Drones for Loading
```bash
curl http://localhost:8080/api/drones/available
```

---

## 📌 Notes

- Drone battery must be ≥ 25% to load medication.
- Weight limit per model is enforced.
- Drone state transitions (LOADING → DELIVERING → etc.) are handled every 10 seconds by a scheduler.
- No authentication required.
