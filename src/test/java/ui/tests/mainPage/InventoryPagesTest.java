package ui.tests.mainPage;

import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.config.Config;
import ui.pages.Pages;
import ui.settings.BaseSettings;

public class InventoryPagesTest extends BaseSettings {

    @BeforeEach
    public void authorize() {
        Pages.inventoryList.open();
    }

    @Test
    @Story("Сортировка по умолчанию")
    public void getDefaultSort() {
        Pages.inventoryList
                .assertDefaultSortIsAZ();
    }
}
