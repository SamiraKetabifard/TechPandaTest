package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class Test2 {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\parsian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(2000));
    }

    @Test
    public void comparePrice() throws  Exception{
        // 1. Go to http://live.techpanda.org
        driver.get("http://live.techpanda.org");

        // 2. Click on Mobile menu
        driver.findElement(By.linkText("MOBILE")).click();

        // 3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
        String XPeriaPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();

        // 4. Click on Sony Xperia mobile
        driver.findElement(By.id("product-collection-image-1")).click();

        // 5. Read the XPeria mobile price from details page
        String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();

        //  Product price in list and details page should be equal ($100)
        try {
            assertEquals(XPeriaPrice, detailPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

