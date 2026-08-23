package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class Test5 {

    WebDriver driver;
    String firstName = "samira";
    String lastName = "ketabifard";
    // unique email for every run
    String email = "samira" + System.currentTimeMillis() + "@gmail.com";

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
    public void testCreateAccountAndShareWishList() throws Exception {
        // 1. Go to website
        driver.get("http://live.techpanda.org/");
        // 2. Click My Account
        driver.findElement(By.linkText("MY ACCOUNT")).click();

        Thread.sleep(2000);

        // 3a. Click Create Account
        driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();

        Thread.sleep(2000);

        // 3b. Fill registration form

        driver.findElement(By.id("firstname"))
                .sendKeys(firstName);

        driver.findElement(By.id("lastname"))
                .sendKeys(lastName);

        driver.findElement(By.id("email_address"))
                .sendKeys(email);

        driver.findElement(By.id("password"))
                .sendKeys("samira20@");

        driver.findElement(By.id("confirmation"))
                .sendKeys("samira20@");

        // 4. Register
        driver.findElement(
                By.xpath("//button[@title='Register']")).click();

        Thread.sleep(2000);
        // 5. Verify registration
        String expectedWelcome =
                "WELCOME, " + firstName + " " + lastName + "!";
        String actualWelcome =driver.findElement(
                        By.cssSelector("p.welcome-msg")).getText();

        System.out.println("Expected : " + expectedWelcome);
        System.out.println("Actual   : " + actualWelcome);

        Assert.assertEquals(actualWelcome.toUpperCase(), expectedWelcome.toUpperCase());

        // 6. Go to TV menu
        driver.findElement(By.linkText("TV")).click();
        Thread.sleep(2000);
        // 7. Add LG LCD to wishlist
        driver.findElement(
                By.linkText("Add to Wishlist")).click();
        Thread.sleep(2000);
        // 8. Share Wishlist
        driver.findElement(
                By.xpath("//button[@title='Share Wishlist']")).click();
        Thread.sleep(2000);
        // 9. Fill share form
        driver.findElement(By.id("email_address")).clear();
        driver.findElement(By.id("email_address")).sendKeys("support@guru99.com");
        driver.findElement(By.id("message"))
                .sendKeys(
                        "Hey!! this LCD TV looks ok, check it out !!");
        driver.findElement(
                By.xpath("//button[@title='Share Wishlist']")).click();
        Thread.sleep(2000);
        // 10. Verify Wishlist shared
        String expectedWishList =
                "Your Wishlist has been shared.";
        String actualWishList = driver.findElement(
                By.cssSelector("li.success-msg span")).getText();
        //String actualWishList =
                //driver.findElement(
                        //By.xpath(
                          //      ".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
        System.out.println("Expected Wishlist : " + expectedWishList);
        System.out.println("Actual Wishlist   : " + actualWishList);

        Assert.assertEquals(
                actualWishList, expectedWishList);

    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}