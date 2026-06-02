package ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverConditions;
import io.qameta.allure.Step;

public class CheckoutStepTwoPage {

    @Step("Проверить что открылась страница покупки заказа")
    public CheckoutStepTwoPage shouldBeOnCheckoutPage() {
        Selenide.webdriver().shouldHave(WebDriverConditions
                .urlContaining("/checkout-step-two.html"));
        return this;
    }
}
