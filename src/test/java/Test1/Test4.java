package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class Test4 {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\parsian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testCompareSonyXperiaAndIPhone() throws InterruptedException {

        // 1. Go to http://live.techpanda.org
        driver.get("http://live.techpanda.org");

        // 2. Click on Mobile menu
        driver.findElement(By.linkText("MOBILE")).click();

        // 3. In mobile products list , click on 'Add To Compare' for 2 mobiles (Iphone & Sony Xperia)
        // store the title of the 2 mobiles for comparison for verification later when popup page comes up

        driver.findElement(By.cssSelector(
                "a[href*='product_compare/add/product/2']")).click();

        String mainMobile1 = driver.findElement(
                By.xpath("//h2/a[@title='IPhone']")
        ).getText(); // text captured - upperCase "IPHONE"

        System.out.println("mainMobile1 = " + mainMobile1);

        Thread.sleep(1000);

        driver.findElement(By.cssSelector(
                "a[href*='product_compare/add/product/1']")).click();

        String mainMobile2 = driver.findElement(
                By.xpath("//h2/a[@title='Sony Xperia']")
        ).getText(); // text captured - upperCase "SONY XPERIA"

        System.out.println("mainMobile2 = " + mainMobile2);

        Thread.sleep(1000);

        // 4. Click on 'COMPARE' button. A popup window opens
        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        Thread.sleep(1000);

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // 5. Verify the pop-up window and check that the products are reflected in it
        // Heading "COMPARE PRODUCTS" with selected products in it.

        String strHead = "COMPARE PRODUCTS";

        String compHead = driver.findElement(
                By.cssSelector("h1")
        ).getText();

        System.out.println("compHead = " + compHead);

        String popupMobile1 = driver.findElement(
                By.xpath("//h2/a[@title='IPhone']")
        ).getText(); // text captured is "IPHONE" in uppercase

        String popupMobile2 = driver.findElement(
                By.xpath("//h2/a[@title='Sony Xperia']")
        ).getText(); // text captured "SONY XPERIA" in uppercase

        System.out.println("popupMobile1 = " + popupMobile1);
        System.out.println("popupMobile2 = " + popupMobile2);

        Thread.sleep(1000);

        // to check the popup heading/title
        assertEquals(strHead, compHead);

        // to check the 2 mobiles selected are the two in the popup
        // this is to check the IPhone
        assertEquals(mainMobile1, popupMobile1);

        // to check the 2 mobiles selected are the two in the popup
        // this is to check Sony X
        assertEquals(mainMobile2, popupMobile2);

        // 6. Close the Popup Windows
        driver.findElement(
                By.xpath("//button[@title='Close Window']")
        ).click();

        Thread.sleep(1000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}