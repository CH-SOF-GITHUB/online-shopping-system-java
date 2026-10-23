# Online Shopping System — Java

A console-based online shopping system developed in Java to practice object-oriented programming (OOP), software design, business rules, and unit testing.

## 🎯 Project Objectives

The goal of this project is to build a simple online shopping system while applying Java best practices and fundamental software engineering concepts.

## 🛠️ Technologies

- **Java 17**
- **Apache Maven**
- **JUnit 5** — Unit testing
- **Git & GitHub** — Version control

## ✨ Features

### Implemented

- Product category management using Java enums
- Abstract `Product` class with common attributes and behaviors
- `PhysicalProduct` and `DigitalProduct` subclasses
- Price validation and normalization using `BigDecimal`
- Stock management with quantity validation
- Customer model with basic authentication logic
- Input validation for customer names, emails, and passwords

### Planned

- Shopping cart management
- Order creation and management
- Order total calculation
- Checkout workflow
- Unit tests with JUnit 5
- Improved documentation and test coverage

## 📁 Project Structure

```text
online-shopping-system/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/onlineshop/
│   │           ├── enums/
│   │           │   └── ProductCategory.java
│   │           └── model/
│   │               ├── Product.java
│   │               ├── PhysicalProduct.java
│   │               ├── DigitalProduct.java
│   │               └── Customer.java
│   └── test/
│       └── java/
├── pom.xml
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- Java JDK 17 or later
- Apache Maven
- Git (optional, for cloning the repository)

### Clone the repository

```bash
git clone https://github.com/YOUR-USERNAME/online-shopping-system-java.git
cd online-shopping-system-java
```

Replace `YOUR-USERNAME` with your GitHub username.

### Build the project

```bash
mvn clean compile
```

### Run the tests

```bash
mvn test
```

## 🧪 Testing

JUnit 5 is used to test business rules and validate application behavior.

Planned test scenarios include:

- Valid and invalid product prices
- Product stock increases and decreases
- Invalid product and customer data
- Physical versus digital product shipping requirements
- Customer login and logout behavior

## 📚 Concepts Practiced

- Object-oriented programming
- Encapsulation and inheritance
- Abstraction and polymorphism
- Constructors and validation
- Enums
- `BigDecimal` for monetary values
- Exception handling
- Unit testing with JUnit 5
- Maven project management

## 🔐 Security Note

Customer authentication is simulated for educational purposes only. Password handling is not production-ready and must not be used as-is in a real application.

## 📌 Project Status

**In development.** Features and tests will be added incrementally.

## 👤 Author

**Chaker Ben Said**

- GitHub: [CH-SOF-GITHUB](https://github.com/CH-SOF-GITHUB)
- LinkedIn: [linkedin.com/in/chaker-ben-said](https://www.linkedin.com/in/chaker-ben-said)
