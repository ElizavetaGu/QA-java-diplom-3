import com.UserOperations;
import com.pageobject.LoginPage;
import com.pageobject.MainPage;
import com.pageobject.ProfilePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class ProfileTest {
    ProfilePage profilePage;
    MainPage mainPage;
    UserOperations userOperations;
    Map<String, String> userData;
    String userPassword;
    String userEmail;
    String userName;

    @Before
    public void setUp(){
        userOperations = new UserOperations();
        userData = userOperations.register();
        userPassword = userData.get("password");
        userEmail = userData.get("email");
        userName = userData.get("name");

        browser = "firefox";
        LoginPage loginPage =
                open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        mainPage = loginPage.login(userEmail, userPassword);
        profilePage = mainPage.goToProfilePage();
    }

    @After
    public void tearDown(){
        userOperations.delete();
    }

    @Test
    public void checkLogout() {
        LoginPage loginPage = profilePage.logout();
        loginPage.getEnterButton().should(exist);
    }

    @Test
    public void checkGoToConstructor(){
        MainPage mainPage = profilePage.goToConstructor();
        mainPage.getSauces().should(exist);
        mainPage.getFilings().should(exist);
        mainPage.getBuns().should(exist);
        mainPage.getPlaceOrderButton().should(exist);
        mainPage.getLoginButton().should(not(exist));
    }

    @Test
    public void checkGoToMainPage(){
        MainPage mainPage = profilePage.goToMainPage();
        mainPage.getSauces().should(exist);
        mainPage.getFilings().should(exist);
        mainPage.getBuns().should(exist);
        mainPage.getPlaceOrderButton().should(exist);
        mainPage.getLoginButton().should(not(exist));
    }

    @Test
    public void checkGoToProfilePage(){
        profilePage.getProfileButton().should(exist);
        Assert.assertEquals(userEmail, profilePage.getEmail());
        Assert.assertEquals(userName, profilePage.getName());
    }
}
