package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;


public class MobileTests extends TestBase {

    @Test
    @DisplayName("Успешный поиск по слову «Appium»")
    void successfulSearchTest() {
        step("Ввести «Appium» в поисковую строку", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });

        step("Проверить найденный контент", () -> {
            $(id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(text("Appium"));
        });
    }

    @Test
    @DisplayName("Проверка функциональности поиска")
    void emptySearchTest() {
        step("Ввести «» в поисковую строку", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(Keys.ENTER);
        });

        step("Проверка страницу с результатом поиска «Страница с поиском не пустая", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }
}