package testcases;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserTest {

    IOSDriver driver;
    public static String firstDeviceName = "a2e817d8b4ed41ad28b92a8986b3bb1d3e0bca78"; // iPhone X
    public static String secondDeviceName = "8c6d19d745f1b8902d5d72f744117dfd51fd7c56"; // iPhone 6 Plus

    @BeforeClass
    @Parameters({"deviceID"})
    public void startTime(String device_id) throws IOException {
        DesiredCapabilities cap = new DesiredCapabilities();

        if (device_id.equalsIgnoreCase(firstDeviceName)){
            cap.setCapability("deviceName",firstDeviceName);
            cap.setCapability("udid",firstDeviceName);
            cap.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone X");
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,"12.2");
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
            cap.setCapability("wdaLocalPort", 8200);
            cap.setCapability(MobileCapabilityType.APP, "/Users/phildolganov/Library/Developer/Xcode/DerivedData/WebDriverAgent-cvqckynzuuktkogfpkkkgyompmoj/Build/Products/Debug-iphoneos/IntegrationApp.app");

        } else if (device_id.equalsIgnoreCase(secondDeviceName)){
            cap.setCapability("deviceName",secondDeviceName);
            cap.setCapability("udid",secondDeviceName);
            cap.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 6 Plus");
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,"12.2");
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
            cap.setCapability("wdaLocalPort", 8201);
            cap.setCapability(MobileCapabilityType.APP, "/Users/phildolganov/Library/Developer/Xcode/DerivedData/WebDriverAgent-cvqckynzuuktkogfpkkkgyompmoj/Build/Products/Debug-iphoneos/IntegrationApp.app");
        }

        driver = new IOSDriver(new URL("http://localhost:4444/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    }

    @Test
    public void testcase() throws InterruptedException {
        driver.findElement(MobileBy.AccessibilityId("Attributes")).click();

        WebElement switches = driver.findElement(By.className("XCUIElementTypeSwitch"));

        //Turn off the switch
        if (switches.getAttribute("value").equals("1")){
            switches.click();
        }

        Thread.sleep(3000);

        //Turn on the switch
        if (switches.getAttribute("value").equals("0")){
            switches.click();
        }


        Thread.sleep(3000);
        driver.quit();
    }
}
