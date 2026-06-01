package ui.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import ui.config.Config;
import ui.helpers.CookieHelper;
import ui.helpers.LocalStorageHelper;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends BasePage {
    private final ElementsCollection cartItem = $$x("//*[@data-test='inventory-item-name']");
    private final ElementsCollection removeButtons = $$x("//button[contains(@data-test,'remove')]");
    private final ElementsCollection inventoryItems = $$x("//*[@data-test='inventory-item']");
    private final SelenideElement cartBadge = $x("//*[@data-test='shopping-cart-badge']");
    private final SelenideElement continueShoppingButton = $x("//*[@data-test='continue-shopping']");
    private final SelenideElement checkoutButton = $x("//*[@data-test='checkout']");


    public CartPage() {
        URL += "/cart.html";
    }

    public void open(int... itemIds) {
        Selenide.open(BASE_URL);
        CookieHelper.setSession(Config.correctUsername);
        LocalStorageHelper.setCart(itemIds);
        Selenide.open(URL);
    }

    @Step("Получение имени первого товара в корзине")
    public String getFirstCartItemName() {
        return cartItem.first().getText();
    }

    @Step("Удаление товара из корзины")
    public CartPage removeFirstItem() {
        removeButtons.first().click();
        return this;
    }

    @Step("Поле с товарами пустое")
    public CartPage shouldBeEmpty() {
        inventoryItems.shouldHave(CollectionCondition.size(0));
        return this;
    }

    @Step("Удалить товар и проверить, что корзина пуста")
    public CartPage cartIsEmptyAfterDeletingOnlyItem() {
        return removeFirstItem()
                .shouldBeEmpty();
    }

    @Step("Счётчик корзины равен {count}")
    public CartPage cartBadgeShouldHaveCount(int count) {
        cartBadge.shouldHave(Condition.text(String.valueOf(count)));
        return this;
    }

    @Step("Счётчик корзины отсутствует")
    public CartPage cartBadgeShouldBeHidden() {
        cartBadge.shouldNot(Condition.exist);
        return this;
    }

    @Step("Количество товаров в корзине равно {count}")
    public CartPage cartShouldHaveSize(int count) {
        inventoryItems.shouldHave(CollectionCondition.size(count));
        return this;
    }

    @Step("Нажать Continue Shopping")
    public InventoryListPage clickContinueShopping() {
        continueShoppingButton.click();
        return Pages.inventoryList;
    }

    @Step("Нажать Checkout")
    public CheckoutStepOnePage clickCheckout() {
        checkoutButton.click();
        return Pages.stepOne;
    }

}
