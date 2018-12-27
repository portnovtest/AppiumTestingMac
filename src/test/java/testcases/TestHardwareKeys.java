package testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestHardwareKeys {
    public static AndroidDriver driver;
    public static AppiumDriverLocalService service;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        File app = new File("./app/selendroid-test-app-0.17.0.apk");
        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/bin/node"))
                        .withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
                        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                        .withLogFile(new File(System.getProperty("user.dir") + "/src/test/resources/logs/Appium.log")));
        service.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        //AppPackage & AppActivity
        // adb shell
        // dumpsys window windows | grep -E 'mCurrentFocus'

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//        driver.findElement(By.id("io.selendroid.testapp:id/buttonStartWebview")).click();
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
//        Thread.sleep(3000);
        driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).click();
//        driver.pressKey(new KeyEvent(AndroidKey.A));
//        driver.pressKey(new KeyEvent(AndroidKey.P));
//        driver.pressKey(new KeyEvent(AndroidKey.P));
//        driver.pressKey(new KeyEvent(AndroidKey.I));
//        driver.pressKey(new KeyEvent(AndroidKey.U));
//        driver.pressKey(new KeyEvent(AndroidKey.M));

        Actions action = new Actions(driver);
        action.sendKeys("Appium").perform();

       //driver.toggleWifi();
        try {
            driver.toggleAirplaneMode();
        } catch (Throwable t) {
            System.out.print("Airplane mode active");
        }

        //driver.longPressKey(new KeyEvent(AndroidKey.HOME));
        Thread.sleep(3000);



        //Thread.sleep(3000);
        driver.quit();
        service.stop();
    }
}
