package ui.helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import ui.config.Config;

public class CookieHelper {
    public static void setSession(String username) {
        var manage = WebDriverRunner.getWebDriver().manage();
        manage.deleteAllCookies();
        manage.addCookie(new Cookie.Builder("session-username", username)
                .path("/")
                .build());
    }
}