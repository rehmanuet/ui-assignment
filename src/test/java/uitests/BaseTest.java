package uitests;

import extent.ExtentTestManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.Page;

import java.time.Duration;

import static utils.Constants.*;

/**
 * Implementation of the Base test Class for the generic methods used for test cases
 *
 * @author Abdur.Rehman
 */

@Listeners(TestListener.class)
public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public Page page;

    @BeforeMethod
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        page = new Page(driver, wait);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        ExtentTestManager.flush();
    }
}
