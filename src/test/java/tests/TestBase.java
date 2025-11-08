package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import helpers.Attach;
import helpers.BrowserstackConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    protected static BrowserstackConfig config;

    @BeforeAll
    static void setUp() {
        // Устанавливаем платформу по умолчанию, если не задана
        String platform = getProperty("platform", "android");
        if (platform != null && !platform.trim().isEmpty()) {
            System.setProperty("platform", platform);
        } else {
            System.setProperty("platform", "android"); // значение по умолчанию
        }

        config = ConfigFactory.create(BrowserstackConfig.class);

        Configuration.browser = BrowserstackDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 30000;

        System.out.println("📱 Запуск тестов на платформе: " + platform);
        System.out.println("📱 Устройство: " + config.device());
        System.out.println("📱 Версия ОС: " + config.osVersion());
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
        Attach.addVideo(sessionId);
        closeWebDriver();
    }

    private static String getProperty(String name, String defaultValue) {
        String property = System.getProperty(name);
        return (property != null && !property.isEmpty()) ? property : defaultValue;
    }
}