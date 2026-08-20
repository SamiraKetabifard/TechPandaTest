package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class Test3 {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\parsian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testInvalidQuantityForSonyXperia() throws Exception {

        // 1. Go to http://live.techpanda.org
        driver.get("http://live.techpanda.org");

        // 2. Click on Mobile menu
        driver.findElement(By.linkText("MOBILE")).click();

        // 3. In the list of all mobile , click on 'ADD TO CART' for Sony Xperia mobile
        driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]"
                + "/div/div[2]/div[1]/div[3]/ul/li[3]/div/div[3]/button")).click();

        // 4. Change 'QTY' value to 1000 and click 'UPDATE' button.
        // Expected "The requested quantity for "Sony Xperia" is not available." error message is displayed.

        driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input"))
                .clear();

        driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input"))
                .sendKeys("1000");

        driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/button"))
                .click();

        // 5. Verify the error message
        String reqQty = driver.findElement(
                By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[2]/p")
        ).getText();

        String msgQty = "* The maximum quantity allowed for purchase is 500.";

        assertEquals(reqQty, msgQty);

        // 6. Click on 'EMPTY CART' link in the footer of list of all mobiles.
        // A message "SHOPPING CART IS EMPTY" is shown.

        driver.findElement(By.xpath(".//*[@id='empty_cart_button']")).click();

        // 7. Verify cart is empty
        String noItemsStg = "You have no items in your shopping cart.";

        String noItemsMsg = driver.findElement(
                By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/div[2]/p[1]")
        ).getText();

        System.out.println("You have no items msg = " + noItemsMsg);

        assertEquals(noItemsStg, noItemsMsg);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}