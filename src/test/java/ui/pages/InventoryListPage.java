package ui.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import ui.config.Config;
import ui.helpers.CookieHelper;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class InventoryListPage extends BasePage {

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
        $(".product_sort_container").shouldHave(value("az"));
        return this;
    }

    @Step("Получаем количество элементов на странице")
    public InventoryListPage assertInventoryItemsCount(int num) {
        $$(".inventory_item").shouldHave(size(num));
        return this;
    }
}
