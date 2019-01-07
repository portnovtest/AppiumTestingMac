package testcases;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestAlerts {
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
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1.2");
        capabilities.setCapability(MobileCapabilityType.UDID,"8c6d19d745f1b8902d5d72f744117dfd51fd7c56");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/phildolganov/Library/Developer/Xcode/DerivedData/WebDriverAgent-cvqckynzuuktkogfpkkkgyompmoj/Build/Products/Debug-iphoneos/IntegrationApp.app");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(MobileBy.AccessibilityId("Alerts")).click();
//        driver.findElement(MobileBy.AccessibilityId("Create App Alert")).click();
//        driver.switchTo().alert().accept();

        driver.findElement(MobileBy.AccessibilityId("Create GPS access Alert")).click();
        HashMap<String,String> param = new HashMap<>();
        param.put("action", "getButtons");
        List<String> buttons = (List<String>) driver.executeScript("mobile: alert", param);

        for (String button : buttons) {
            System.out.println(button);
            if (button.equals("Always Allow")){
                param.put("action", "accept");
                driver.executeScript("mobile: alert", param);
                break;
            }
        }


        Thread.sleep(3000);
        driver.quit();
        //service.stop();
    }
}
