package tests;

//import controllers.ExcelDataProvider;
import org.testng.annotations.Test;
import pageObjects.initializePageObjects.PageFactoryInitializer;
//import utils.LoggerUtil;
//import utils.Retry;

public class Test1 extends PageFactoryInitializer {
    @Test//(retryAnalyzer = Retry.class)
    public void testGoogle() throws Exception {
  //  	LoggerUtil.logInfo("testGoogle");
        googleHomePage()
                .verifyPageTitle();
        
    }
}
