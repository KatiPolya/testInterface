package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlfaCardTest {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest2() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
        public void test(){
        driver.get("http://localhost:9999");
        driver.findElement(By.name("name")).sendKeys("Мороз Екатерина");
        driver.findElement(By.name("phone")).sendKeys("+42077882355");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String text = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText();

        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    public void invalidNameValidPhoneValidCheckbox(){
        driver.get("http://localhost:9999");
        driver.findElement(By.name("name")).sendKeys("Vitaliy");
        driver.findElement(By.name("phone")).sendKeys("+42077882355");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String text = driver.findElement(By.cssSelector ("[data-test-id='name'].input_invalid .input__sub")).getText();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    public void validNameInvalidPhoneValidCheckbox(){
        driver.get("http://localhost:9999");
        driver.findElement(By.name("name")).sendKeys("Мороз Екатерина");
        driver.findElement(By.name("phone")).sendKeys("+420778823");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__content")).click();
        String text = driver.findElement(By.cssSelector ("[data-test-id='phone'].input_invalid .input__sub")).getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void validNameValidPhoneInvalidCheckbox(){
        driver.get("http://localhost:9999");
        driver.findElement(By.name("name")).sendKeys("Мороз Екатерина");
        driver.findElement(By.name("phone")).sendKeys("+42077882356");
        driver.findElement(By.className("button__content")).click();
        String text = driver.findElement(By.cssSelector ("[data-test-id='agreement'].input_invalid .checkbox__text")).getText();

        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", text.trim());
    }

    @Test
    public void emptyNameEmptyPhoneEmptyCheckbox(){
        driver.get("http://localhost:9999");
        driver.findElement(By.className("button__content")).click();
        String text = driver.findElement(By.cssSelector ("[data-test-id='name'].input_invalid .input__sub")).getText();

        assertEquals("Поле обязательно для заполнения", text.trim());

    }

}
