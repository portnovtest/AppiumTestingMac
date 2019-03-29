package testcases;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestSwipeOrScroll {
    public static IOSDriver driver;
    public static AppiumDriverLocalService service;

    public static void swipe(int x_start,int y_start,int x_stop,int y_stop, int duration){
        new TouchAction<>(driver).press(PointOption.point(x_start,y_start))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(duration)))
                .moveTo(PointOption.point(x_stop,y_stop)).release().perform();
    }


    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
                        .withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
                        //.usingPort(4723).withIPAddress("127.0.0.1")
                        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                        .withLogFile(new File("/Users/phildolganov/IdeaProjects/AppiumTestingMac/src/test/resources/logs/Appium.log")));
        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Plus");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
        capabilities.setCapability(MobileCapabilityType.UDID,"8c6d19d745f1b8902d5d72f744117dfd51fd7c56");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/phildolganov/Library/Developer/Xcode/DerivedData/WebDriverAgent-cvqckynzuuktkogfpkkkgyompmoj/Build/Products/Debug-iphoneos/IntegrationApp.app");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(MobileBy.AccessibilityId("Scrolling")).click();

        WebElement tapElement = driver.findElement(MobileBy.AccessibilityId("TableView"));
        tapElement.click();

        System.out.println(driver.findElement(MobileBy.AccessibilityId("35")).isDisplayed());
        int i = 0;
        while (!driver.findElement(MobileBy.AccessibilityId("35")).isDisplayed()){
            System.out.println("Swipe count : " + i);
            swipe(20,639,20,67,2);
            i++;
        }

        System.out.println(driver.findElement(MobileBy.AccessibilityId("35")).isDisplayed());

        i = 0;
        System.out.println(driver.findElement(MobileBy.AccessibilityId("1")).isDisplayed());
        while (!driver.findElement(MobileBy.AccessibilityId("1")).isDisplayed()){
            System.out.println("Swipe count : " + i);
            swipe(20,67,20,639,2);
            i++;
        }
        System.out.println(driver.findElement(MobileBy.AccessibilityId("1")).isDisplayed());

        Thread.sleep(3000);
        driver.quit();
        service.stop();
    }
}
