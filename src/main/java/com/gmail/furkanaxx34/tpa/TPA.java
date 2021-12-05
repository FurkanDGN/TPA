package com.gmail.furkanaxx34.tpa;

import co.aikar.commands.PaperCommandManager;
import com.gmail.furkanaxx34.tpa.commands.TPACommand;
import com.gmail.furkanaxx34.tpa.commands.TPAcceptCommand;
import com.gmail.furkanaxx34.tpa.commands.TPDenyCommand;
import com.gmail.furkanaxx34.tpa.handler.TPHandler;
import com.gmail.furkanaxx34.tpa.util.ListenerBasic;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TPA extends JavaPlugin {

  @Override
  public void onEnable() {
    registerListeners();
    PaperCommandManager manager = new PaperCommandManager(this);
    manager.registerCommand(new TPACommand());
    manager.registerCommand(new TPAcceptCommand());
    manager.registerCommand(new TPDenyCommand());
    getLogger().info("Plugin enabled successfully!");
  }

  private void registerListeners() {
    new ListenerBasic<>(
      PlayerQuitEvent.class,
      event -> true,
      event -> TPHandler.handleQuit(event.getPlayer())
    ).register(this);
  }
}
