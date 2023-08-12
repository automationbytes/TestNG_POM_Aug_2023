package pageObjects.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pageObjects.initializePageObjects.PageFactoryInitializer;

public class HomePage extends PageFactoryInitializer {

    @FindBy(xpath="//select[@data-test='product_sort_container']")
    private WebElement filterDropdown;

    @FindBy(xpath="//button[@id=\"react-burger-menu-btn\"]")
    private WebElement menutButton;

    @FindBy(xpath="//a[@id=\"logout_sidebar_link\"]")
    private WebElement logOutButton;

    public void SelectFilter(String value) {
        Select dropdown = new Select(filterDropdown);
        dropdown.selectByVisibleText(value);
    }

    public void LogOut(){
        menutButton.click();
        logOutButton.click();

    }

}
