package com.gmail.furkanaxx34.tpa.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import com.gmail.furkanaxx34.tpa.handler.TPHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("tpaccept")
public class TPAcceptCommand extends BaseCommand {

  @Default
  public static void defaultCommand(CommandSender sender, String[] args) {
      if (!(sender instanceof Player player)) {
          sender.sendMessage("This command only can be execute by players.");
          return;
      }
    TPHandler.acceptRequest(player);
  }
}
