package pageObjects.initializePageObjects;

//import controllers.BaseMethod;

import controllers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.modules.GMailPageObjects;
import pageObjects.modules.GoogleHomePageObjects;
import pageObjects.modules.HomePage;
import pageObjects.modules.LoginPage;

import static controllers.WebDriverFactory.getWebDriver;

public class PageFactoryInitializer extends WebDriverFactory {// extends BaseMethod {

    public GoogleHomePageObjects googleHomePage() {
        return PageFactory.initElements(getWebDriver(), GoogleHomePageObjects.class);
    }

    public GMailPageObjects gmailPage() {

        return PageFactory.initElements(getWebDriver(), GMailPageObjects.class);
    }

    public HomePage homePage(){
        return PageFactory.initElements(getWebDriver(), HomePage.class); //findby
    }


    public LoginPage loginPage (){
        return PageFactory.initElements(getWebDriver(), LoginPage.class); //findby
    }
}
