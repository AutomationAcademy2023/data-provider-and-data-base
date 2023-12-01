import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class DataProviderTest {

    @DataProvider(name = "searchDataProvider")
    public Object[][] searchDataProvider() {
        return new Object[][] {
                { "laptops", "LENOVO"},
                { "smartphones", "IPHONE" },
                { "headphones", "RAZER" },
        };
    }

    @BeforeMethod
    public void setup() {
        open("https://google.com");
    }

    @Test(dataProvider = "searchDataProvider")
    public void searchUsingOneParameter(String searchTerm1, String searchTerm2) { //number of parameters MUST match the number of elements in DataProvider inner arrays
        $("textarea[type='search']").setValue(searchTerm1).pressEnter();

        // Assert that the search results page is displayed
        $("div[id='rso']").should(appear);
        $("textarea[type='search']").shouldHave(text(searchTerm1));

    }


    @Test(dataProvider = "searchDataProvider")
    public void searchUsingMultipleParameters(String searchTerm1, String searchTerm2) {
        $("textarea[type='search']").setValue(searchTerm1 + " " + searchTerm2).pressEnter();

        // Assert that the search results page is displayed
        $("div[id='rso']").should(appear);
        $("textarea[type='search']").shouldHave(text(searchTerm1 + " " + searchTerm2));

    }



}
