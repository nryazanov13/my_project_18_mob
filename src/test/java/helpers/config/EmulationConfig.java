package helpers.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/emulator.properties")
public interface EmulationConfig extends Config {

    @Key("device.name")
    String deviceName();

    @Key("os_version")
    String osVersion();

    @DefaultValue("android")
    String platform();
}