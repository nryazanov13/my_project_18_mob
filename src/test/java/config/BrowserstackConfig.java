package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:browserstack.properties"
})
public interface BrowserstackConfig extends Config {

    @Key("userName")
    String userName();

    @Key("accessKey")
    String accessKey();

    @Key("app")
    String app();

    @Key("device")
    String device();

    @Key("os_version")
    String osVersion();
}