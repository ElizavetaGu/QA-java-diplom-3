import com.UserOperations;
import com.pageobject.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
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
        mainPage =
                open("https://stellarburgers.nomoreparties.site", MainPage.class);
    }

    @After
    public void tearDown(){
        userOperations.delete();
    }

    @Test
    @DisplayName("Check that a user can login using a button on the main page")
    public void checkLoginViaMainPage() {
        LoginPage loginPage = mainPage.login();

        MainPage finalMainPage = loginPage.login(userEmail, userPassword);
        ProfilePage profilePage = finalMainPage.goToProfilePage();

        Assert.assertEquals(userName, profilePage.getName());
        Assert.assertEquals(userEmail, profilePage.getEmail());
    }

    @Test
    @DisplayName("Check that a user can login using a profile button")
    public void checkLoginViaProfilePage(){
        LoginPage loginPage = mainPage.loginViaProfilePage();

        MainPage finalMainPage = loginPage.login(userEmail, userPassword);
        ProfilePage profilePage = finalMainPage.goToProfilePage();

        Assert.assertEquals(userName, profilePage.getName());
        Assert.assertEquals(userEmail, profilePage.getEmail());
    }

    @Test
    @DisplayName("Check that a user can login using a button on the registration page")
    public void checkLoginViaRegisterPage(){
        LoginPage loginPage0 = mainPage.login();
        RegisterPage registerPage = loginPage0.signUp();
        LoginPage loginPage = registerPage.login();

        MainPage finalMainPage = loginPage.login(userEmail, userPassword);
        ProfilePage profilePage = finalMainPage.goToProfilePage();

        Assert.assertEquals(userName, profilePage.getName());
        Assert.assertEquals(userEmail, profilePage.getEmail());
    }

    @Test
    @DisplayName("Check that a user can login using a button on the forgot password page")
    public void checkLoginViaForgotPasswordPage(){
        LoginPage loginPage0 = mainPage.login();
        ForgotPasswordPage forgotPasswordPage = loginPage0.restorePassword();
        LoginPage loginPage = forgotPasswordPage.login();

        MainPage finalMainPage = loginPage.login(userEmail, userPassword);
        ProfilePage profilePage = finalMainPage.goToProfilePage();

        Assert.assertEquals(userName, profilePage.getName());
        Assert.assertEquals(userEmail, profilePage.getEmail());
    }
}
