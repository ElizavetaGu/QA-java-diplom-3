package com.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    @Step("Go to profile page")
    public ProfilePage goToProfilePage(){
        profilePageButtonAuth.click();
        return page(ProfilePage.class);
    }

    @Step("Go to login page via a profile page button")
    public LoginPage loginViaProfilePage(){
        profilePageButtonUnauth.click();
        return page(LoginPage.class);
    }

    @Step("Go to login page")
    public LoginPage login(){
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Find login button")
    public SelenideElement getLoginButton(){
        return loginButton;
    }

    @Step("Find place order button")
    public SelenideElement getPlaceOrderButton(){
        return placeOrderButton;
    }

    @Step("Choose bun tab")
    public SelenideElement chooseBuns(){
        bunsTab.click();
        return bunsTab;
    }

    @Step("Find bun tab")
    public SelenideElement getBuns(){
        return bunsTab;
    }

    @Step("Choose sauces tab")
    public SelenideElement chooseSauces(){
        saucesTab.click();
        return saucesTab;
    }

    @Step("Find sauces tab")
    public SelenideElement getSauces(){
        return saucesTab;
    }

    @Step("Choose fillings tab")
    public SelenideElement chooseFilings(){
        fillingsTab.click();
        return fillingsTab;
    }

    @Step("Find fillings tab")
    public SelenideElement getFilings(){
        return fillingsTab;
    }
}
