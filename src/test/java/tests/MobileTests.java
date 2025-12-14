package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screens.SearchScreen;

import static io.qameta.allure.Allure.step;


public class MobileTests extends TestBase {

    SearchScreen searchScreen = new SearchScreen();

    @Test
    @DisplayName("Проверка функциональности поиска")
    void emptySearchTest() {
        step("Ввести «» в поисковую строку", () -> {
            searchScreen
                    .clickOnSkipButton()
                    .clickOnSearchTab()
                    .pressEnter();
        });

        step("Проверка страницу с результатом поиска «Страница с поиском не пустая", () -> {
            searchScreen
                    .checkIfPageListIsNotEmpty();
        });
    }

    @Test
    @DisplayName("Успешный поиск по слову «Appium»")
    void successfulSearchTest() {
        step("Ввести «Appium» в поисковую строку", () -> {
            searchScreen
                    .clickOnSkipButton()
                    .clickOnSearchTab()
                    .insertSearchText("Appium");
        });

        step("Проверить найденный контент", () -> {
            searchScreen
                    .checkSearchResultForSpecificText("Appium");
        });
    }


}