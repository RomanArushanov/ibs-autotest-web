package org.ibs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    static ChromeOptions chromeOptions = new ChromeOptions();
    static WebDriverWait wait;

    @BeforeAll
    static void init(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        chromeOptions.setBinary("C:\\Users\\Роман\\Downloads\\chrome-win64\\chrome.exe");
        driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10,1000));
    }

   @AfterAll
    public static void close() {
        driver.quit();
    }

    public static Object[][] testDataOne() {
        return new Object[][]{
                {"Apple", "FRUIT", "Фрукт", "5"},
                {"Gurke", "VEGETABLE", "Овощ", "6"},
                {"胡蘿蔔", "VEGETABLE", "Овощ", "7"},
                {"Абрикос", "FRUIT", "Фрукт", "8"},
                {"موز", "FRUIT", "Фрукт", "9"}
        };
    }

    public static Object[][] testDataTwo() {
        return new Object[][]{
                {"Огурец", "VEGETABLE", "Овощ", "5"},
                {"Tomate", "VEGETABLE", "Овощ", "6"},
                {"Apfel", "FRUIT", "Фрукт", "7"},
                {"普通話", "FRUIT", "Фрукт", "8"},
                {"الشمندر", "VEGETABLE", "Овощ", "9"}
        };
    }

    void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
