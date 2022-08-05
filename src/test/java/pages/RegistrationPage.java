package pages;


import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Implementation of the page layer for the Registration Page
 */

public class RegistrationPage extends BasePage {
    @Getter
    @Setter
    String emailAddr;

    Faker faker = new Faker();
    By pageHeading = By.className("page-heading");
    By createAccountText = By.cssSelector("[id='create-account_form'] [class='page-subheading']");
    By emailTextBox = By.cssSelector("[id='email_create']");
    By submitButton = By.cssSelector("[id='SubmitCreate']");
    By invalidEmailErrorText = By.cssSelector("[id='create_account_error'] li");
    By mrTitle = By.cssSelector("[for='id_gender1']");
    By mrsTitle = By.cssSelector("[for='id_gender2']");
    By firstName = By.cssSelector("[id='customer_firstname']");
    By lastName = By.cssSelector("[id='customer_lastname']");
    By email = By.cssSelector("[id='email']");
    By password = By.cssSelector("[id='passwd']");

    By day = By.cssSelector("[id='days']");
    By month = By.cssSelector("[id='months']");
    By year = By.cssSelector("[id='years']");
    By addressCompany = By.cssSelector("[id='company']");
    By address = By.cssSelector("[id='address1']");
    By addressPostCode = By.cssSelector("[id='postcode']");
    By addressCity = By.cssSelector("[id='city']");
    By addressState = By.cssSelector("[id='id_state']");
    By addressPhoneNumber = By.cssSelector("[id='phone_mobile']");
    By register = By.cssSelector("[id='submitAccount']");
    By signOutButton = By.cssSelector("[class='logout']");
    By infoAccountText = By.cssSelector("[class='info-account']");

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void checkRegistrationLandingPage() {
        Assert.assertTrue(isVisible(pageHeading), "page heading is not displayed");
        Assert.assertTrue(isVisible(createAccountText), "page heading is not displayed");
        Assert.assertEquals(doGetText(pageHeading), "AUTHENTICATION");
        Assert.assertEquals(doGetText(createAccountText), "CREATE AN ACCOUNT");
    }

    public void submitEmail() {
        setEmailAddr(faker.internet().emailAddress());
        submitEmail(getEmailAddr());
    }

    public void submitEmail(String email) {
        setEmailAddr(email);
        Assert.assertTrue(isVisible(emailTextBox), "email field is not displayed");
        doSendKeys(emailTextBox, getEmailAddr());
        Assert.assertTrue(isVisible(submitButton), "email field is not displayed");
        click(submitButton);
    }

    public void register() {
        waitUntilVisible(mrTitle);
        click(mrTitle);
        doSendKeys(firstName, faker.name().firstName());
        doSendKeys(lastName, faker.name().lastName());
        scrollToElement(password);

        selectByValue(day, "10");
        selectByValue(month, "10");
        selectByValue(year, "2001");

        doSendKeys(password, "TestAutomation12");
        scrollToElement(addressCompany);
        doSendKeys(addressCompany, "Andela");
        scrollToElement(address);
        doSendKeys(address, faker.address().fullAddress());

        doSendKeys(addressCity, "Columbus");
        selectByText(addressState, "Kentucky");

        scrollToElement(addressPostCode);
        Assert.assertTrue(isVisible(addressPostCode), "year text box is not displayed");
        doSendKeys(addressPostCode, "00000");
        Assert.assertTrue(isVisible(addressPhoneNumber), "year text box is not displayed");
        doSendKeys(addressPhoneNumber, faker.phoneNumber().cellPhone());
        click(register);
    }


    public void checkRegisterSuccessfully() {
        waitUntilVisible(infoAccountText);
        Assert.assertEquals(doGetText(infoAccountText),
                "Welcome to your account. Here you can manage all of your personal information and orders.");
    }
    public void signOut() {
        Assert.assertTrue(isVisible(signOutButton), "Not Successfully Logged-in");
        click(signOutButton);
        waitUntilVisible(createAccountText);
        Assert.assertTrue(isVisible(createAccountText), "Not Successfully Logged-in");

    }
    public void checkErrorAlert(String message) {
        Assert.assertTrue(isVisible(invalidEmailErrorText), "page heading is not displayed");
        Assert.assertEquals(doGetText(invalidEmailErrorText), message);
    }
}

