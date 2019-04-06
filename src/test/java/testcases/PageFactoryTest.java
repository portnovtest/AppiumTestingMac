package testcases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PageFactoryTest {

    public AppiumDriver driver;
    File app = new File("./app/selendroid-test-app-0.17.0.apk");

    @AndroidFindBy(id = "io.selendroid.testapp:id/my_text_field")
    public WebElement textfield;

    @AndroidFindBy(id = "io.selendroid.testapp:id/visibleButtonTest")
    public WebElement displayButton;

    @AndroidFindBy(id = "io.selendroid.testapp:id/visibleTextView")
    public WebElement displayTxt;

    @AndroidFindBy(className = "android.widget.Button")
    public List<WebElement> button;

    @AndroidFindBy(id = "io.selendroid.testapp:id/buttonStartWebview")
    public WebElement imgBtn;

    @AndroidFindBy(className = "android.widget.Spinner")
    public List<WebElement> spinner;

    @AndroidFindBys({
            @AndroidBy(id = "android:id/content"),
            @AndroidBy(id = "android:id/parentPanel"),
            @AndroidBy(id = "android:id/customPanel"),
            @AndroidBy(id = "android:id/custom"),
            @AndroidBy(className = "android.widget.ListView"),
            @AndroidBy(className = "android.widget.CheckedTextView")
    })
    public List<WebElement> cars;

    @BeforeTest
    public void setUP() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Samsung");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"io.selendroid.testapp");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,".HomeScreenActivity");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void doTesting() throws InterruptedException {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);

        textfield.sendKeys("Rahul");
        displayButton.click();
        System.out.println(displayTxt.getText());
        System.out.println("Total buttons are: " +button.size());
        imgBtn.click();
        Thread.sleep(5000);
        //System.out.println(driver.getPageSource());
        System.out.println(spinner.get(1).getText());
//        spinner.get(1).click();
//        Thread.sleep(5000);
//         cars.get(0).click();

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
