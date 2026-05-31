package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {
    private final ElementsCollection cartItem = $$x("//*[@data-test='inventory-item-name']");

    @Step("Получение имени первого товара в корзине")
    public String getFirstCartItemName() {
        return cartItem.first().getText();
    }
}
