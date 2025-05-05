# Create New Account Page – Unit Testing Project

This is a Java Swing-based "Create New Account" form developed as part of a **Software Verification and Validation** course assignment. The form includes real-time input validation and comprehensive unit tests using **JUnit** and automated CI via **GitHub Actions**.

## 🧩 Features

- ✅ Form with fields: First Name, Last Name, Email, Password, Confirm Password, Date of Birth  
- 🔐 Password rules: 8+ characters, uppercase, lowercase, digit, special character  
- ⚠ Real-time field validation and error messages  
- 🎉 Success screen with green background if form is valid  
- 📅 Date of birth validation (leap years, valid days/months)

## 🧪 Unit Tests (JUnit 5)

- Name and email validations  
- Password rule checks and mismatch cases  
- Valid/invalid date formats  
- Equivalence Partitioning, Boundary Value Analysis  
- Test lifecycle methods: `@BeforeEach`, `@AfterEach`

## 🚀 GitHub Actions

Each commit triggers automated tests via **GitHub Actions** CI/CD workflow.

## 🔧 Technologies Used

- Java 17  
- Swing GUI  
- JUnit 5  
- Maven  
- Git & GitHub  
- GitHub Actions

## 👨🏻‍🤝‍👩🏻 Contributors

- 👩‍💻 [Buse İpek Nacaroğlu] – (https://github.com/buseipek-ncrgl)  
- 👨‍💻 [Enes Cıkcık] – (https://github.com/enesckk)

## 📂 Project Structure

CreateAccountJUnit/
├── src/
│ └── main/java/com/account/
│ ├── CreateAccountForm.java
│ └── Validator.java
├── src/
│ └── test/java/com/account/
│ └── ValidatorTest.java
├── pom.xml
└── .github/workflows/maven.yml


## 📝 How to Run

1. Open the project in VS Code  
2. Ensure Java 17 and Maven are installed  
3. Run `mvn test` to execute unit tests  
4. Run `CreateAccountForm.java` to launch the form GUI

---

_This project was developed for academic purposes in 2025 and demonstrates good software testing practices with automation._


