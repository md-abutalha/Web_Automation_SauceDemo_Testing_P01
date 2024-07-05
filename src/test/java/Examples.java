
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Examples {
    WebDriver driver;

    @Before
    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
//        driver = new ChromeDriver();

//        WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().setup(); // Use the correct version for your ChromeDriver
//
//
//        driver = new ChromeDriver();


        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        driver.get("https://www.saucedemo.com/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
    }

    @Test
    public void validateTitle() {
        String title = driver.getTitle();
        Assert.assertEquals("Swag Labs", title);
    }

    @Test
    public void validateLogin() {

//        Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));

// this fluent wait
        Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(5))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait1.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.id("login-button"));
            }
        });

        Point point = driver.manage().window().getPosition();
        System.out.println(point.x);
        System.out.println(point.y);

//        defined window by tester
        Point point1 = new Point(22, 22);
        driver.manage().window().setPosition(point1);

        Dimension myDimension = new Dimension(1200, 800);
        driver.manage().window().setSize(myDimension);

        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        String fontColor = driver.findElement(By.id("user-name")).getCssValue("font-size");
        System.out.println(fontColor);

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        String loginAttribute = driver.findElement(By.id("login-button")).getAttribute("value");
        Assert.assertTrue(loginAttribute.equals("Login"));

        String passwordText = driver.findElement(By.xpath("//div[@class='login_password']/h4")).getText();
        Assert.assertTrue(passwordText.equals("Password for all users:"));

        if (driver.findElement(By.id("login-button")).isDisplayed()) {
            driver.findElement(By.id("login-button")).click();
        }

        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", url); //from junit
    }

    @Test
    public void selectValue() {
        validateLogin();
        WebElement element = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(element);
//        select.selectByValue("za");
//        select.selectByIndex(3);
        select.selectByVisibleText("Price (low to high)");

        if (select.isMultiple()) {
            System.out.println("select multiple dropdown");
        } else {
            System.out.println("single select dropdown");
        }
    }

    @Test
    public void multipleSelectElement() {
        driver.navigate().to("https://only-testing-blog.blogspot.com/2014/01/textbox.html");
        WebElement element = driver.findElement(By.name("FromLB"));
        Select select = new Select(element);

        if (select.isMultiple()) {
            System.out.println("It is a multiple dropdown");
        } else {
            System.out.println("It is not multiple dropdown");
        }

//        WebElement option1 = select.getFirstSelectedOption();
//        System.out.println(option1.getAttribute("value"));

        select.selectByValue("USA");
        select.selectByValue("Russia");

        select.selectByIndex(2);
        // Print all selected options
        List<WebElement> allSelectedOptions = select.getAllSelectedOptions();
        for (WebElement option : allSelectedOptions) {
            System.out.println(option.getAttribute("value"));
        }
    }

    @Test
    public void checkBox() {
        driver.navigate().to("https://only-testing-blog.blogspot.com/2014/01/textbox.html");
        WebElement checkbox = driver.findElement(By.id("Carlist"));
        Select select1 = new Select(checkbox);
        select1.selectByIndex(1);

        if (select1.isMultiple()) {
            System.out.println("check box it multiple");
        } else {
            System.out.println("single checkBOX");
        }
    }

    @Test
    public void useXpathHere() {
        driver.findElement(By.xpath("//input[contains(@id,'user')]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[starts-with(@id,'user')]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='user-name' and @name='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='user-name' or @name='user-name']")).sendKeys("standard_user");

        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).isDisplayed();
        driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).click();
    }

    @Test
    public void useXpathAccessMethod() {
        driver.findElement(By.xpath("//div[@class='login-box']//following::input[2]")).click();
    }

    @Test
    public void useActionClassMethod() {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.id("user-name"));
        actions.moveToElement(element).click(element).sendKeys("standard_user").doubleClick(element).contextClick(element).build().perform();
    }

    @Test
    public void userAlertMethod() {
        driver.navigate().to("https://only-testing-blog.blogspot.com/2014/01/textbox.html");
        driver.findElement(By.xpath("//input[@value='Show Me Alert']")).click();
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath("//button[text()='Show Me Prompt']")).click();
        driver.switchTo().alert().sendKeys("talha");
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
