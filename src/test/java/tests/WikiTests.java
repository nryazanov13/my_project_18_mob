package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screens.SearchScreen;

import static io.qameta.allure.Allure.step;

@DisplayName("Класс для проверки функциональности поиска в интернет-энциклопедии «Википедия»")
public class WikiTests extends TestBase {

    SearchScreen searchScreen = new SearchScreen();

    @Test
    @Feature("Функциональность поиска в интернет-энциклопедии")
    @Story("Я как пользователь хочу иметь возможность пользоваться строкой поиска без ошибок")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
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
    @Feature("Функциональность поиска в интернет-энциклопедии")
    @Story("Я как пользователь хочу иметь возможность выполнять поиск по конкретному слову")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
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