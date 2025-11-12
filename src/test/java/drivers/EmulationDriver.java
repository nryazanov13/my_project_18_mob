package drivers;

import com.codeborne.selenide.WebDriverProvider;
import helpers.EmulationConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class EmulationDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        EmulationConfig config = ConfigFactory.create(EmulationConfig.class);

        if (config.platform().equals("android")) {
            return createAndroidDriver(config);
        } else {
            return createIOSDriver(config);
        }
    }

    private WebDriver createAndroidDriver(EmulationConfig config) {
        UiAutomator2Options options = new UiAutomator2Options();

        // Ключевые настройки для Android эмулятора
        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setPlatformVersion(config.osVersion())
                .setDeviceName(config.deviceName())
                .setApp(getAppPath())
                .setAppPackage("org.wikipedia.alpha")
                .setAppActivity("org.wikipedia.*");

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    private WebDriver createIOSDriver(EmulationConfig config) {
        XCUITestOptions options = new XCUITestOptions();

        // Ключевые настройки для iOS симулятора
        options.setAutomationName(IOS_XCUI_TEST)
                .setPlatformName(IOS)
                .setPlatformVersion(config.osVersion())
                .setDeviceName(config.deviceName())
                .setApp(getAppPath())
                .setBundleId("org.wikimedia.wikipedia") ;// Bundle ID для iOS


        return new IOSDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAppPath() {
        String appVersion = "app-alpha-universal-release.apk";
        String appUrl = "https://github.com/wikimedia/apps-android-wikipedia" +
                "/releases/download/latest/" + appVersion;
        String appPath = "src/test/resources/apps/" + appVersion;

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}