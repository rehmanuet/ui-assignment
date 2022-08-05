package pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Constants.TIMEOUT;

/**
 * Implementation of the page layer for the Product Page
 */

public class ProductPage extends BasePage {
    By addToCartButton = By.cssSelector("[title='Add to cart']");
    By womenCategory = By.cssSelector("a[title='Women']");
    By products = By.cssSelector("[class='product-image-container']");
    By continueShoppingButton = By.cssSelector("[title='Continue shopping']");
    By cartButton = By.cssSelector("[title='View my shopping cart']");
    By cartProductCount = By.cssSelector("[class='ajax_cart_quantity']");
    By proceedCheckout = By.cssSelector("[class*='standard-checkout']");
    By processAddress = By.cssSelector("[name='processAddress']");
    By acceptTermCheckbox = By.cssSelector("[name='cgv']");
    By paymentTypeCheque = By.cssSelector("[class='cheque']");
    By confirmOrder = By.cssSelector("[id='cart_navigation'] button");
    By successOrderAlert = By.cssSelector("[class*='alert-success']");
    By backToOrder = By.cssSelector("[class='cart_navigation exclusive'] a");
    By latestOrder = By.cssSelector("[class^='first_item'] a[class='color-myaccount']");


    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void checkCartProductQuantity(String count) {
        waitForPageLoad();
        waitUntilVisible(cartProductCount);
        Assert.assertEquals(doGetText(cartProductCount), count);
    }

    public void proceedToCheckout() {
        waitForPageLoad();
        scrollToElement(proceedCheckout);
        click(proceedCheckout);
    }

    public void confirmAddress() {
        waitForPageLoad();
        scrollToElement(processAddress);
        click(processAddress);
    }

    public void confirmShipping() {
        waitForPageLoad();
        click(acceptTermCheckbox);
        scrollToElement(proceedCheckout);
        click(proceedCheckout);
    }

    public void selectPaymentMethod() {
        waitForPageLoad();
        sleep(TIMEOUT);
        scrollToElement(paymentTypeCheque);
        click(paymentTypeCheque);
    }

    public void confirmOrder() {
        waitForPageLoad();
        scrollToElement(confirmOrder);
        click(confirmOrder);
    }

    public void getCartSummary() {
        waitForPageLoad();
        scrollToElement(cartButton);
        click(cartButton);
    }

    public void checkIfOrderCompleted(String message) {
        waitForPageLoad();
        waitUntilVisible(successOrderAlert);
        Assert.assertEquals(doGetText(successOrderAlert), message);

    }
    public void checkOrderHistory() {
        click(backToOrder);
        waitForPageLoad();
        Assert.assertTrue(isVisible(latestOrder));
    }

    public void addProduct() {
        scrollToElement(products);
        hover(products);
        hover(products);
        click(addToCartButton);
        waitUntilVisible(continueShoppingButton);
        click(continueShoppingButton);
        scrollToElement(cartButton);
        scrollToElement(products);
    }


    public void selectCategory() {
        waitForPageLoad();
        waitUntilVisible(womenCategory);
        click(womenCategory);
        waitUntilVisible(products);
        scrollToElement(products);
    }
}

