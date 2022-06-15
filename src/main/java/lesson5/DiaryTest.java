package lesson5;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class DiaryTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://diary.ru/");

        System.out.println();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //aop.xml    .setup()   - {System.out.println();}
        WebElement loginButton = driver.findElement(By.xpath("//a[.='Вход']"));
        loginButton.click();

        driver.findElement(By.xpath("//a[.='Вход']")).click();

        driver.findElement(By.id("loginform-username")).sendKeys("spartalex");
        driver.findElement(By.id("loginform-password")).sendKeys("123456");

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();

        driver.switchTo().parentFrame();

        driver.findElement(By.id("login_btn")).click();

        Thread.sleep(5000);

        Cookie authCookie = new Cookie("_identity_", "83668234c30812b14c46bac1deda1a240770255504032650b424a" +
                "75038c00b3aa%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3545507%2C%22E_QJqRaNd" +
                "BrepyeVN7uNXi5Dz9tjGpfX%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(authCookie);
        driver.navigate().refresh();

        driver.findElement(By.xpath("//a[@title='Новая запись']")).click();

        String postName = "Post" + new Random().nextInt(1000);

        //Thread.sleep(1000);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));//Duration.ofSeconds(5)
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postTitle")));

        driver.findElement(By.id("postTitle")).sendKeys(postName);
        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));

        driver.findElement(By.id("tinymce")).sendKeys("Our post test");
        driver.switchTo().parentFrame();

        driver.findElement(By.id("rewrite")).click();

        driver.findElement(By.xpath(String.format("//a[.='%s']", postName))).click();

        List<WebElement> posts = driver.findElements(By.xpath("//a[@class='title']"));
        posts.stream().filter(p -> p.getText().contains(postName)).findFirst().get().click();
        Thread.sleep(5000);

        driver.quit();
    }
}
