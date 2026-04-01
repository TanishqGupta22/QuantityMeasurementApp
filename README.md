# ⚖️ Quantity Measurement App

A Java application built using **Data Driven Testing (DDT)** that evolves from a simple unit comparison utility into a full **Spring Boot REST API** with database persistence and security.

---

## 🚀 Tech Stack
- Java 21
- Maven 4.0
- Spring Boot 3.5.12
- Spring Data JPA
- H2 Database
- Spring Security (JWT + OAuth2)
- JDBC

---

## 📖 Project Journey

This project was built **use case by use case**, each adding a new layer of functionality.

---

## 🔵 Phase 1 — Length Measurement (UC1–UC7)

| UC  | Branch | Description |
|-----|--------|------------|
| UC1 | feature/UC1-FeetEquality | Compare two feet values |
| UC2 | feature/UC2-InchEquality | Compare two inch values |
| UC3 | feature/UC3-GenericLength | Introduced LengthUnit enum |
| UC4 | feature/UC4-YardEquality | Added YARDS unit |
| UC5 | feature/UC5-UnitConversion | Unit conversions |
| UC6 | feature/UC6-UnitAddition | Add different units |
| UC7 | feature/UC7-TargetUnitAddition | Result in first unit |

---

## 🟣 Phase 2 — Generic Units (UC8–UC12)

| UC  | Branch | Description |
|-----|--------|------------|
| UC8 | feature/UC8-StandaloneUnit | Introduced IMeasurable |
| UC9 | feature/UC9-WeightMeasurement | Added Weight units |
| UC10 | feature/UC10-GenericQuantity | Generic Quantity |
| UC11 | feature/UC11-VolumeMeasurementEquality | Volume units |
| UC12 | feature/UC12-QuantityMeasurementOperations | Subtract & Divide |

---

## 🟠 Phase 3 — Architecture (UC13–UC15)

| UC  | Branch | Description |
|-----|--------|------------|
| UC13 | feature/UC13-CentralizedOperations | Arithmetic enum |
| UC14 | feature/UC14-TemperatureMeasurement | Temperature logic |
| UC15 | feature/UC15-N-Tier | Controller → Service → Repository |

---

## 🟡 Phase 4 — Database (UC16)

| UC  | Branch | Description |
|-----|--------|------------|
| UC16 | feature/UC16-DatabaseIntegration | JDBC + H2 |

### Key Concepts:
- JDBC Connection Management  
- Connection Pooling  
- Parameterized Queries  
- application.properties  
- Custom Exceptions  

---

## 🟢 Phase 5 — Spring Boot REST API (UC17)

| UC  | Branch | Description |
|-----|--------|------------|
| UC17 | feature/UC17-SpringBackendQMA | Full Spring Boot API |

### Key Concepts:
- Spring Boot Auto Configuration  
- Spring Data JPA  
- REST Controllers  
- Dependency Injection (@Autowired)  
- Global Exception Handling  
- H2 Database  

---

## 🔴 Phase 6 — Security (UC18)

| UC  | Branch | Description |
|-----|--------|------------|
| UC18 | feature/UC18-GoogleAuth | OAuth2 + JWT |

### Key Concepts:
- Google OAuth2  
- JWT Authentication  
- Protected APIs  
- Spring Security Filter Chain  
- Stateless Sessions  

---

## 📐 Supported Units

| Category | Units |
|----------|------|
| Length | FEET, INCH, YARDS, CENTIMETER |
| Weight | KILOGRAM, GRAM, POUND |
| Volume | LITRE, MILLILITRE, GALLON |
| Temperature | CELSIUS, FAHRENHEIT |

---

## ⚙️ Running the App

### Prerequisites
- Java 17+
- Maven 3.6+

### Steps
```bash
git clone <https://github.com/TanishqGupta22/QuantityMeasurementApp>
cd QuantityMeasurementApp
mvn spring-boot:run
 ##Author
Tanishq Gupta
