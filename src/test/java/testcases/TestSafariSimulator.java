package testcases;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestSafariSimulator {

    public static IOSDriver driver;
    public static AppiumDriverLocalService service;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
                        .withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
                        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                        .withLogFile(new File("/Users/phildolganov/IdeaProjects/AppiumTestingMac/src/test/resources/logs/Appium.log")));
        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7 Plus");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
        //capabilities.setCapability(MobileCapabilityType.UDID,"9AA0F53C-1F42-4E5C-AE9E-3B5F7F16C54C");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        driver.get("http://google.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.name("q")).sendKeys("Hello Appium");

        Thread.sleep(3000);

        driver.quit();
        service.stop();
    }
}
