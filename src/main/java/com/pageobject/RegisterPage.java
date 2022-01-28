package com.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {
    //локатор поля имя
    @FindBy(how = How.NAME,using="name")
    private ElementsCollection nameAndEmailFields;

    //локатор поля email
    //@FindBy(how = How.NAME,using="name")
    //private SelenideElement emailField;

    //локатор поля пароль
    @FindBy(how = How.NAME,using="Пароль")
    private SelenideElement passwordField;

    //локатор кнопки Зарегистрироваться
    @FindBy(how = How.XPATH,using=".//button[text()='Зарегистрироваться']")
    private SelenideElement signupButton;

    //локатор кнопки Войти
    @FindBy(how = How.LINK_TEXT,using="Войти")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH,using = ".//p[text()='Некорректный пароль']")
    private SelenideElement passwordError;

    //локатор кнопки личный кабинет
    @FindBy(how = How.XPATH,using = ".//p[text()='Личный Кабинет']")
    private SelenideElement profilePageButton;

    public ProfilePage goToProfilePage(){
        profilePageButton.click();
        return page(ProfilePage.class);
    }

    public void setNameField(String name){
        nameAndEmailFields.get(0).setValue(name);
    }

    public void setEmailField(String email){
        nameAndEmailFields.get(1).setValue(email);
    }

    public void setPasswordField(String password){
        passwordField.setValue(password);
    }

    public void setRegisterFields(String name, String email, String password){
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }

    public LoginPage submitRegistration(){
        signupButton.click();
        return page(LoginPage.class);
    }

    public LoginPage login(){
        loginButton.click();
        return page(LoginPage.class);
    }

    public String getPasswordError(){
        return passwordError.getText();
    }
}
