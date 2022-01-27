package com.pageobject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    //локатор кнопки личный кабинет
    @FindBy(how = How.XPATH,using = ".//p[text()='Личный Кабинет']")
    private SelenideElement profilePageButtonAuth;

    //локатор кнопки личный кабинет
    @FindBy(how = How.XPATH,using = ".//p[text()='Личный Кабинет']")
    private SelenideElement profilePageButtonUnauth;

    //локатор кнопки войти в аккаунт
    @FindBy(how = How.XPATH,using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    //локатор кнопки оформить заказ
    @FindBy(how = How.XPATH,using = ".//button[text()='Оформить заказ']")
    private SelenideElement placeOrderButton;

    //локатор вкладки Булки
    @FindBy(how = How.XPATH,using = ".//div[span[text()='Булки']]")
    private SelenideElement bunsTab;

    //локатор вкладки Соусы
    @FindBy(how = How.XPATH,using = ".//div[span[text()='Соусы']]")
    private SelenideElement saucesTab;

    //локатора вкладки Начинки
    @FindBy(how = How.XPATH,using = ".//div[span[text()='Начинки']]")
    private SelenideElement fillingsTab;

    public ProfilePage goToProfilePage(){
        profilePageButtonAuth.click();
        return page(ProfilePage.class);
    }

    public LoginPage loginViaProfilePage(){
        profilePageButtonUnauth.click();
        return page(LoginPage.class);
    }

    public LoginPage login(){
        loginButton.click();
        return page(LoginPage.class);
    }

    public SelenideElement getLoginButton(){
        return loginButton;
    }

    public SelenideElement getPlaceOrderButton(){
        return placeOrderButton;
    }

    public SelenideElement chooseBuns(){
        bunsTab.click();
        return bunsTab;
    }

    public SelenideElement getBuns(){
        return bunsTab;
    }

    public SelenideElement chooseSauces(){
        saucesTab.click();
        return saucesTab;
    }

    public SelenideElement getSauces(){
        return saucesTab;
    }

    public SelenideElement chooseFilings(){
        fillingsTab.click();
        return fillingsTab;
    }

    public SelenideElement getFilings(){
        return fillingsTab;
    }
}
