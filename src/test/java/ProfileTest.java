import com.UserOperations;
import com.pageobject.LoginPage;
import com.pageobject.MainPage;
import com.pageobject.ProfilePage;
import io.qameta.allure.junit4.DisplayName;
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

        browser = "chrome";
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
    @DisplayName("Check that a user can logout")
    public void checkLogout() {
        LoginPage loginPage = profilePage.logout();
        loginPage.getEnterButton().should(exist);
    }

    @Test
    @DisplayName("Check that a user can go to the constructor from the profile page")
    public void checkGoToConstructor(){
        MainPage mainPage = profilePage.goToConstructor();
        mainPage.getSauces().should(exist);
        mainPage.getFilings().should(exist);
        mainPage.getBuns().should(exist);
        mainPage.getPlaceOrderButton().should(exist);
        mainPage.getLoginButton().should(not(exist));
    }

    @Test
    @DisplayName("Check that a user can go to the main page from the profile page using stellar burgers logo")
    public void checkGoToMainPage(){
        MainPage mainPage = profilePage.goToMainPage();
        mainPage.getSauces().should(exist);
        mainPage.getFilings().should(exist);
        mainPage.getBuns().should(exist);
        mainPage.getPlaceOrderButton().should(exist);
        mainPage.getLoginButton().should(not(exist));
    }

    @Test
    @DisplayName("Check that a user can go to the profile page")
    public void checkGoToProfilePage(){
        profilePage.getProfileButton().should(exist);
        Assert.assertEquals(userEmail, profilePage.getEmail());
        Assert.assertEquals(userName, profilePage.getName());
    }
}
