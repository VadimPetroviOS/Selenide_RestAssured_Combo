package ui.tests.inventoryPage;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ui.config.Config;
import ui.pages.Pages;
import ui.settings.BaseSettings;

import java.util.Comparator;
import java.util.stream.Stream;

public class InventoryPagesTest extends BaseSettings {

    static Stream<Arguments> sortProvider() {
        return Stream.of(
                Arguments.of(Config.sortAZ, Comparator.naturalOrder()),
                Arguments.of(Config.sortZA, Comparator.reverseOrder())
        );
    }

    static Stream<Arguments> priceSortProvider() {
        return Stream.of(
                Arguments.of(Config.sortLohi, Comparator.<Double>naturalOrder()),
                Arguments.of(Config.sortHilo, Comparator.<Double>reverseOrder())
        );
    }

    static Stream<Arguments> cartCountProvider() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(3),
                Arguments.of(6)
        );
    }

    @BeforeEach
    public void authorize() {
        Pages.inventoryList.open();
    }

    @Test
    @Story("Сортировка по умолчанию")
    public void assertDefaultSort() {
        Pages.inventoryList
                .assertDefaultSortIsAZ()
                .assertNamesSorted(Comparator.naturalOrder());
    }

    @ParameterizedTest
    @MethodSource("sortProvider")
    @Story("Сортировка товаров по названию")
    public void sortByName(String sortValue, Comparator<String> comparator) {
        Pages.inventoryList
                .selectSort(sortValue)
                .assertNamesSorted(comparator);
    }

    @ParameterizedTest
    @MethodSource("priceSortProvider")
    @Story("Сортировка товаров по цене")
    public void sortByPrice(String sortValue, Comparator<Double> comparator) {
        Pages.inventoryList
                .selectSort(sortValue)
                .assertPricesSorted(comparator);
    }

    @Test
    @Story("Счётчик корзины")
    public void cartBadgeAppearsOnAddItem() {
        Pages.inventoryList
                .addItemsToCart(1)        // добавить первый товар
                .assertCartBadge(1);     // счётчик = 1
    }

    @ParameterizedTest
    @MethodSource("cartCountProvider")
    @Story("Счётчик корзины")
    public void cartBadgeIncrementsOnAddItem(int itemsToAdd) {
        Pages.inventoryList
                .addItemsToCart(itemsToAdd)
                .assertCartBadge(itemsToAdd);
    }

    @Test
    @Story("Отображение товара в корзине")
    void testAddItemsToCartAndVerifyDisplay() {
        String expectedName = Pages.inventoryList.getFirstItemName(); // запомнили имя

        String actualName = Pages.inventoryList
                .addItemsToCart(1)
                .assertCartBadge(1)
                .openCart()
                .getFirstCartItemName();

        Assertions.assertEquals(expectedName, actualName, "Имя товара в корзине не совпадает");
    }
}
