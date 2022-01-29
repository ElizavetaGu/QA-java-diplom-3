import com.UserOperations;
import com.codeborne.selenide.SelenideElement;
import com.pageobject.LoginPage;
import com.pageobject.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest {

    @Before
    public void setUp(){
        browser = "chrome";
    }

    @Test
    @DisplayName("Check that an authorized user can switch between constructor tabs")
    public void checkConstructorTabsWithLogin(){
        UserOperations userOperations = new UserOperations();
        Map<String, String> userData = userOperations.register();

        LoginPage loginPage =
                open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        MainPage mainPage = loginPage.login(userData.get("email"), userData.get("password"));

        SelenideElement fillingsTab = mainPage.chooseFilings();
        fillingsTab.shouldHave(attribute("class"));

        SelenideElement saucesTab = mainPage.chooseSauces();
        saucesTab.shouldHave(attribute("class"));
        fillingsTab.shouldHave(attribute("class"));

        SelenideElement bunsTab = mainPage.chooseBuns();
        bunsTab.shouldHave(attribute("class"));
        saucesTab.shouldHave(attribute("class"));

        userOperations.delete();
    }

    @Test
    @DisplayName("Check that an unauthorized user can switch between constructor tabs")
    public void checkConstructorTabsWithoutLogin(){
        MainPage mainPage =
                open("https://stellarburgers.nomoreparties.site", MainPage.class);

        SelenideElement fillingsTab = mainPage.chooseFilings();
        fillingsTab.shouldHave(attribute("class"));

        SelenideElement saucesTab = mainPage.chooseSauces();
        saucesTab.shouldHave(attribute("class"));
        fillingsTab.shouldHave(attribute("class"));

        SelenideElement bunsTab = mainPage.chooseBuns();
        bunsTab.shouldHave(attribute("class"));
        saucesTab.shouldHave(attribute("class"));
    }
}
