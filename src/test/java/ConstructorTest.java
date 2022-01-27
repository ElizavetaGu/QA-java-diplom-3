import com.UserOperations;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.pageobject.LoginPage;
import com.pageobject.MainPage;
import com.pageobject.ProfilePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest {
    final String selectedTabClass = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    final String unselectedTabClass = "tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect";

    @Before
    public void setUp(){
        browser = "firefox";
    }

    @Test
    public void checkConstructorTabsWithLogin(){
        UserOperations userOperations = new UserOperations();
        Map<String, String> userData = userOperations.register();

        LoginPage loginPage =
                open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        MainPage mainPage = loginPage.login(userData.get("email"), userData.get("password"));

        SelenideElement fillingsTab = mainPage.chooseFilings();
        fillingsTab.shouldHave(attribute("class",selectedTabClass));

        SelenideElement saucesTab = mainPage.chooseSauces();
        saucesTab.shouldHave(attribute("class",selectedTabClass));
        fillingsTab.shouldHave(attribute("class",unselectedTabClass));

        SelenideElement bunsTab = mainPage.chooseBuns();
        bunsTab.shouldHave(attribute("class",selectedTabClass));
        saucesTab.shouldHave(attribute("class",unselectedTabClass));

        userOperations.delete();
    }

    @Test
    public void checkConstructorTabsWithoutLogin(){
        MainPage mainPage =
                open("https://stellarburgers.nomoreparties.site", MainPage.class);

        SelenideElement fillingsTab = mainPage.chooseFilings();
        fillingsTab.shouldHave(attribute("class",selectedTabClass));

        SelenideElement saucesTab = mainPage.chooseSauces();
        saucesTab.shouldHave(attribute("class",selectedTabClass));
        fillingsTab.shouldHave(attribute("class",unselectedTabClass));

        SelenideElement bunsTab = mainPage.chooseBuns();
        bunsTab.shouldHave(attribute("class",selectedTabClass));
        saucesTab.shouldHave(attribute("class",unselectedTabClass));
    }
}
