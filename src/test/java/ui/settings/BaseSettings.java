package ui.settings;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseSettings {
    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
//        Configuration.holdBrowserOpen = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
            Selenide.closeWebDriver();
    }
}
