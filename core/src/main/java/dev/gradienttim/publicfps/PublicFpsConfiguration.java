package dev.gradienttim.publicfps;

import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SliderWidget.SliderSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.TextFieldWidget.TextFieldSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@ConfigName("settings")
public class PublicFpsConfiguration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(false);

  @TextFieldSetting
  private final ConfigProperty<String> url = new ConfigProperty<>("http://localhost:9412")
      .addChangeListener(value -> {
        PublicFpsAddon addon = PublicFpsAddon.addon;
        if (addon != null) addon.reInitialize();
      });

  @TextFieldSetting
  private final ConfigProperty<String> token = new ConfigProperty<>("SECRET_TOKEN")
      .addChangeListener(value -> {
        PublicFpsAddon addon = PublicFpsAddon.addon;
        if (addon != null) addon.reInitialize();
      });

  @SliderSetting(min = 1f, max = 60f)
  private final ConfigProperty<Float> interval = new ConfigProperty<>(3f)
      .addChangeListener(value -> {
        PublicFpsAddon addon = PublicFpsAddon.addon;
        if (addon != null) addon.reInitialize();
      });

  @Override
  public ConfigProperty<Boolean> enabled() {
    return this.enabled;
  }

  public ConfigProperty<String> url() { return this.url; }

  public ConfigProperty<String> token() { return this.token; }

  public ConfigProperty<Float> interval() { return this.interval; }

}
