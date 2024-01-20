package dev.gradienttim.publicfps;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class PublicFpsAddon extends LabyAddon<PublicFpsConfiguration> {

  static PublicFpsAddon addon;

  private ScheduledFuture<?> executorService = null;
  private final PublicFpsApi api = new PublicFpsApi();

  @Override
  protected void enable() {
    PublicFpsAddon.addon = this;
    this.registerSettingCategory();
    this.reInitialize();
  }

  public void reInitialize() {
    if (this.executorService != null) {
      this.executorService.cancel(true);
    }

    String url = this.configuration().url().get();
    String token = this.configuration().token().get();
    Float interval = this.configuration().interval().get();

    this.executorService = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
      int fps = this.labyAPI().minecraft().getFPS();
      this.api.sendFPS(fps, url, token);
    }, 0, interval.intValue(), TimeUnit.SECONDS);
  }

  @Override
  protected Class<? extends PublicFpsConfiguration> configurationClass() {
    return PublicFpsConfiguration.class;
  }

}
