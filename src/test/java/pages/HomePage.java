package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Implementation of the page layer for the Home Page
 */

public class HomePage extends BasePage {

    By logo = By.className("logo");
    By signInButton = By.className("login");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void checkLogo() {
        Assert.assertTrue(isVisible(logo), "Logo is not displayed");
    }

    public void clickSignInButton() {
        checkLogo();
        Assert.assertTrue(isVisible(signInButton), "Username field is not displayed");
        click(signInButton);
    }
}

