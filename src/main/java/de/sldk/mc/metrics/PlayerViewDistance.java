package de.sldk.mc.metrics;

import io.prometheus.client.Gauge;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerViewDistance extends OnlinePlayerMetric {
    private static final Gauge PLAYER_VIEW_DISTANCE = Gauge.build()
        .name(prefix("player_view_distance"))
        .help("Online player view distance")
        .labelNames("name", "uid")
        .create();

    public PlayerViewDistance(Plugin plugin) {
        super(plugin, PLAYER_VIEW_DISTANCE);
    }

    @Override
    public void collect() {
        PLAYER_VIEW_DISTANCE.clear();
        super.collect();
    }

    @Override
    protected void collect(Player player) {
        PLAYER_VIEW_DISTANCE.labels(getName(player), getUid(player)).set(player.getClientViewDistance());
    }
}
