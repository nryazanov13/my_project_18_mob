package helpers;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/ios.properties")

public interface AppleConfig extends Config {
    @Config.Key("app")
    String app();

    @Key("device")
    String device();

    @Key("os_version")
    String osVersion();
}
