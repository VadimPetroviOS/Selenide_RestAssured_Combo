package ui.tests.cartPage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.Pages;
import ui.settings.BaseSettings;

public class CartMultipleItemsTests extends BaseSettings {

    @BeforeEach
    public void openCartWithMultipleItems() {
        Pages.cart.open(0, 1, 2, 3);
    }

    @Test
    void removeOneItemFromMultiple() {
        Pages.cart.cartShouldHaveSize(4)
                .removeFirstItem()
                .cartShouldHaveSize(3)
                .cartBadgeShouldHaveCount(3);
    }
}
