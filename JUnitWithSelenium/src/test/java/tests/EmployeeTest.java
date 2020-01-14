package tests;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import tests.credentials.CredentialsTestInfo;

import java.util.concurrent.TimeUnit;

public class EmployeeTest {

    @Test
    public void testRegisterNewEmployee() throws InterruptedException {
        ChromeDriver driver;
        System.setProperty("webdriver.chrome.driver", "/Users/matea/Downloads/chromedriver");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // load index page
        driver.get("https://kombinacija.hopto.org/");

        // ssl
        WebElement element = driver.findElement(By.id("details-button"));
        element.click();
        WebElement element1 = driver.findElement(By.id("proceed-link"));
        element1.click();

        // login
        driver.findElement(By.id("login-link")).click();
        driver.findElement(By.id("email")).sendKeys(CredentialsTestInfo.ADMIN_EMAIL);
        driver.findElement(By.id("pass")).sendKeys(CredentialsTestInfo.ADMIN_PASSWORD);
        driver.findElement(By.id("signin")).click();

        Thread.sleep(3000);

        // assert login
        String welcomeMessage = driver.findElement(By.id("welcome-msg")).getAttribute("innerHTML");
        assert (welcomeMessage.contains(CredentialsTestInfo.ADMIN_NAME));

        // load admin page
        driver.findElement(By.id("go-admin")).click();

        // open add employee form
        driver.findElement(By.cssSelector("a[href*='pageSubmenu']")).click();
        driver.findElement(By.xpath("//a[@onclick=\"toggleVisibility('C1'); loadHoodsADD_E();\"]")).click();

        // fill form
        driver.findElement(By.id("add-emp-new-name")).sendKeys("Leon");
        driver.findElement(By.id("add-emp-new-lname")).sendKeys("Kranjcevic");
        driver.findElement(By.id("add-emp-new-oib")).sendKeys("12312312355");
        driver.findElement(By.id("add-emp-new-email")).sendKeys("leon.kranjcevic@gmail.com");
        driver.findElement(By.id("add-emp-new-pass")).sendKeys("password");
        driver.findElement(By.id("add-emp-new-pass2")).sendKeys("password");

        // select neighborhood
        Select dropdownNeighborhood = new Select(driver.findElement(By.id("add-emp-select")));
        dropdownNeighborhood.selectByVisibleText("Maksimir");

        // submit
        driver.findElement(By.name("add-emp")).click();

        Thread.sleep(3000);

        // handle alert
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        assert (alertText.contains("Komunalni radnik uspješno stvoren"));
    }

    @Test
    public void testMoveEmployee() throws InterruptedException {
        assert (false);
    }

    @Test
    public void testDeleteEmployee() throws InterruptedException {
        assert (false);
    }

}
