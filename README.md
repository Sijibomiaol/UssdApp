# UssdApp

# USSD Banking Application

This project is a **USSD Banking Application** built using Java and Spring Boot. It provides users with a seamless way to perform banking operations such as creating accounts, depositing funds, withdrawing funds, and checking balances via USSD codes.

---

## Features

### User Management
- **Create User Account**: Users can register for an account using their phone number, name, and PIN.

### Account Transactions
- **Deposit**: Allows users to deposit money into their account.
- **Withdraw**: Enables withdrawal of funds from their account.
- **Check Balance**: Users can check their account balance.

### USSD Functionality
- **USSD Codes**: Provides intuitive USSD codes for navigation and operations.
- **Dynamic Menus**: Displays contextual options and confirmations during transactions.

---

## Technology Stack

### Backend
- **Programming Language**: Java
- **Framework**: Spring Boot

### Tools & Libraries
- **Database**: H2 (in-memory) for development/testing, MySQL/PostgreSQL for production.
- **Messaging**: Kafka for asynchronous communication (SMS notifications).
- **Utilities**:
  - Custom utilities for encryption (SecurityUtils), account number generation (AccountGenerator), and model mapping (ModelMapperUtils).

### Caching
- **Spring Cache**: Implements caching for user data to enhance performance.

---

## Project Structure

```plaintext
src/main/java/com/sijibomiaol/skaetAss
    |- context
        |- entity: JPA entities for database tables.
        |- repository: JPA repositories for data access.
    |- model
        |- request: DTOs for incoming requests.
        |- response: DTOs for API responses.
    |- service
        |- Impl: Implementation classes for business logic.
    |- utills: Utility classes for common functions.
    |- messaging
        |- kafka: Classes for Kafka-based SMS service.
    |- exception: Custom exception handling classes.
```

---

## Installation

### Prerequisites
- Java 11 or higher
- Maven
- MySQL/PostgreSQL database (for production environment)

### Steps
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd ussd-banking-app
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. Access the API documentation (if enabled):
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## Usage

### USSD Flow
- Dial `*123#` to begin.
- Navigate through options using numeric inputs:
  1. Create Account
  2. Account Deposit
  3. Account Withdrawal
  4. Account Balance

---

## Environment Configuration

### application.properties
Update the following properties as needed:

```properties
# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=<db_username>
spring.datasource.password=<db_password>

# Kafka configuration
spring.kafka.bootstrap-servers=<kafka_server>

# Cache configuration
spring.cache.type=simple
```

---

## Contributing

### Guidelines
1. Fork the repository.
2. Create a new branch for your feature or bug fix:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add your message here"
   ```
4. Push to your branch:
   ```bash
   git push origin feature-name
   ```
5. Submit a pull request.

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.

---

## Acknowledgments
- **Spring Boot Team**: For providing the framework.
- **Contributors**: Thanks to all contributors for their efforts.

---

## Contact
For any inquiries, reach out to:
- **Author**: Sijibomi Moses Aderinlewo
- **Email**: Aderinlewom@gmail.com
- 

