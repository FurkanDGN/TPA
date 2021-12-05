package com.gmail.furkanaxx34.tpa.handler;

import com.gmail.furkanaxx34.tpa.util.CooldownUtil;
import com.gmail.furkanaxx34.tpa.util.XColor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class TPHandler {

  @NotNull
  private final static HashMap<UUID, UUID> requests = new HashMap<>();

  public void acceptRequest(@NotNull Player player) {
    final var uniqueId = player.getUniqueId();
    final var uuid = requests.get(uniqueId);
    if (uuid == null) {
      player.sendMessage(XColor.colorize("&eNobody sent you a teleport request."));
      return;
    }
    final var target = Bukkit.getPlayer(uuid);
    if (target == null || !target.isOnline()) {
      player.sendMessage(XColor.colorize("&eNobody sent you a teleport request."));
      requests.remove(uniqueId);
      return;
    }
    target.teleport(player);
    target.sendMessage(XColor.colorize("&a%s has accepted your teleport request.".formatted(player.getName())));
    player.sendMessage(XColor.colorize("&aYou have accepted %s's teleport request.".formatted(target.getName())));
  }

  public void denyRequest(@NotNull Player player) {
    final var uniqueId = player.getUniqueId();
    final var uuid = requests.get(uniqueId);
    if (uuid == null) {
      player.sendMessage(XColor.colorize("&eNobody sent you a teleport request."));
      return;
    }
    final var target = Bukkit.getPlayer(uuid);
    if (target == null || !target.isOnline()) {
      player.sendMessage(XColor.colorize("&eNobody sent you a teleport request."));
      requests.remove(uniqueId);
      return;
    }
    requests.remove(uniqueId);
    player.sendMessage(XColor.colorize("&aYou refused the teleport request of %s.".formatted(target.getName())));
    target.sendMessage(XColor.colorize("&eA player named %s refused your teleport request.".formatted(player.getName())));
  }

  public void handleQuit(@NotNull Player player) {
    Set<UUID> willRemove = new HashSet<>();
    for (UUID uuid : requests.keySet()) {
      if (requests.get(uuid).equals(player.getUniqueId())) {
        willRemove.add(uuid);
      }
    }
    willRemove.forEach(requests::remove);
  }

  public boolean hasSent(@NotNull Player from, @NotNull Player to) {
    return requests.getOrDefault(to.getUniqueId(), UUID.randomUUID()).equals(from.getUniqueId()) && CooldownUtil.check(from, "%s-%s".formatted(from.getUniqueId(), to.getUniqueId()), 5000);
  }

  public void sendRequest(@NotNull Player from, @NotNull Player to) {
    requests.put(to.getUniqueId(), from.getUniqueId());
  }
}
