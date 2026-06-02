package ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverConditions;
import io.qameta.allure.Step;
import ui.config.Config;
import ui.helpers.CookieHelper;
import ui.helpers.LocalStorageHelper;
import ui.models.CheckoutInfo;
import ui.tests.checkoutStepOnePage.CheckoutStepOnePageTests;

import static com.codeborne.selenide.Selenide.$x;

public class CheckoutStepOnePage extends BasePage {

    private final SelenideElement firstNameInput = $x("//*[@data-test='firstName']");
    private final SelenideElement lastNameInput = $x("//*[@data-test='lastName']");
    private final SelenideElement postalCodeInput = $x("//*[@data-test='postalCode']");
    private final SelenideElement continueButton = $x("//*[@data-test='continue']");

    CheckoutStepOnePage() {
        URL += "/checkout-step-one.html";
    }

    public void open(int... itemIds) {
        Selenide.open(BASE_URL);
        CookieHelper.setSession(Config.correctUsername);
        LocalStorageHelper.setCart(itemIds);
        Selenide.open(URL);
    }

    @Step("Проверить что открылась страница оформления заказа")
    public CheckoutStepOnePage shouldBeOnCheckoutPage() {
        Selenide.webdriver().shouldHave(WebDriverConditions
                .urlContaining("/checkout-step-one.html"));
        return this;
    }

    public CheckoutStepOnePage enterFirstName(String firstname) {
        firstNameInput.setValue(firstname);
        return this;
    }

    public CheckoutStepOnePage enterLastName(String lastname) {
        lastNameInput.setValue(lastname);
        return this;
    }

    public CheckoutStepOnePage enterPostalCode(String postalCode) {
        postalCodeInput.setValue(postalCode);
        return this;
    }

    public CheckoutStepTwoPage clickContinue() {
        continueButton.click();
        return Pages.stepTwo;
    }

    public CheckoutStepTwoPage fillForm(CheckoutInfo info) {
        return enterFirstName(info.getFirstName())
                .enterLastName(info.getLastName())
                .enterPostalCode(info.getPostalCode())
                .clickContinue();
    }

}

