package tests;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.credentials.CredentialsTestInfo;

import java.util.concurrent.TimeUnit;

public class NeighborhoodTest {

    @Test
    public void testDeleteNeighborhood() throws InterruptedException {
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

        // select last neighborhood in list
        driver.findElement(By.cssSelector("a[href*='kvartsubmenu']")).click();
        driver.findElement(By.xpath("//a[@onclick=\"toggleVisibility('A2'); loadHoods();\"]")).click();
        driver.findElement(By.xpath("//ul[@id=\"del-hood-list\"]/li[last()]")).click();

        // delete selected
        driver.findElement(By.xpath("//button[@onclick=\"doDeleteNeighborhood()\"]")).click();

        Thread.sleep(3000);

        // handle alert
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        assert (alertText.contains("uspješno obrisano"));
    }

}
