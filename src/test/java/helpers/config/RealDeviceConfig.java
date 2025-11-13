package helpers.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/real.properties")
public interface RealDeviceConfig extends Config {

    @Key("device.name")
    String deviceName();

    @Key("Udid")
    String udid();

    @Key("os_version")
    String osVersion();

    @DefaultValue("android")
    String platform();
}