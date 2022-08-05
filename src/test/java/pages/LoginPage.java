package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Implementation of the page layer for the Login Page
 */

public class LoginPage extends BasePage {

    By loginFormHeading = By.cssSelector("[id='login_form'] [class='page-subheading']");
    By loginEmail = By.cssSelector("[id='login_form'] [id='email']");
    By loginPassword = By.cssSelector("[id='login_form'] [id='passwd']");
    By submit = By.cssSelector("[id='SubmitLogin']");
    By authenticationFailText = By.cssSelector("[class*='alert-danger'] li");
    By infoAccountText = By.cssSelector("[class='info-account']");
    By signOut = By.cssSelector("[class='logout']");


    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void checkEmailField() {
        Assert.assertTrue(isVisible(loginEmail), "Email field is not displayed");
    }

    public void checkPasswordField() {
        Assert.assertTrue(isVisible(loginPassword), "Password field is not displayed");
    }

    public void checkSignInButton() {
        Assert.assertTrue(isVisible(submit), "Sign-in button is not displayed");
    }

    public void signOut() {
        scrollToElement(signOut);
        click(signOut);
    }

    public void doLogin(String email, String pass) {
        waitUntilVisible(loginFormHeading);
        checkEmailField();
        doSendKeys(loginEmail, email);

        checkPasswordField();
        doSendKeys(loginPassword, pass);

        checkSignInButton();
        click(submit);
    }

    public void checkLoginSuccessfully() {
        waitUntilVisible(infoAccountText);
        Assert.assertEquals(doGetText(infoAccountText),
                "Welcome to your account. Here you can manage all of your personal information and orders.");
    }

    public void checkLoginError(String message) {
        Assert.assertTrue(isVisible(authenticationFailText), "Error message is not displayed");
        Assert.assertEquals(doGetText(authenticationFailText), message);
    }
}

