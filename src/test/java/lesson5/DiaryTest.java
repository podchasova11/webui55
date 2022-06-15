package lesson5;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    WebDriver driver;
    WebDriverWait webDriverWait;

    //a[@data-test='LINK ITEM-NAME ITEM-URL' and contains(@href, 'movie')]


    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait((driver), Duration.ofSeconds(5));
        driver.get("https://diary.ru/");
    }

    @Test
    void likeFilmTest() {
        driver.findElement(By.xpath("//a[.='Вход']")).click();

        driver.findElement(By.id("loginform-username")).sendKeys("spartalex");
        driver.findElement(By.id("loginform-password")).sendKeys("123456");

    }
// Тест работает браузер открывается и затем закрывается 
    @AfterEach
    void tearDown() {
        driver.quit();
    }

}





/**
 *
 * ЭТО ГОТОВЫЙ ШАБЛОН ПОСТРОЕНИЯ АВТОТЕСТА
 *
 * @BeforeAll
static void registerDriver() {
WebDriverManager.chromedriver().setup();
}

 @BeforeEach
 void initDriver() {
 driver = new ChromeDriver();
 webDriverWait = new WebDriverWait((driver, Duration.ofSeconds(5)));
 }

 @Test
 void likeFilmTest() {

 }

 @AfterEach
 void tearDown() {
 driver.quit();
 }

 }**/
