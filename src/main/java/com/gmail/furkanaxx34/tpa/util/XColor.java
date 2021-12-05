package com.gmail.furkanaxx34.tpa.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class XColor {

  public String colorize(@NotNull String str) {
    return ChatColor.translateAlternateColorCodes('&', str);
  }

}
