package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.*;
import java.time.Duration;
import javax.mail.MessagingException;

public class Test10 {

    WebDriver driver;
    private String vUsername = "user01";
    private String vPassword = "user001";

    @BeforeTest
    public void setUp() {

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\parsian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void verifyExportedOrdersFileCanBeSentByEmail()throws Exception {
        driver.get("http://live.techpanda.org/index.php/backendlogin");
        //  2. Login the credentials provided

        driver.findElement(By.xpath("//input[@id = 'username']")).sendKeys(vUsername);

        driver.findElement(By.xpath("//input[@id = 'login']")).sendKeys(vPassword);
        driver.findElement(By.xpath("//input[@value = 'Login']")).click();

        try {Thread.sleep(5000);}catch(Exception e){};

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        //  3a. Go to Sales-> Orders menu                                                    // click the Sales tab

        // click the Sales tab
        driver.findElement(By.linkText("Sales")).click();


        try {Thread.sleep(5000);}catch(Exception e){};

        //  3b. Go to Sales-> Orders menu

        driver.findElement(By.linkText("Orders")).click();                                    // click the Orders sub tab


        try {Thread.sleep(5000);}catch(Exception e){};


        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        try {Thread.sleep(5000);}catch(Exception e){};

        //  4. Select 'CSV' in Export To dropdown and click Export button.

        driver.findElement(By.xpath("//button[@title = 'Export']")).click();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }


        String filePath = System.getProperty("user.home")+"/Downloads/orders.csv";
        try {
            EmailUtil.emailUtil(filePath);
            //Mail.mail(filePath);
        } catch (MessagingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 5. Read downloaded file and display the Heading and all the Order details in the console windows
        File f = new File(filePath);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line!=null){
                System.out.println(line);
                line = br.readLine();
            }
            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
