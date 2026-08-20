package Test1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.*;

public class Test1{

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\parsian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(2000));
    }

    @Test
    public void testMobileSorting() throws Exception {
        // 1. Go to website
        driver.get("http://live.techpanda.org/");

        // 2. Verify Title
        String homeTitle = driver.getTitle();
        System.out.println("Home Title: " + homeTitle);

        // 3. Click MOBILE
        driver.findElement(By.linkText("MOBILE")).click();

        // 4. Verify mobile Title
        String mobileTitle = driver.getTitle();
        System.out.println("Mobile Title: " + mobileTitle);

        // 5. Sort by Name
        Select sortDropdown = new Select(driver.findElement(By.cssSelector("select[title='Sort By']")));
        sortDropdown.selectByVisibleText("Name");
        Thread.sleep(2000);

        // 6. Verify sorting
        List<WebElement> productElements = driver.findElements(By.cssSelector(".product-name a"));
        List<String> productNames = new ArrayList<>();
        for (WebElement element : productElements) {
            productNames.add(element.getText().trim());
        }

        List<String> sortedNames = new ArrayList<>(productNames);
        Collections.sort(sortedNames);

        Assert.assertEquals(productNames, sortedNames, "Products are NOT sorted by name!");
        System.out.println("Products are sorted correctly.");
    }

    @AfterTest
    public void tearDown() {
            driver.quit();

    }
}