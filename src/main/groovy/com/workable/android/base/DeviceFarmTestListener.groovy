package com.workable.android.base

import com.persado.oss.quality.stevia.selenium.core.SteviaContext
import com.workable.android.base.controllers.AndroidController
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.testng.IConfigurationListener2
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult

/**
 * DeviceFarmTestListener
 * <p/>
 * Listener class that is invoked by it's declaration in the suite file(s) e.g testng.xml inside <listener></listener> tags
 * This class implements The ITestListener,IConfigurationListener2,interfaces available in testng framework
 * thus provides us with the capability to add desired functionality in various pointcuts during a test execution.
 * <p/>
 * Created by panagiotis_tsiakos on 2/19/15.
 */

class DeviceFarmTestListener implements ITestListener, IConfigurationListener2 {


    @Override
    void beforeConfiguration(ITestResult iTestResult) {

    }

    @Override
    void onConfigurationSuccess(ITestResult iTestResult) {

    }

    //Generate screenshot on test failure (not illustrated in the report due to testng bug)
    @Override
    void onConfigurationFailure(ITestResult iTestResult) {
        generateScreenshot(iTestResult.getMethod().getConstructorOrMethod().getName())

    }

    @Override
    void onConfigurationSkip(ITestResult iTestResult) {

    }

    @Override
    void onTestStart(ITestResult iTestResult) {

    }

    @Override
    void onTestSuccess(ITestResult iTestResult) {

    }

    //Generate screenshot on test failure
    @Override
    void onTestFailure(ITestResult iTestResult) {
        generateScreenshot(iTestResult.getMethod().getConstructorOrMethod().getName())
    }

    @Override
    void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    void onStart(ITestContext iTestContext) {

    }

    @Override
    void onFinish(ITestContext iTestContext) {

    }

    public boolean generateScreenshot(final String name) {
        String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
        File screenshot = ((TakesScreenshot) ((AndroidController) SteviaContext.getWebController()).getDriver()).getScreenshotAs(OutputType.FILE);
        return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
    }

}
