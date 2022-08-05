package listeners;

import com.relevantcodes.extentreports.LogStatus;
import extent.ExtentManager;
import extent.ExtentTestManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.FileUtils;

import static utils.Constants.*;

/**
 * Standard TestNG Listener Class
 */

public class TestListener implements ITestListener {
    private static synchronized String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public void onStart(ITestContext iTestContext) {
        // TODO: 05/08/2022 Integration of Log4j for logging mechanism instead of System.out.println
        System.out.println((FileUtils.getMessage(ON_START_MESSAGE) + iTestContext.getName()));
    }

    public void onFinish(ITestContext iTestContext) {
        // TODO: 05/08/2022 Integration of Log4j for logging mechanism instead of System.out.println
        System.out.println((FileUtils.getMessage(ON_FINISH_MESSAGE) + iTestContext.getName()));
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }

    public void onTestStart(ITestResult iTestResult) {
        // TODO: 05/08/2022 Integration of Log4j for logging mechanism instead of System.out.println
        System.out.println((FileUtils.getMessage(ON_TEST_START_MESSAGE) + getTestMethodName(iTestResult) + " start"));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        // TODO: 05/08/2022 Integration of Log4j for logging mechanism instead of System.out.println
        System.out.println((FileUtils.getMessage(ON_TEST_SUCCESS_MESSAGE) + getTestMethodName(iTestResult) + " succeed"));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        // TODO: 05/08/2022 Integration of Log4j for logging mechanism instead of System.out.println
        System.out.println((FileUtils.getMessage(ON_TEST_FAILURE_MESSAGE) + getTestMethodName(iTestResult) + " failed"));
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        // TODO: 05/08/2022 Integration of Log4j for logging mechanism instead of System.out.println
        System.out.println((FileUtils.getMessage(ON_TEST_SKIPPED_MESSAGE) + getTestMethodName(iTestResult) + " skipped"));
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

}	