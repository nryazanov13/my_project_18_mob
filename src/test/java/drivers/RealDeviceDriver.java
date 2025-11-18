package drivers;

import com.codeborne.selenide.WebDriverProvider;
import helpers.config.RealDeviceConfig;
import io.appium.java_client.android.AndroidDriver;
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

public class RealDeviceDriver implements WebDriverProvider {

    // Константы уровня класса
    private static final String APPIUM_SERVER_URL = "http://localhost:4723/wd/hub";
    private static final String APP_VERSION = "app-alpha-universal-release.apk";
    private static final String APP_URL = "https://github.com/wikimedia/apps-android-wikipedia/releases/download/latest/" + APP_VERSION;
    private static final String APP_PATH = "src/test/resources/apps/" + APP_VERSION;

    // Константы для Android пакетов и активностей
    private static final String ANDROID_APP_PACKAGE = "org.wikipedia.alpha";
    private static final String ANDROID_APP_ACTIVITY = "org.wikipedia.main.MainActivity";

    // Константы для iOS bundle ID
    private static final String IOS_BUNDLE_ID = "org.wikimedia.wikipedia";

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        RealDeviceConfig config = ConfigFactory.create(RealDeviceConfig.class);

        if (config.platform().equals("android")) {
            return createAndroidDriver(config);
        } else {
            return createIOSDriver(config);
        }
    }

    private WebDriver createAndroidDriver(RealDeviceConfig config) {
        io.appium.java_client.android.options.UiAutomator2Options options =
                new io.appium.java_client.android.options.UiAutomator2Options();

        // Ключевые настройки для реального Android устройства
        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setPlatformVersion(config.osVersion())
                .setDeviceName(config.deviceName())
                .setApp(getAppPath())
                .setAppPackage(ANDROID_APP_PACKAGE)
                .setAppActivity(ANDROID_APP_ACTIVITY)
                .setUdid(config.udid())              // Уникальный ID устройства
                .setNoReset(false)                   // Не сбрасывать данные приложения
                .setFullReset(false)                 // Не делать полный сброс
                .setAutoGrantPermissions(true);      // Автоматически давать разрешения

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    private WebDriver createIOSDriver(RealDeviceConfig config) {
        XCUITestOptions options = new XCUITestOptions();

        // Ключевые настройки для реального iOS устройства
        options.setAutomationName(IOS_XCUI_TEST)
                .setPlatformName(IOS)
                .setDeviceName(config.deviceName())
                .setApp(getAppPath())
                .setBundleId(IOS_BUNDLE_ID)
                .setUdid(config.udid());         // Уникальный ID устройства

        return new IOSDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL(APPIUM_SERVER_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAppPath() {
        File app = new File(APP_PATH);
        if (!app.exists()) {
            try (InputStream in = new URL(APP_URL).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app.getAbsolutePath();
    }
}