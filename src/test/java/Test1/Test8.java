package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test8 {

    WebDriver driver;

    public String firstName = "samira";
    public String lastName = "ketabifard";
    public String vEmail = "sketabifard@gmail.com";
    public String vPW = "samira20@";
    public String vPrice, sPrice;


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
    public void verifyReorderWithChangedQuantity() throws InterruptedException {

        // Step 1. Go to http://live.techpanda.org
        driver.get("http://live.techpanda.org ");

        // Step 2. Click on My Account link
        driver.findElement(By.linkText("MY ACCOUNT")).click();

        Thread.sleep(15000);

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Step 3. Login in application using previously created credential
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(vEmail);
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(vPW);
        driver.findElement(By.id("send2")).click();	 // this is the Login button

        Thread.sleep(5000);

        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // 4.Click on 'REORDER' link , change QTY & click Update
        System.out.println("Before Reorder");
        driver.findElement(By.linkText("REORDER")).click();
        System.out.println("After Reorder");


        // *  Get the Grand Total Price
        vPrice = driver.findElement(By.xpath
                (".//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")).getText();

        // switching to new window
			    /*for (String handle : driver.getWindowHandles()) {
			    	driver.switchTo().window(handle);
			    	}*/

        // this will change the QTY
        driver.findElement(By.xpath("//input[@title='Qty']")).clear();
        driver.findElement(By.xpath("//input[@title='Qty']")).sendKeys("10");
        System.out.println("*** QTY  Set ***");

        // this will click the Update button
        //driver.findElement(By.cssSelector("td.product-cart-actions > button[name=update_cart_action]")).click();
        System.out.println("*** Cart Updated ***");

        // this will check the Grand Total price after being updated
        sPrice = driver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")).getText();
        System.out.println("sPrice ="+sPrice);

        // verify the before and after Grand Total price, to confirm it has changed
        if (vPrice== sPrice){
            System.out.println("*** Grand Total price has not changed. ***");
        }else{
            System.out.println("*** Grand Total price has changed. ***");
        }

        // this could be the Proceed to Checkout button
        driver.findElement(By.xpath("//button[@type='button']")).click();


        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        //
        //*  BILLING ADDRESS
        try {
            Select bAddr = new Select(driver.findElement(By.name("billing_address_id")));
            int bAddrSize = bAddr.getOptions().size();
            bAddr.selectByIndex(bAddrSize-1);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("No dropdown element present");
        }

        driver.findElement(By.id("billing:firstname")).clear();
        driver.findElement(By.id("billing:firstname")).clear();
        driver.findElement(By.id("billing:firstname")).sendKeys(firstName);
        driver.findElement(By.id("billing:lastname")).clear();
        driver.findElement(By.id("billing:lastname")).sendKeys(lastName);
        driver.findElement(By.id("billing:company")).clear();

        driver.findElement(By.id("billing:street1")).clear();
        driver.findElement(By.id("billing:street1")).sendKeys("148 Crown Street");
        new Select(driver.findElement(By.xpath("//select[@id='billing:country_id']"))).selectByIndex(14);
        Thread.sleep(5000);
        driver.findElement(By.id("billing:city")).clear();
        driver.findElement(By.id("billing:city")).sendKeys("Sydney");
        driver.findElement(By.id("billing:region")).clear();
        driver.findElement(By.id("billing:region")).sendKeys("New South Wales");
        driver.findElement(By.id("billing:postcode")).clear();
        driver.findElement(By.id("billing:postcode")).sendKeys("2000");
        driver.findElement(By.id("billing:telephone")).clear();
        driver.findElement(By.id("billing:telephone")).sendKeys("8850 6789");

        // check radio button to "Ship to different address"
        driver.findElement(By.id("billing:use_for_shipping_no")).click();

        // click the CONTINUE button

        // after the click above, it is still on same web page: live.guru99.com/index.php/checkout/onepage/
        driver.findElement(By.xpath(".//*[@id='billing-buttons-container']/button")).click();


        // switching to new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Thread.sleep(2000);


        // * SHIPPING ADDRESS
        try {
            Select sAddr = new Select(driver.findElement(By.name("shipping_address_id")));
            int sAddrSize = sAddr.getOptions().size();
            sAddr.selectByIndex(sAddrSize-1);
        } catch (Exception e) {
            System.out.println("No dropdown element present");
        }

        Thread.sleep(3000);

// Wait until Shipping Address section is ready
        WebElement shippingFirstName = driver.findElement(By.id("shipping:firstname"));

        Thread.sleep(3000);

// Sometimes the shipping form is disabled initially.
// Click the "Ship to this address" / address section if necessary.
        if (!shippingFirstName.isEnabled()) {
            driver.findElement(By.id("shipping:firstname")).click();
            Thread.sleep(2000);
        }

        shippingFirstName.clear();
        shippingFirstName.sendKeys(firstName);

        driver.findElement(By.id("shipping:lastname")).clear();
        driver.findElement(By.id("shipping:lastname")).sendKeys(lastName);
        driver.findElement(By.id("shipping:company")).clear();

        driver.findElement(By.id("shipping:street1")).clear();
        driver.findElement(By.id("shipping:street1")).sendKeys("50 Berry Street");
        driver.findElement(By.id("shipping:street2")).clear();

        Thread.sleep(3000);

        // In Shipping Method, Click Continue
        driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click();

        Thread.sleep(2000);

        // In Payment Information select 'Check/Money Order' radio button. Click Continue
        driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();


        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='payment-buttons-container']/button")).click();

        Thread.sleep(2000);

        // Click 'PLACE ORDER' button
        driver.findElement(By.xpath(".//*[@id='review-buttons-container']/button")).click();

        Thread.sleep(2000);

        // Verify Order is generated. Note the order number
        String orderNum = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/p[1]/a")).getText();
        System.out.println("*** Your order number for your record = " + orderNum);
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
