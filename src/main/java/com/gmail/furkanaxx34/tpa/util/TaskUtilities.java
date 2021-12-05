package com.gmail.furkanaxx34.tpa.util;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TaskUtilities {
  @Nullable
  private static Plugin plugin;

  @NotNull
  public static BukkitTask async(@NotNull Consumer<BukkitRunnable> job) {
    return (new RunnableWrapper(job)).runTaskAsynchronously(getPlugin());
  }

  @NotNull
  public static BukkitTask asyncLater(long delay, @NotNull Consumer<BukkitRunnable> job) {
    return (new RunnableWrapper(job)).runTaskLaterAsynchronously(getPlugin(), delay);
  }

  @NotNull
  public static BukkitTask asyncTimer(long period, @NotNull Consumer<BukkitRunnable> job) {
    return asyncTimerLater(0L, period, job);
  }

  @NotNull
  public static BukkitTask asyncTimer(long period, @NotNull Predicate<BukkitRunnable> job) {
    return asyncTimerLater(0L, period, job);
  }

  @NotNull
  public static BukkitTask asyncTimerLater(long delay, long period, @NotNull Predicate<BukkitRunnable> job) {
    return (new RunnableWrapper(BukkitRunnable::cancel, job)).runTaskTimerAsynchronously(getPlugin(), delay, period);
  }

  @NotNull
  public static BukkitTask asyncTimerLater(long delay, long period, @NotNull Consumer<BukkitRunnable> job) {
    return (new RunnableWrapper(job)).runTaskTimerAsynchronously(getPlugin(), delay, period);
  }

  public static void init(@NotNull Plugin plugin) {
    TaskUtilities.plugin = plugin;
  }

  @NotNull
  public static BukkitTask sync(@NotNull Consumer<BukkitRunnable> job) {
    return (new RunnableWrapper(job)).runTask(getPlugin());
  }

  @NotNull
  public static BukkitTask syncLater(long delay, @NotNull Consumer<BukkitRunnable> job) {
    return (new RunnableWrapper(job)).runTaskLater(getPlugin(), delay);
  }

  @NotNull
  public static BukkitTask syncTimer(long period, @NotNull Consumer<BukkitRunnable> job) {
    return syncTimerLater(0L, period, job);
  }

  @NotNull
  public static BukkitTask syncTimer(long period, @NotNull Predicate<BukkitRunnable> job) {
    return syncTimerLater(0L, period, job);
  }

  @NotNull
  public static BukkitTask syncTimerLater(long delay, long period, @NotNull Predicate<BukkitRunnable> job) {
    return (new RunnableWrapper(BukkitRunnable::cancel, job)).runTaskTimer(getPlugin(), delay, period);
  }

  @NotNull
  public static BukkitTask syncTimerLater(long delay, long period, @NotNull Consumer<BukkitRunnable> job) {
    return (new RunnableWrapper(job)).runTaskTimer(getPlugin(), delay, period);
  }

  @NotNull
  private static Plugin getPlugin() {
    return (Plugin) Optional.ofNullable(plugin).orElseThrow(() -> {
      return new RuntimeException("Use TaskUtilities#init(Plugin) first!");
    });
  }

  private TaskUtilities() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }
}

