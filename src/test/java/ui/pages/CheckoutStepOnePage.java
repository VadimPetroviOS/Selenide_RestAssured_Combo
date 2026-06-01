package ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverConditions;
import io.qameta.allure.Step;

public class CheckoutStepOnePage {
    @Step("Проверить что открылась страница оформления заказа")
    public CheckoutStepOnePage shouldBeOnCheckoutPage() {
        Selenide.webdriver().shouldHave(WebDriverConditions
                .urlContaining("/checkout-step-one.html"));
        return this;
    }
}
