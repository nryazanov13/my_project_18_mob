package screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class SearchScreen {

    protected SelenideElement
            searchTab = $(accessibilityId("Search Wikipedia")),
            searchTextField = $(id("org.wikipedia.alpha:id/search_src_text")),
            pageListItemTitle = $(id("org.wikipedia.alpha:id/page_list_item_title"));

    protected ElementsCollection
            pageListItems = $$(id("org.wikipedia.alpha:id/page_list_item_title"));

    public SearchScreen clickOnSearchTab() {
        searchTab.click();
        return this;
    }

    public SearchScreen insertSearchText (String text){
        searchTextField.sendKeys(text);
        return this;
    }

    public SearchScreen pressEnter (){
        searchTextField.sendKeys(Keys.ENTER);
        return this;
    }

    public SearchScreen checkSearchResultForSpecificText(String text){
        pageListItemTitle.shouldHave(text(text));
        return this;
    }

    public SearchScreen checkIfPageListIsNotEmpty () {
        pageListItems.shouldHave(sizeGreaterThan(0));
        return this;
    }





}
