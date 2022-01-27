package com.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    //локатор поля ввода email
    @FindBy(how = How.NAME,using="name")
    private SelenideElement emailField;

    //локатор поля ввода пароля
    @FindBy(how = How.NAME,using="Пароль")
    private SelenideElement passwordField;

    //локатор кнопки войти
    @FindBy(how = How.XPATH,using=".//button[text()='Войти']")
    private SelenideElement enterButton;

    //локатор кнопки восстановить пароль
    @FindBy(how = How.LINK_TEXT,using="Восстановить пароль")
    private SelenideElement restorePassword;

    //локатор кнопки зарегистрироваться
    @FindBy(how = How.LINK_TEXT,using="Зарегистрироваться")
    private SelenideElement signUpButton;

    public void setEmailField(String email){
        emailField.setValue(email);
    }

    public void setPasswordField(String password){
        passwordField.setValue(password);
    }

    public MainPage confirmLogin(){
        enterButton.click();
        return page(MainPage.class);
    }

    public MainPage login(String email, String password){
        setEmailField(email);
        setPasswordField(password);
        return confirmLogin();
    }

    public RegisterPage signUp(){
        signUpButton.click();
        return page(RegisterPage.class);
    }

    public ForgotPasswordPage restorePassword(){
        restorePassword.click();
        return page(ForgotPasswordPage.class);
    }

    public SelenideElement getEnterButton(){
        return enterButton;
    }
}
