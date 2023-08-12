package pageObjects.modules;

import controllers.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends WebDriverFactory {

    @FindBy(xpath = "//input[@id='user-name']")
    WebElement username_webEdit;


    @FindBy(xpath = "//input[@id='password']")
    WebElement password_editbox;


    @FindBy(xpath = "//input[@id='login-button']")
    WebElement login_button;


    //findby
    public void enterUsername(String user){
        username_webEdit.sendKeys(user);
    }

    //normal by
    public void enterPassword(String password){
        password_editbox.sendKeys(password);
    }

    public void clickLogin(){
        login_button.click();
    }

    public String pageTitle(){
        return getWebDriver().getTitle();
    }
}
