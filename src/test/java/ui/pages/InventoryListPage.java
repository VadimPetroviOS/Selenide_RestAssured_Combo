package ui.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import ui.config.Config;
import ui.helpers.CookieHelper;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;

public class InventoryListPage extends BasePage {

    private final ElementsCollection inventoryItemCount =
            $$(By.xpath("//div[@class='inventory_list']/div[@class='inventory_item']"));
    private final SelenideElement azSortElement = $x("//*[@class='select_container']/select/option[@value='az']");

    public InventoryListPage() {
        URL += "/inventory.html";
    }

    public void open() {
        Selenide.open(BASE_URL);
        CookieHelper.setSession(Config.correctUsername);
        Selenide.open(URL);
    }

    @Step("Проверяем сортировку по умолчанию: Name (A to Z)")
    public InventoryListPage assertDefaultSortIsAZ() {
        azSortElement.shouldHave(value("az"));
        return this;
    }

    @Step("Получаем количество элементов на странице")
    public InventoryListPage assertInventoryItemsCount(int num) {
        inventoryItemCount.shouldHave(size(num));
        return this;
    }
}
