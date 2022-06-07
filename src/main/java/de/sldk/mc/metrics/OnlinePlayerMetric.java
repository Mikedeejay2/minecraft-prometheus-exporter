package de.sldk.mc.metrics;

import io.prometheus.client.Collector;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class OnlinePlayerMetric extends Metric {

    public OnlinePlayerMetric(Plugin plugin, Collector collector) {
        super(plugin, collector);
    }

    @Override
    public final void doCollect() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            collect(player);
        }
    }

    protected abstract void collect(Player player);

    protected String getUid(Player player) {
        return player.getUniqueId().toString();
    }

    protected String getName(Player player) {
        return player.getName();
    }

}
