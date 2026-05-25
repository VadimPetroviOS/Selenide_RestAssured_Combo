package ui.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryListPage extends BasePage {

    public InventoryListPage() {
        URL += "/inventory.html";
    }

    @Step("Получаем количество элементов на странице")
    public InventoryListPage assertInventoryItemsCount(int num) {
        $$(".inventory_item").shouldHave(size(num));
        return this;
    }
}
