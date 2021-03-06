package com.gmail.furkanaxx34.tpa.util;

import java.util.HashMap;
import org.bukkit.entity.Player;

public class CooldownUtil {

  private static final HashMap<String, Long> countdown = new HashMap<>();

  public static boolean check(Player player, String action, long delay) {
    final var key = "%s-%s".formatted(player.getUniqueId(), action);
    final var now = System.currentTimeMillis();
    if (!countdown.containsKey(key)) {
      countdown.put(key, now + delay);
      return true;
    }
    final var timeMillis = countdown.get(key);
    if (now >= timeMillis) {
      countdown.remove(key);
      return false;
    }
    return false;
  }
}
