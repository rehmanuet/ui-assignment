package tests;

import com.relevantcodes.extentreports.LogStatus;
import extent.ExtentTestManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;

import java.lang.reflect.InvocationTargetException;

/**
 * Implementation of the test cases for the Inventory Page
 */

public class VerifyAccountRegistration extends BaseTest {

        @Test
    public void tc001_verifyNewAccountRegistration() {
        ExtentTestManager.startTest("TC001-VerifyAccountRegistration",
                "Verification of new account registration");
        try {
            page.getInstance(HomePage.class).clickSignInButton();
            page.getInstance(RegistrationPage.class).checkRegistrationLandingPage();
            page.getInstance(RegistrationPage.class).submitEmail();
            page.getInstance(RegistrationPage.class).register();
            page.getInstance(RegistrationPage.class).checkRegisterSuccessfully();
            page.getInstance(RegistrationPage.class).signOut();

            ExtentTestManager.getTest().log(LogStatus.PASS, "New account created successfully");
        } catch (AssertionError | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException error) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, error.getMessage());
            Assert.fail(error.getMessage());
        }
    }

    @Test
    public void tc002_verifyInvalidEmailRegistration() {
        ExtentTestManager.startTest("TC001-VerifyAccountRegistration",
                "Verification of account registration with invalid email format");
        try {
            page.getInstance(HomePage.class).clickSignInButton();
            page.getInstance(RegistrationPage.class).checkRegistrationLandingPage();
            page.getInstance(RegistrationPage.class).submitEmail("invalid_email[a]gmailcom");
            page.getInstance(RegistrationPage.class).checkErrorAlert("Invalid email address.");

            ExtentTestManager.getTest().log(LogStatus.PASS, "Alert shown successfully");
        } catch (AssertionError | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException error) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, error.getMessage());
            Assert.fail(error.getMessage());
        }
    }


    @Test
    public void tc003_verifyAlreadyRegisteredEmail() {
        ExtentTestManager.startTest("TC001-VerifyAccountRegistration",
                "Verification of new account registration with already registered email");
        try {
            page.getInstance(HomePage.class).clickSignInButton();
            page.getInstance(RegistrationPage.class).checkRegistrationLandingPage();
            page.getInstance(RegistrationPage.class).submitEmail("test@gmail.com");
            page.getInstance(RegistrationPage.class)
                    .checkErrorAlert("An account using this email address has already been registered. Please enter a valid password or request a new one.");

            ExtentTestManager.getTest().log(LogStatus.PASS, "Alert shown successfully");
        } catch (AssertionError | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException error) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, error.getMessage());
            Assert.fail(error.getMessage());
        }
    }
}