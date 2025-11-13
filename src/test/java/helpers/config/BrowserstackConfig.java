package helpers.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${platform}.properties",
        "system:properties",
        "system:env"
})
public interface BrowserstackConfig extends Config {

    @Key("device")
    String device();

    @Key("os_version")
    String osVersion();
}