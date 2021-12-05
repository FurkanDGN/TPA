package com.gmail.furkanaxx34.tpa.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import com.gmail.furkanaxx34.tpa.handler.TPHandler;
import com.gmail.furkanaxx34.tpa.util.XColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("tpa")
public class TPACommand extends BaseCommand {

  @Default
  @CommandCompletion("@players")
  public static void defaultCommand(CommandSender sender, String[] args) {
      if (!(sender instanceof Player player)) {
          sender.sendMessage("This command only can be execute by players.");
          return;
      }
      if (args.length != 1) {
          player.sendMessage(XColor.colorize("&eUsage: &7/tpa <player>"));
          return;
      }
      final var targetPlayer = Bukkit.getPlayer(args[0]);
      if (targetPlayer == null || !targetPlayer.isOnline()) {
          player.sendMessage(XColor.colorize("&ePlayer cannot be found."));
          return;
      }
      if (TPHandler.hasSent(player, targetPlayer)) {
        player.sendMessage(XColor.colorize("&eYou must wait before sending a request to this player again."));
        return;
      }
      TPHandler.sendRequest(player, targetPlayer);
      targetPlayer.sendMessage(XColor.colorize("&6The player named %s wants to teleport to you. Type &a/tpaccept &6to accept, &c/tpdeny &6to reject.".formatted(player.getName())));
      player.sendMessage(XColor.colorize("&aTpa request successfully sent to &e%s&a.".formatted(targetPlayer.getName())));
  }
}
