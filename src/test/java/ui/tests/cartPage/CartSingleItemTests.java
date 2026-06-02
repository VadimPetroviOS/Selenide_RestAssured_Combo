package ui.tests.cartPage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.pages.Pages;
import ui.settings.BaseSettings;

public class CartSingleItemTests extends BaseSettings {

    @BeforeEach
    public void openCartWithOneItem() {
        Pages.cart.open(4);
    }

    @Test
    @Tag("regression")
    void deleteCartItem() {
        Pages.cart.cartIsEmptyAfterDeletingOnlyItem();
    }

    @Test
    @Tag("regression")
    void cartBadgeDisappearsAfterRemovingLastItem() {
        Pages.cart.cartBadgeShouldHaveCount(1)
                .cartIsEmptyAfterDeletingOnlyItem()
                .cartBadgeShouldBeHidden();
    }

    @Test
    @Tag("smoke")
    void continueShoppingNavigatesToInventory() {
        Pages.cart.clickContinueShopping()
                .shouldBeOnInventoryPage();
    }

    @Test
    @Tag("regression")
    void checkoutNavigatesToCheckoutPage() {
        Pages.cart.clickCheckout()
                .shouldBeOnCheckoutPage();
    }
}
