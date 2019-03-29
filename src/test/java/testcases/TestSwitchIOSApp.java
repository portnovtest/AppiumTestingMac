package testcases;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestSwitchIOSApp {
    public static IOSDriver driver;
    public static AppiumDriverLocalService service;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

//        service = AppiumDriverLocalService.buildService(
//                new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
//                        .withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
//                        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
//                        .withLogFile(new File("/Users/phildolganov/IdeaProjects/AppiumTestingMac/src/test/resources/logs/Appium.log")));
//        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Plus");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
        capabilities.setCapability(MobileCapabilityType.UDID,"8c6d19d745f1b8902d5d72f744117dfd51fd7c56");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/phildolganov/Library/Developer/Xcode/DerivedData/WebDriverAgent-cvqckynzuuktkogfpkkkgyompmoj/Build/Products/Debug-iphoneos/IntegrationApp.app");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Activate WhatsApp
        HashMap<String,String> param = new HashMap<>();
        param.put("bundleId", "net.whatsapp.WhatsApp");
        driver.executeScript("mobile: launchApp", param);
        driver.findElement(MobileBy.AccessibilityId("Roma v USA")).click();

        Thread.sleep(3000);
        //ReActivate Integration App
        param.put("bundleId", "com.facebook.wda.integrationApp");
        driver.executeScript("mobile: activateApp", param);

        Thread.sleep(3000);
        // Activate PhotoAlbum
        param.put("bundleId", "com.apple.mobileslideshow");
        driver.executeScript("mobile: launchApp", param);

        Thread.sleep(3000);
        driver.quit();
        //service.stop();
    }
}
