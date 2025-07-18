# Rest Assured API Testing Project

Welcome to my Rest Assured API testing project! 👋

This project is built to test APIs using **Rest Assured** (a Java library), along with **TestNG** and **Maven**.
I utilized APIs from [Rahul Shetty Academy](https://rahulshettyacademy.com) to practice real-world API testing, 
including login, product creation, order placement, and product deletion.

---

## 📂 What’s Inside This Project?

- `src/main/java/pojo/` → Java classes for creating and reading JSON data
- `src/test/java/` → Contains actual test scripts using Rest Assured
- `testng.xml` → Controls how and which tests are run
- `pom.xml` → Lists all the libraries (dependencies) the project needs
- `addPlace.json` → Sample JSON file used in testing

---

## 🛠 Tools & Technologies Used

- Java
- Rest Assured
- TestNG
- Maven
- Git & GitHub

---

## ✅ What This Project Can Do

- Log in to get the user token
- Add a new product using the POST API
- Place an order with dynamic data
- Delete a product using the DELETE API
- Uses Java classes to make request bodies easy to manage

---

## ▶️ How to Run This Project

### Step 1: Setup
Make sure you have:
- Java installed
- Maven installed
- An internet connection (APIs are online)

### Step 2: Run the Tests
Open terminal/command prompt in the project folder and run:

```bash
mvn test
````

Alternatively, you can open the project in Eclipse/IntelliJ and run `testng.xml` directly.

---

## 📸 Test Flow (Simple Steps)

1. Log in with your email and password
2. Get a token and use it in the next requests
3. Add a product with details and an image
4. Place an order using the product ID
5. Delete the product to clean up

---

## 🧑‍💻 Author

Made with ❤️ by **Aakash Kumar**
GitHub: [@aakashshau](https://github.com/aakashshau)

