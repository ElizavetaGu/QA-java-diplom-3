package com.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {
    //локатор кнопки Войти
    @FindBy(how = How.LINK_TEXT,using="Войти")
    private SelenideElement loginButton;

    public LoginPage login(){
        loginButton.click();
        return page(LoginPage.class);
    }
}
