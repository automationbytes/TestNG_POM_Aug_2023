package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private static int count = 0;
    private static int maxTry = 2; // Retry the failed test 2 times

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (iTestResult.getThrowable() != null && count < maxTry) {
        	if (count < maxTry) {
                count++;
                return true;
            }
        }
        return false;
    }
    

    public static int getCount() {
        return count;
    }
}
	