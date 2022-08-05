package pages;

import com.relevantcodes.extentreports.LogStatus;
import extent.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Implementation of the Base Page Class for the wrapper methods used in test cases
 *
 * @author Abdur.Rehman
 */

public class BasePage extends Page {
    public BasePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void doSendKeys(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    public void scrollToElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForPageLoad() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until((ExpectedCondition<Boolean>) wd -> {
            assert wd != null;
            return ((JavascriptExecutor) wd)
                    .executeScript("return document.readyState;")
                    .equals("complete");
        });
    }

    public String doGetText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void selectByValue(By locator, String value) {

        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    public void selectByText(By locator, String text) {

        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    public boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean displayedStatus = driver.findElement(locator).isDisplayed();
            ExtentTestManager.getTest().log(LogStatus.INFO, locator.toString() + " : " + displayedStatus);
            return displayedStatus;
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false;
        }

    }

    public void hover(By locator) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(locator)).build().perform();
    }

}