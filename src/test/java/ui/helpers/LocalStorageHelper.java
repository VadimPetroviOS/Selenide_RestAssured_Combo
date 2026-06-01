package ui.helpers;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class LocalStorageHelper {

    public static void setCart(int... itemIds) {
        String ids = Arrays.stream(itemIds)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));

        executeJavaScript(
                "localStorage.setItem('cart-contents', '[" + ids + "]')"
        );
    }
}
