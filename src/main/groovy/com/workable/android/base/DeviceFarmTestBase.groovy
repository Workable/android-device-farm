package com.workable.android.base

import com.persado.oss.quality.stevia.selenium.core.SteviaContext
import com.persado.oss.quality.stevia.spring.SteviaTestBase
import org.junit.AfterClass
import org.springframework.test.context.ContextConfiguration
import org.testng.annotations.BeforeClass
import org.testng.annotations.Listeners

/**
 * Created by panagiotis_tsiakos on 15/06/16.
 */
@Listeners(value = [DeviceFarmTestListener.class])
@ContextConfiguration(locations = ["classpath:META-INF/spring/device-farm-context.xml"])
class DeviceFarmTestBase extends SteviaTestBase {


    @BeforeClass()
    contextInitBeforeTest() throws Exception {

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


    @AfterClass()
    void cleanContextOnTest() {
        SteviaContext.clean();
    }


}