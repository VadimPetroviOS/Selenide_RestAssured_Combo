package ui.tests.checkoutStepOnePage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.config.Config;
import ui.models.CheckoutInfo;
import ui.models.UserCredentials;
import ui.pages.Pages;
import ui.settings.BaseSettings;

public class CheckoutStepOnePageTests extends BaseSettings {

    private CheckoutInfo info;

    @BeforeEach
    void setup() {
        info = new CheckoutInfo();
        info.setFirstName(Config.firstName);
        info.setLastName(Config.lastName);
        info.setPostalCode(Config.postalCode);
        Pages.stepOne.open(4);
    }

    @Test
    void shouldBeOnCheckoutStepTwoPage() {
        Pages.stepOne
                .fillForm(info)
                .shouldBeOnCheckoutPage();
    }
}
