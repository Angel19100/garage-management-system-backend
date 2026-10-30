# Garage Management System Backend

## Student Information

- **Name:** Rutaremara Angel Faith
- **Student ID:** 27948
- **Course:** INSY 8322 – Web Technology and Internet
- **Institution:** Adventist University of Central Africa (AUCA)
- **Project:** Garage Management System
- **Academic Year:** 2026–2027

## 1. Project Description

The Garage Management System Backend is a REST API developed to manage the daily activities of a vehicle repair garage.

The system allows the garage to manage vehicles, mechanics, specialties, service bays, job cards and payments. It also supports job card status management, payment records, garage reports and dashboard information.

The application was developed using Spring Boot, Spring Data JPA and PostgreSQL.

## 2. Technologies Used

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Postman
- Visual Studio Code

2. Generate the Spring Boot Project

Visit Spring Initializr.

Select Maven as the project type.

Select Java as the language.

Choose Spring Boot version 4.0.9.

Set the project name and artifact to garage-management-system.

Select Java version 25.

Add the required dependencies:

Spring Web

Spring Data JPA

PostgreSQL Driver

Click Generate to download the project ZIP file.

Extract the downloaded ZIP folder and open the project in Visual Studio Code.

## 3. Main Features

### Part A – Domain Model and CRUD

The system manages five main entities:

- Specialty
- Bay
- Mechanic
- Vehicle
- JobCard

Each entity supports the required Create, Read, Update and Delete operations.

### Part B – Derived Queries

- Find vehicles by vehicle type, ignoring letter case.
- Find job cards by status.
- Find job cards within a date range.

### Part C – JPQL Queries

- Find mechanics by specialty.
- Find bays that are not occupied by mechanics.
- Find specialties that no mechanic holds.
- Find vehicles assigned to a particular mechanic through job cards.

### Part D – Business Rules

- Prevent saving job cards with past dates.
- Prevent a mechanic from receiving conflicting job cards on the same date.
- Validate job card status transitions.
- Add specialties to mechanics.
- Move mechanics between bays while checking bay availability.

### Part E – Payments

- Record payments only for finished job cards.
- View payment history for a vehicle using its plate number.
- Delete payments.
- Prevent deleting job cards that still have payments.

### Part F – Reports and Bulk Updates

- Display the number of job cards grouped by status.
- Cancel a mechanic's job cards for a particular date, excluding finished job cards.

### Bonus Features

- Garage dashboard summary showing vehicles, mechanics, free bays, today's job cards, opened job cards and payments.
- Job card pagination with sorting.

## 4. Database Configuration

The application uses PostgreSQL.

Create a database named:

`garage_management_system`

Configure the database connection in:

`src/main/resources/application.properties`


## 5. Requirements

Before running the application, make sure you have installed:

1. Java Development Kit (JDK) 25.
2. Apache Maven.
3. PostgreSQL.
4. Visual Studio Code or another Java IDE.

## 6. How to Run the Application

### Step 1: Clone the repository

```bash
git clone https://github.com/YOUR-GITHUB-USERNAME/garage-management-system-backend.git
```

### Step 2: Open the project

Open the cloned `garage-management-system-backend` folder in Visual Studio Code.

### Step 3: Configure PostgreSQL

Create the database:

```sql
CREATE DATABASE garage_management_system;
```

Update the database username and password in `application.properties`.

### Step 4: Build the application

Open the terminal in the project folder and run:

```bash
mvn clean install
```

### Step 5: Start the application

Run:

```bash
mvn spring-boot:run
```

The application starts on:

`http://localhost:8080`

## 7. Testing with Postman

The API endpoints were tested using Postman.

The exported Postman collection is included in the root directory:

`garage-management-system-midterm.postman_collection.json`

The collection contains requests for the assignment requirements, organised by their requirement IDs, including A2, B1, C1, D1, E1 and F1.

## 8. Main API Endpoints

| Feature | HTTP Method | Endpoint |
|---|---|---|
| Save specialty | POST | `/api/specialties/save` |
| Get all specialties | GET | `/api/specialties/all` |
| Save bay | POST | `/api/bays/save` |
| Get all bays | GET | `/api/bays/all` |
| Get free bays | GET | `/api/bays/free` |
| Save mechanic | POST | `/api/mechanics/save` |
| Get all mechanics | GET | `/api/mechanics/all` |
| Save vehicle | POST | `/api/vehicles/save` |
| Get all vehicles | GET | `/api/vehicles/all` |
| Find vehicles by type | GET | `/api/vehicles/by-vehicle-type` |
| Save job card | POST | `/api/job-cards/save` |
| Get all job cards | GET | `/api/job-cards/all` |
| Find job cards by status | GET | `/api/job-cards/by-status` |
| Find job cards by date range | GET | `/api/job-cards/between` |
| Update job card status | PATCH | `/api/job-cards/{id}/status` |
| Save payment | POST | `/api/payments/save` |
| Get vehicle payment history | GET | `/api/payments/vehicle/{plateNumber}` |
| Get job card statistics | GET | `/api/job-cards/stats/by-status` |
| Cancel job cards for a date | PATCH | `/api/job-cards/cancel-day` |
| Get dashboard summary | GET | `/api/dashboard/summary` |
| Get paginated job cards | GET | `/api/job-cards/page` |

