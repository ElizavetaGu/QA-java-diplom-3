package com.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {
    //локатор кнопки Конструктор
    @FindBy(how = How.XPATH,using = ".//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    //локатор кнопки перехода на главную старницу на логитипе Stellar Burgers
    @FindBy(how = How.CLASS_NAME,using = "AppHeader_header__logo__2D0X2")
    private SelenideElement stellarBurgersButton;

    //локатор кнопки профиль
    @FindBy(how = How.XPATH,using=".//button[text()='Профиль']")
    private SelenideElement profileButton;

    //локатор кнопки выйти
    @FindBy(how = How.XPATH,using=".//button[text()='Выход']")
    private SelenideElement logOutButton;

    @FindBy(how = How.NAME,using="Name")
    private SelenideElement nameField;

    @FindBy(how = How.NAME,using="name")
    private SelenideElement emailField;

    public MainPage goToConstructor(){
        constructorButton.click();
        return page(MainPage.class);
    }

    public MainPage goToMainPage(){
        stellarBurgersButton.click();
        return page(MainPage.class);
    }

    public LoginPage logout(){
        logOutButton.click();
        return page(LoginPage.class);
    }

    public String getName(){
        return nameField.getValue();
    }

    public String getEmail(){
        return emailField.getValue();
    }

    public SelenideElement getProfileButton() { return profileButton; }
}
