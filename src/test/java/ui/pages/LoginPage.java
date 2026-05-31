package ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import ui.models.UserCredentials;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

    private final SelenideElement usernameInput = $("#user-name");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $("[data-test='error']");

    public LoginPage() {
        URL += "/";
    }

    @Step("Открываем главную страницу")
    public LoginPage open() {
        Selenide.open(URL);
        return this;
    }

    @Step("Заполняем логин {s}")
    public LoginPage fillUsernameInput(String s) {
        usernameInput.setValue(s);
        return this;
    }

    @Step("Заполняем пароль {s}")
    public LoginPage fillPasswordInput(String s) {
        passwordInput.setValue(s);
        return this;
    }

    @Step("Кликаем по кнопке логина")
    public InventoryListPage clickLoginButton() {
        loginButton.click();
        return Pages.inventoryList;
    }

    @Step("Кликаем по кнопке логина (ожидаем ошибку)")
    public LoginPage clickLoginButtonExpectingError() {
        loginButton.click();
        return this;
    }

    @Step("Логин успешен: {creds.username}")
    public InventoryListPage loginUserSuccess(UserCredentials creds) {
        return fillUsernameInput(creds.getUsername())
                .fillPasswordInput(creds.getPassword())
                .clickLoginButton();
    }

    @Step("Логин неуспешен: {creds.username}")
    public LoginPage loginUserFailed(UserCredentials creds) {
        return fillUsernameInput(creds.getUsername())
                .fillPasswordInput(creds.getPassword())
                .clickLoginButtonExpectingError();
    }

    @Step("Проверяем наличие сообщения об ошибке")
    public LoginPage assertErrorMessagePresent() {
        errorMessage.shouldBe(visible);
        return this;
    }
}
