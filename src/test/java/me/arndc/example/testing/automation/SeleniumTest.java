package me.arndc.example.testing.automation;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ThymeleafApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumTest {

    private WebDriver driver;

    @BeforeClass
    public static void start() {
        final String webDriverPath =  "driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", webDriverPath);
    }

    @Before
    public void setUp() {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        driver = new ChromeDriver(options);
        
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("http://localhost:8083");

        Thread.sleep(1500);

        final WebElement dropdown = driver.findElement(By.linkText("Dropdown Test"));
        dropdown.click();

        Thread.sleep(1500);

        final WebElement choice2 = driver.findElement(By.linkText("Test 2"));
        choice2.click();

        Thread.sleep(1500);

        final String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).endsWith("/test/2");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
