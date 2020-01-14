package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {

    @Test
    public void testRegisterCitizen() {
        ChromeDriver driver;
        System.setProperty("webdriver.chrome.driver" , "/Users/matea/Downloads/chromedriver");

        driver = new ChromeDriver();
        driver.get("https://kombinacija.hopto.org/");

        // ssl
        WebElement element = driver.findElement(By.id("details-button"));
        element.click();
        WebElement element1 = driver.findElement(By.id("proceed-link"));
        element1.click();

        // register form
        driver.findElement(By.id("register")).click();

        // credentials
        driver.findElement(By.id("name")).sendKeys("Domagoj");
        driver.findElement(By.id("lastname")).sendKeys("Marinello");
        driver.findElement(By.id("email")).sendKeys("domagoj.marinello@fer.hr");
        driver.findElement(By.id("pass")).sendKeys("Domagoj");
        driver.findElement(By.id("re_pass")).sendKeys("Domagoj");

        // checkbox
        driver.findElement(By.xpath("//label[@for='agree-term']")).click();

        // submit
        driver.findElement(By.id("signup")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // assert
        String msg = driver.findElement(By.id("welcome-msg")).getAttribute("innerHTML");
        assert (msg.contains("Domagoj"));
    }

}

