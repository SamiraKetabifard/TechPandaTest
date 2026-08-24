package Test1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test11 {

    WebDriver driver;

    @BeforeTest
    public void setUp() {

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\parsian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void test1() {

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
