package tests;

import com.relevantcodes.extentreports.LogStatus;
import extent.ExtentTestManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;

import java.lang.reflect.InvocationTargetException;

import static utils.Constants.VALID_PASSWORD;
import static utils.Constants.VALID_EMAIL;

/**
 * Implementation of the test cases for the Checkout/Order History Page
 */

public class VerifyProductCheckout extends BaseTest {

    @Test
    public void tc007_verifyAddToCart() {
        ExtentTestManager.startTest("TC007-VerifyAddToCart",
                "Verify If products are being added to cart");
        try {
            page.getInstance(HomePage.class).clickSignInButton();
            page.getInstance(LoginPage.class).doLogin(VALID_EMAIL, VALID_PASSWORD);
            page.getInstance(LoginPage.class).checkLoginSuccessfully();
            page.getInstance(ProductPage.class).selectCategory();
            page.getInstance(ProductPage.class).addProduct();
//            page.getInstance(ProductPage.class).addProduct();
            page.getInstance(ProductPage.class).getCartSummary();
            page.getInstance(ProductPage.class).checkCartProductQuantity("1");
            page.getInstance(LoginPage.class).signOut();

            ExtentTestManager.getTest().log(LogStatus.PASS, "Products are added successfully");
        } catch (AssertionError | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException error) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, error.getMessage());
            Assert.fail(error.getMessage());
        }
    }

    //
    @Test
    public void tc001_verifyCheckOutAndHistory() {
        ExtentTestManager.startTest("TC008-VerifyCheckOutAndHistory",
                "Verification of checkout and order history");
        try {
            page.getInstance(HomePage.class).clickSignInButton();
            page.getInstance(LoginPage.class).doLogin(VALID_EMAIL, VALID_PASSWORD);
            page.getInstance(LoginPage.class).checkLoginSuccessfully();
            page.getInstance(ProductPage.class).selectCategory();
            page.getInstance(ProductPage.class).addProduct();
            page.getInstance(ProductPage.class).getCartSummary();
            page.getInstance(ProductPage.class).checkCartProductQuantity("1");
            page.getInstance(ProductPage.class).proceedToCheckout();
            page.getInstance(ProductPage.class).confirmAddress();
            page.getInstance(ProductPage.class).confirmShipping();
            page.getInstance(ProductPage.class).selectPaymentMethod();
            page.getInstance(ProductPage.class).confirmOrder();
            page.getInstance(ProductPage.class).checkIfOrderCompleted("Your order on My Store is complete.");
            page.getInstance(ProductPage.class).checkOrderHistory();
            page.getInstance(LoginPage.class).signOut();


            ExtentTestManager.getTest().log(LogStatus.PASS, "Product checkout successfully");

        } catch (AssertionError | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException error) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, error.getMessage());
            Assert.fail(error.getMessage());
        }
    }
}
