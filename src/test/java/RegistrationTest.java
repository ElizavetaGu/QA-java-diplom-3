import com.pageobject.LoginPage;
import com.pageobject.MainPage;
import com.pageobject.ProfilePage;
import com.pageobject.RegisterPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.UserOperations.EMAIL_POSTFIX;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest {
    RegisterPage registerPage;
    String email;
    String password;
    String name;

    @Before
    public void setUp(){
        email = RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX;
        password = RandomStringUtils.randomAlphabetic(10);
        name = RandomStringUtils.randomAlphabetic(10);

        browser = "firefox";
        registerPage =
                open("https://stellarburgers.nomoreparties.site/register", RegisterPage.class);
    }

    @Test
    public void checkSuccessfulRegistration() {
        registerPage.setRegisterFields(name, email, password);
        LoginPage loginPage = registerPage.submitRegistration();
        MainPage mainPage = loginPage.login(email, password);

        ProfilePage profilePage = mainPage.goToProfilePage();

        Assert.assertEquals(name, profilePage.getName());
        Assert.assertEquals(email.toLowerCase(), profilePage.getEmail().toLowerCase());
    }

    @Test
    public void checkRegistrationWithIncorrectPassword() {
        registerPage.setRegisterFields(name, email, RandomStringUtils.randomAlphabetic(5));
        registerPage.submitRegistration();

        String actualError = registerPage.getPasswordError();
        Assert.assertEquals("Некорректный пароль", actualError);
    }
}
