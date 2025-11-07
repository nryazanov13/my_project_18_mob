package helpers;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/android.properties")

public interface AndroidConfig extends Config {

    @Key("app")
    String app();

    @Key("device")
    String device();

    @Key("os_version")
    String osVersion();
}
