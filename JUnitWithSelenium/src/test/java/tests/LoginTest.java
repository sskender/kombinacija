package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @Test
    public void testLoginGoodCreds () {
        ChromeDriver driver;
        System.setProperty("webdriver.chrome.driver" , "/Users/matea/Downloads/chromedriver");

        driver = new ChromeDriver();
        driver.get("https://kombinacija.hopto.org/");

        // ssl
        WebElement element = driver.findElement(By.id("details-button"));
        element.click();
        WebElement element1 = driver.findElement(By.id("proceed-link"));
        element1.click();

        // login credentials
        driver.findElement(By.id("login-link")).click();

        // login form
        driver.findElement(By.id("email")).sendKeys("matea.vasilj@fer.hr");
        driver.findElement(By.id("pass")).sendKeys("password");

        // submit
        driver.findElement(By.id("signin")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // assert
        String msg = driver.findElement(By.id("welcome-msg")).getAttribute("innerHTML");
        assert (msg.contains("Matea"));
    }

}