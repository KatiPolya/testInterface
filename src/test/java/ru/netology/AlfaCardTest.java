package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlfaCardTest {
    private WebDriver driver;

    @BeforeAll
    static void setUp(){
        System.setProperty("webdriver.chrome.driver", "driver/mac/chromedriver");
    }

    @BeforeEach
    void setUp2(){
        driver = new ChromeDriver();
    }

    @AfterEach
        public void close(){
        driver.quit();
        driver = null;
    }

    @Test
        public void test(){
        driver.get("http://localhost:9999");
        driver.findElement(By.name("name")).sendKeys("Мороз Екатерина");
        driver.findElement(By.name("phone")).sendKeys("+42077882355");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        driver.findElement(By.className("paragraph")).getText();
        String text = driver.findElement(By.className("paragraph")).getText();

        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    public void test2(){
        driver.get("http://localhost:9999");
        driver.findElement(By.name("name")).sendKeys("Vitaliy");
        driver.findElement(By.name("phone")).sendKeys("+42077882355");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        driver.findElement(By.className("input__sub")).getText();
        String text = driver.findElement(By.className("input__sub")).getText();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }
}
