package com.gmail.furkanaxx34.tpa.util;

import java.util.function.Consumer;
import java.util.function.Predicate;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public final class RunnableWrapper extends BukkitRunnable {
    @NotNull
    private final Consumer<BukkitRunnable> consumer;
    @NotNull
    private final Predicate<BukkitRunnable> predicate;

    public RunnableWrapper(@NotNull Consumer<BukkitRunnable> consumer) {
        this(consumer, (runnable) -> {
            return true;
        });
    }

    public void run() {
        if (this.predicate.test(this)) {
            this.consumer.accept(this);
        }

    }

    public RunnableWrapper(@NotNull Consumer<BukkitRunnable> consumer, @NotNull Predicate<BukkitRunnable> predicate) {
        this.consumer = consumer;
        this.predicate = predicate;
    }
}
