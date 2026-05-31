package ui.tests.mainPage;

import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.config.Config;
import ui.models.UserCredentials;
import ui.pages.Pages;
import ui.settings.BaseSettings;

public class MainPagesTests extends BaseSettings {

    private static final int GOODS_COUNT = 6;
    private UserCredentials creds;

    @BeforeEach
    void setup() {
        creds = new UserCredentials();
        creds.setUsername(Config.correctUsername);
        creds.setPassword(Config.correctPassword);
        Pages.login.open();
    }

    @Test
    @Story("Успешный логин")
    public void testCorrectLogin() {
        Pages.login
                .loginUserSuccess(creds)
                .assertInventoryItemsCount(GOODS_COUNT);
    }

    @Test
    @Story("Неуспешный логин")
    public void testIncorrectLogin() {
        creds.setUsername(Config.incorrectUsername);
        Pages.login
                .loginUserFailed(creds)
                .assertErrorMessagePresent();
    }
}