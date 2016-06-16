package com.workable.android.base

import com.persado.oss.quality.stevia.selenium.core.SteviaContext
import com.persado.oss.quality.stevia.spring.SteviaTestBase
import org.springframework.test.context.ContextConfiguration
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Listeners

/**
 * Created by panagiotis_tsiakos on 15/06/16.
 */
@Listeners(value = [DeviceFarmTestListener.class])
@ContextConfiguration(locations = ["classpath:META-INF/spring/device-farm-context.xml"])
class DeviceFarmTestBase extends SteviaTestBase {


    @BeforeClass(alwaysRun = true)
    contextInitBeforeClass() throws Exception {

        Map<String, String> parameters = ['rcHost'              : 'localhost',
                                          'rcPort'              : '4723',
                                          'driverType'          : 'appium',
                                          'debugging'           : 'true',
                                          'actionsLogging'      : 'true',
                                          'highlight'           : 'false',
                                          'waitForElement'      : '10',
                                          'scrollTimeOut'       : '30',
                                          'tests.takeScreenshot': 'true',
                                          'mobilePlatformName'  : 'Android',
                                          'cloudService'        : 'deviceFarm',
                                          'mobileDeviceName'    : '',
                                          'runOnRealDevice:'    : 'true'
        ]
        initializeStevia(parameters);
    }


    @AfterClass(alwaysRun = true)
    void cleanContextOnClass() {
        SteviaContext.clean();
    }


}
