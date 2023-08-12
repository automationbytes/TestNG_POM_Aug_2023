package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import controllers.InitMethod;
import utils.Retry;

import org.testng.*;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExtentTestNGIReporterListener extends InitMethod implements IReporter {
    @SuppressWarnings("rawtypes")
    public void generateReport(List xmlSuites, List suites, String outputDirectory) {
        init();
        for (Object suite : suites) {
            Map result = ((ISuite) suite).getResults();

            for (Object res : result.values()) {
                ITestContext context = ((ISuiteResult) res).getTestContext();

                try {
                    buildTestNodes(context.getFailedTests(), Status.FAIL);
                    buildTestNodes(context.getSkippedTests(), Status.SKIP);
                    buildTestNodes(context.getPassedTests(), Status.PASS);
                } catch (Exception e) {
                }
            }
        }
        for (String s : Reporter.getOutput()) {
            extent.addTestRunnerOutput(s);
        }
        extent.setSystemInfo("Author", "Vignesh Ayyamani");
        extent.setSystemInfo("Browser", browser);
        extent.setSystemInfo("OS", OSName);
        extent.setSystemInfo("OS Version", OSVersion);
        extent.setSystemInfo("OS Architecture", OSArchitecture);
        extent.setSystemInfo("OS Bit", OSBit);
        extent.setSystemInfo("JAVA Version", System.getProperty("java.version"));
        try {
            extent.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
            extent.setSystemInfo("Host IP Address", InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
        }
        extent.flush();
    }

    private void init() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        sparkReporter.config().setDocumentTitle("Extent Report_Vignesh Ayyamani");
        sparkReporter.config().setReportName("Extent Report_Vignesh Ayyamani");
        sparkReporter.config().setTimeStampFormat("HH:mm:ss");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setProtocol(Protocol.HTTPS);
        sparkReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setReportUsesManualConfiguration(true);
    }

    private void buildTestNodes(IResultMap tests, Status status) throws Exception {
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
//                if (result.getThrowable() != null) {
//                    test.log(status, result.getThrowable().getMessage());
//                } else {
//                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
//                }
                if (result.getThrowable() != null) {
                    String screenshotPath = "Images/" + result.getTestClass().getName() + "."
                            + result.getMethod().getMethodName() + ".png";
                    test.log(status, result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }

                if (result.getStatus() == ITestResult.FAILURE) {
                    // Your existing code for capturing screenshots

                    // Set a flag indicating that this test needs to be retried
                    // Log the retry information to the Extent report
                    if (result.getAttribute("retry") != null) {
                        int retryCount = Retry.getCount();
                        test.log(Status.WARNING, "Test is retried. Retry count: " + retryCount);
                    }

                }
                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}


//package listeners;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Protocol;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import controllers.InitMethod;
//import org.apache.logging.log4j.LogManager;
//import utils.Retry;
//
//import org.testng.*;
//
//import java.net.InetAddress;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//
//public class ExtentTestNGIReporterListener extends InitMethod implements IReporter {
//
//    // Create a logger instance for the class
//    private static final Logger logger = LogManager.getLogger(ExtentTestNGIReporterListener.class);
//
//    @SuppressWarnings("rawtypes")
//    public void generateReport(List xmlSuites, List suites, String outputDirectory) {
//        // Configure Log4j
//        //PropertyConfigurator.configure("src/main/resources/log4j.properties");
//
//        init();
//        for (Object suite : suites) {
//            Map result = ((ISuite) suite).getResults();
//
//            for (Object res : result.values()) {
//                ITestContext context = ((ISuiteResult) res).getTestContext();
//
//                try {
//                    buildTestNodes(context.getFailedTests(), Status.FAIL);
//                    buildTestNodes(context.getSkippedTests(), Status.SKIP);
//                    buildTestNodes(context.getPassedTests(), Status.PASS);
//                } catch (Exception e) {
//                    // Log the exception using Log4j
//                    logger.error("An error occurred during report generation.", e);
//                }
//            }
//        }
//        for (String s : Reporter.getOutput()) {
//            extent.addTestRunnerOutput(s);
//        }
//        extent.setSystemInfo("Author", "Vignesh Ayyamani");
//        extent.setSystemInfo("Browser", browser);
//        extent.setSystemInfo("OS", OSName);
//        extent.setSystemInfo("OS Version", OSVersion);
//        extent.setSystemInfo("OS Architecture", OSArchitecture);
//        extent.setSystemInfo("OS Bit", OSBit);
//        extent.setSystemInfo("JAVA Version", System.getProperty("java.version"));
//        try {
//            extent.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
//            extent.setSystemInfo("Host IP Address", InetAddress.getLocalHost().getHostAddress());
//        } catch (Exception e) {
//            // Log the exception using Log4j
//            logger.error("An error occurred while retrieving host information.", e);
//        }
//        extent.flush();
//    }
//
//    private void init() {
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
//        sparkReporter.config().setDocumentTitle("Extent Report_Vignesh Ayyamani");
//        sparkReporter.config().setReportName("Extent Report_Vignesh Ayyamani");
//        sparkReporter.config().setTimeStampFormat("HH:mm:ss");
//        sparkReporter.config().setEncoding("utf-8");
//        sparkReporter.config().setProtocol(Protocol.HTTPS);
//        sparkReporter.config().setTheme(Theme.DARK);
//        extent = new ExtentReports();
//        extent.attachReporter(sparkReporter);
//        extent.setReportUsesManualConfiguration(true);
//    }
//
//    private void buildTestNodes(IResultMap tests, Status status) throws Exception {
//        if (tests.size() > 0) {
//            for (ITestResult result : tests.getAllResults()) {
//                test = extent.createTest(result.getMethod().getMethodName());
//                for (String group : result.getMethod().getGroups())
//                    test.assignCategory(group);
//                if (result.getThrowable() != null) {
//                    // Log the error message using Log4j
//                    logger.error("Test failed with status: " + status, result.getThrowable());
//                } else {
//                    logger.info("Test " + status.toString().toLowerCase() + "ed");
//                }
//                if (result.getStatus() == ITestResult.FAILURE) {
//                    // Log the retry information using Log4j
//                    if (result.getAttribute("retry") != null) {
//                        int retryCount = Retry.getCount();
//                        logger.warn("Test is retried. Retry count: " + retryCount);
//                    }
//                }
//
//                // Attach captured logs to Extent Reports test node
//                String capturedLogs = (String) result.getAttribute("capturedLogs");
//                if (capturedLogs != null) {
//                    test.info("Test Steps:\n" + capturedLogs);
//                }
//
//                test.getModel().setStartTime(getTime(result.getStartMillis()));
//                test.getModel().setEndTime(getTime(result.getEndMillis()));
//            }
//        }
//    }
//
//    private Date getTime(long millis) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(millis);
//        return calendar.getTime();
//    }
//}
