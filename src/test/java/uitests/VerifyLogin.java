package uitests;

import com.relevantcodes.extentreports.LogStatus;
import extent.ExtentTestManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.lang.reflect.InvocationTargetException;

import static utils.Constants.*;

/**
 * Implementation of the test cases for the Inventory Page
 */

public class VerifyLogin extends BaseTest {

    @Test
    public void tc004_verifyValidLogin() {
        ExtentTestManager.startTest("TC004-VerifyValidLogin",
                "Verification of login with valid credentials");
        try {
            page.getInstance(HomePage.class).clickSignInButton();
            page.getInstance(LoginPage.class).doLogin(VALID_EMAIL, VALID_PASSWORD);
            page.getInstance(LoginPage.class).checkLoginSuccessfully();
            page.getInstance(LoginPage.class).signOut();
            ExtentTestManager.getTest().log(LogStatus.PASS, "Login successfully");
        } catch (AssertionError | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException error) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, error.getMessage());
            Assert.fail(error.getMessage());
        }
    }

    @Test
    public void tc005_verifyErrorOnInvalidCredentialsLogin() {
        ExtentTestManager.startTest("TC005-VerifyErrorOnInvalidCredentialsLogin",
                "Verification of invalid login error message");
        try {
            page.getInstance(HomePage.class).clickSignInButton();
            page.getInstance(LoginPage.class).doLogin(INVALID_EMAIL, INVALID_PASSWORD);
            page.getInstance(LoginPage.class).checkLoginError("Authentication failed.");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Error Message shown successfully on invalid credential");
        } catch (AssertionError | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException error) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, error.getMessage());
            Assert.fail(error.getMessage());
        }
    }

    @Test
    public void tc006_verifyErrorOnEmptyPasswordLogin() {
        ExtentTestManager.startTest("TC006-VerifyErrorOnEmptyPasswordLogin",
                "Verification of empty password error message");
        try {
            page.getInstance(HomePage.class).clickSignInButton();
            page.getInstance(LoginPage.class).doLogin(INVALID_EMAIL, "");
            page.getInstance(LoginPage.class).checkLoginError("Password is required.");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Error Message shown successfully on empty password");
        } catch (AssertionError | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException error) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, error.getMessage());
            Assert.fail(error.getMessage());
        }
    }
}