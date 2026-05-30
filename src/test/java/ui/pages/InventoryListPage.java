package ui.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ui.config.Config;
import ui.helpers.CookieHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;

public class InventoryListPage extends BasePage {

    private final SelenideElement inventoryList =
            $x("//div[@class='inventory_list']");
    private final ElementsCollection inventoryItemCount =
            inventoryList.$$x(".//div[@class='inventory_item']");
    private final ElementsCollection inventoryItemPrices =
            inventoryList.$$x(".//div[@data-test='inventory-item-price']");

    private final SelenideElement sortDropdown = $x("//*[@data-test='product-sort-container']");
    private final SelenideElement azSortElement =
            sortDropdown.$x("./option[@value='az']");


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

    @Step("Выбрать сортировку A→Z")
    public InventoryListPage selectSort(String value) {
        sortDropdown.selectOptionByValue(value);
        return this;
    }

    @Step("Проверяем сортировку товаров")
    public InventoryListPage assertNamesSorted(Comparator<String> comparator) {
        List<String> actual = inventoryItemCount.texts();
        List<String> sorted = actual.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        Assertions.assertEquals(sorted, actual, "Товары отсортированы неверно");
        return this;
    }

    @Step("Проверяем сортировку товаров")
    public InventoryListPage assertPricesSorted(Comparator<Double> comparator) {
        List<Double> actual = inventoryItemPrices.texts()
                .stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sorted = actual.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        Assertions.assertEquals(sorted, actual, "Товары отсортированы неверно");
        return this;
    }
}
