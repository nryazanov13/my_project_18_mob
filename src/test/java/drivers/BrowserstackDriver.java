package drivers;

import com.codeborne.selenide.WebDriverProvider;
import helpers.config.BrowserstackConfig;
import helpers.config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        // Убедимся, что platform установлена
        String platform = System.getProperty("platform", "android");
        System.setProperty("platform", platform);

        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
        BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class);

        // BrowserStack capabilities в старом формате
        caps.setCapability("browserstack.user", credentials.userName());
        caps.setCapability("browserstack.key", credentials.accessKey());
        caps.setCapability("app", credentials.app()); // Единое приложение
        caps.setCapability("device", browserstackConfig.device());
        caps.setCapability("os_version", browserstackConfig.osVersion());
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");

        try {
            return new RemoteWebDriver(
                    new URL(credentials.browserstackUrl()), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}