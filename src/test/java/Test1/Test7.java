package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test7 {

    WebDriver driver;

    public String firstName = "samira";
    public String lastName = "ketabifard";
    public String vEmail = "sketabifard@gmail.com";
    public String vPW = "samira20@";

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
    public void verifySavePreviouslyPlacedOrderAsPDF() throws InterruptedException {

        // Step 1. Go to http://live.techpanda.org
        driver.get("http://live.techpanda.org ");

        // 2. Click on My Account link
        driver.findElement(By.linkText("MY ACCOUNT")).click();

        Thread.sleep(2000);

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // 3. Login in application using previously created credential
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(vEmail);
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(vPW);
        driver.findElement(By.id("send2")).click();	 // this is the Login button

        Thread.sleep(2000);

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // 4. Click on 'My Orders'
        driver.findElement(By.linkText("MY ORDERS")).click();

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // 5. Click on 'View Order'
        driver.findElement(By.linkText("VIEW ORDER")).click();

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        // 7. Click on 'Print Order' link now covers 3
        driver.findElement(By.linkText("Print Order")).click();


        // 8. Click 'Change...' link and a popup will be opened as 'Select a destination' , select 'Save as PDF' link.
        // note:  This do  not exist:   "Change...." link

        // 9. Click on 'Save' button and save the file in some location.
        // note: unable to execute this step due to issue in step 8.
        // 10.Verify Order is saved as PDF
        // note: unable to execute this step due to issue in steps 8 and 9.
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
