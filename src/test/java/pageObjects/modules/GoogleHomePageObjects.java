/**
 * 
 */
package pageObjects.modules;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.initializePageObjects.PageFactoryInitializer;


public class GoogleHomePageObjects extends PageFactoryInitializer
{

	public GoogleHomePageObjects verifyPageTitle() throws Exception
	{
		System.out.println(getWebDriver().getTitle());
	//	Assert.assertEquals(getWebDriver().getTitle(), "Bing");
		return this;
	}

	@FindBy(xpath="//a[text()='Gmail']")
	private WebElement GmailLink;

	@FindBy(id="lst-ib")
	private WebElement SearchBox;

	
	public GoogleHomePageObjects clickonGmailLink() throws Exception
	{
		GmailLink.click();
		return this;		
	}


	public GoogleHomePageObjects enterTextToSearchBox() 
	{
		SearchBox.sendKeys("hello@google.com");
		return this;	
	}

	

}
