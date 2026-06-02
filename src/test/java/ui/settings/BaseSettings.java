package ui.settings;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseSettings {
    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = false;
        SelenideLogger.addListener("allure", new AllureSelenide());

        // для CI
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}