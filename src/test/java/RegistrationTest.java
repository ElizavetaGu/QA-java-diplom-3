import com.pageobject.LoginPage;
import com.pageobject.MainPage;
import com.pageobject.ProfilePage;
import com.pageobject.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.UserOperations.EMAIL_POSTFIX;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest {
    RegisterPage registerPage;
    ProfilePage profilePage;
    String email;
    String password;
    String name;

    @Before
    public void setUp(){
        email = RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX;
        password = RandomStringUtils.randomAlphabetic(10);
        name = RandomStringUtils.randomAlphabetic(10);

        browser = "chrome";
        registerPage =
                open("https://stellarburgers.nomoreparties.site/register", RegisterPage.class);
    }

    @Test
    @DisplayName("Check that a user can register")
    public void checkSuccessfulRegistration() {
        registerPage.setRegisterFields(name, email, password);
        LoginPage loginPage = registerPage.submitRegistration();
        MainPage mainPage = loginPage.login(email, password);

        profilePage = mainPage.goToProfilePage();

        Assert.assertEquals(name, profilePage.getName());
        Assert.assertEquals(email.toLowerCase(), profilePage.getEmail().toLowerCase());

        profilePage.logout();
    }

    @Test
    @DisplayName("Check that a user cannot register using a password that is less than 6 symbols")
    public void checkRegistrationWithIncorrectPassword() {

        String incorrectPassword = RandomStringUtils.randomAlphabetic(5);

        registerPage.setRegisterFields(name, email, incorrectPassword);
        registerPage.submitRegistration();

        String actualError = registerPage.getPasswordError();
        Assert.assertEquals("Некорректный пароль", actualError);
    }
}
