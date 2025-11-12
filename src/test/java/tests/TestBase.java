package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.EmulationDriver;
import drivers.RealDeviceDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void setUp() {
        String deviceHost = System.getProperty("deviceHost", "emulation");
        String platform = System.getProperty("platform", "android");

        // Устанавливаем platform для конфигов
        System.setProperty("platform", platform);

        // Выбираем драйвер в зависимости от deviceHost
        switch (deviceHost) {
            case "browserstack":
                Configuration.browser = BrowserstackDriver.class.getName();
                break;
            case "emulation":
                Configuration.browser = EmulationDriver.class.getName();
                break;
            case "real":
                Configuration.browser = RealDeviceDriver.class.getName();
                break;
            default:
                throw new IllegalArgumentException("Unknown deviceHost: " + deviceHost);
        }

        // Общие настройки
        Configuration.browserSize = null;
        Configuration.timeout = 30000;

        System.out.println("🚀 Запуск тестов:");
        System.out.println("📱 Platform: " + platform);
        System.out.println("🏠 DeviceHost: " + deviceHost);
    }

    @BeforeEach
    void addAllureListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();
        Attach.pageSource();

        // Добавляем видео только для BrowserStack
        if ("browserstack".equals(System.getProperty("deviceHost"))) {
            Attach.addVideo(sessionId);
        }
        closeWebDriver();
    }
}