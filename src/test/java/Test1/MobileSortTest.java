package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class MobileSortTest {

    public static void main(String[] args) {

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\parsian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // 1. Go to website
        driver.get("http://live.techpanda.org/");

        // 2. Verify title of home page
        String homeTitle = driver.getTitle();
        System.out.println(homeTitle);

        // Verify "THIS IS DEMO SITE"
        String pageText = driver.findElement(By.tagName("body")).getText();

        if (pageText.contains("THIS IS DEMO SITE")) {
            System.out.println("THIS IS DEMO SITE is displayed");
        }

        // 3. Click MOBILE
        driver.findElement(By.linkText("MOBILE")).click();

        // 4. Verify title of mobile page
        String mobileTitle = driver.getTitle();
        System.out.println(mobileTitle);

        // 5. Select SORT BY = Name
        WebElement sortDropdown =
                driver.findElement(By.cssSelector("select[title='Sort By']"));

        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Name");

        // 6. Verify products are sorted by name
        List<WebElement> products =
                driver.findElements(By.cssSelector(".product-name"));

        for (WebElement product : products) {
            System.out.println(product.getText());
        }

        driver.quit();
    }
}