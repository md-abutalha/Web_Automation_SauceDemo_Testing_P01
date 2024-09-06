# SauceDemo Automation Project

This repository contains automation scripts for testing the [Sauce Demo](https://www.saucedemo.com/) website, as well as certain elements of the [Only Testing Blog](https://only-testing-blog.blogspot.com/). The goal of this project is to demonstrate comprehensive testing using various Selenium WebDriver capabilities, along with Java-based frameworks for automation. Only selected areas have been tested to highlight key functionalities and best practices in test automation.

## Technologies Used

- **Selenium**: For browser automation and interaction with web elements.
- **Java**: Programming language used to write the test scripts.
- **TestNG**: Testing framework to organize, execute, and manage tests.
- **Allure**: For generating advanced and visually rich test reports.
- **Maven**: Build and dependency management tool.
- **IntelliJ IDEA**: IDE used for code development.
- **POM (Page Object Model)**: Design pattern used to enhance code reusability and maintainability.

## Test Scenarios

The following areas of **Sauce Demo** and **Only Testing Blog** websites have been automated:

1. **Sauce Demo Website**
   - **Title Validation**: Verifying the page title of the Sauce Demo homepage.
   - **Login Functionality**: Testing the login process with correct credentials and validating post-login behavior.
   - **Dropdown Selection**: Testing product sorting functionality using a dropdown menu.
   - **XPath Usage**: Testing various elements using XPath methods to validate the product display and interaction.

2. **Only Testing Blog**
   - **Multiple Dropdown Selection**: Interacting with a multi-select dropdown and verifying selected options.
   - **CheckBox Functionality**: Verifying checkbox selection and behavior.
   - **Action Class Methods**: Demonstrating the use of Action class methods to perform complex user interactions like mouse hovering, context-clicking, and double-clicking.
   - **Alert Handling**: Automating alert interactions and handling alert prompts.

## Features

- **Cross-browser Testing**: Easily adaptable to run tests across different browsers such as Chrome and Firefox.
- **Explicit and Fluent Waits**: Use of both `WebDriverWait` and `FluentWait` for precise handling of dynamic elements.
- **Modular Design**: Tests are designed using the Page Object Model (POM) to improve code structure and maintainability.
- **Test Reports**: Allure reports provide detailed insights, including screenshots and test execution data.

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/saucedemo.git


## 2. Navigate to the Project Directory:
 ```bash
cd saucedemo
 ```

## 3. Install Dependencies: Ensure that Maven is installed and run:
 ```bash
mvn clean install
 ```
## 4. Run the Tests: To execute the test cases, run:
 ```bash
mvn test
 ```
## 5.Generate Test Reports: After test execution, generate Allure reports by running:
 ```bash
mvn allure:serve

 ```
##  **Project Structure**
 ```bash
├── src
│   ├── main
│   │   └── java    # Page Object Models and utility classes
│   ├── test
│   │   └── java    # Test scripts
├── pom.xml         # Maven project object model
└── README.md       # Project documentation
 ```
## **Key Code Snippets**
## Example: Title Validation Test
 ```bash
@Test
public void validateTitle() {
    String title = driver.getTitle();
    Assert.assertEquals("Swag Labs", title);
}

 ```

## Login Test
 ```bash
@Test
public void validateLogin() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    driver.findElement(By.id("password")).sendKeys("secret_sauce");
    driver.findElement(By.id("login-button")).click();
    Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
}

 ```


## Example: Handling Dropdowns
 ```bash
@Test
public void selectValue() {
    validateLogin();
    WebElement element = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
    Select select = new Select(element);
    select.selectByVisibleText("Price (low to high)");
}

 ```

## Future Enhancements
- Expand test coverage to include more functionalities of Sauce Demo and other areas of the Only Testing Blog.
- Add support for Continuous Integration (CI) pipelines using Jenkins or GitHub Actions.
- Increase browser compatibility by adding more cross-browser tests.

## **Contact**
For any inquiries or suggestions, feel free to reach out:
Md. Abu Talha
abutalhabd88@gmail.com
