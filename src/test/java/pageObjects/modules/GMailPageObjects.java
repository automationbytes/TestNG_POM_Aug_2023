/**
 * 
 */
package pageObjects.modules;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;

import static controllers.WebDriverFactory.getWebDriver;

public class GMailPageObjects extends PageFactoryInitializer
{
	@FindBy(xpath="//input[@id='Email']")
	private WebElement emailIDTextBox;
	
	@FindBy(xpath="//input[@id='next']")
	private WebElement nextButton;

	
	public GMailPageObjects enterEmailID(String emailID) 
	{
		emailIDTextBox.sendKeys(emailID);	
		nextButton.click();
		return this;
	}


	public GMailPageObjects verifyPageTitle() throws Exception 
	{
		Assert.assertEquals(getWebDriver().getTitle(), "gagagasgasg");
		return this;
	}
	
	
	
}
