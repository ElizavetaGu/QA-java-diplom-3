import com.pageobject.RegisterPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import static com.UserOperations.EMAIL_POSTFIX;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

public class UnsuccessfulRegistration {
    @Test
    public void checkRegistrationWithIncorrectPassword() {
        String email = RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX;
        String incorrectPassword = RandomStringUtils.randomAlphabetic(5);
        String name = RandomStringUtils.randomAlphabetic(10);

        browser = "firefox";
        RegisterPage registerPage =
                open("https://stellarburgers.nomoreparties.site/register", RegisterPage.class);

        registerPage.setRegisterFields(name, email, incorrectPassword);
        registerPage.submitRegistration();

        String actualError = registerPage.getPasswordError();
        Assert.assertEquals("Некорректный пароль", actualError);
    }
}
