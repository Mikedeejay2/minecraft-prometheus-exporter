package de.sldk.mc.metrics;

import io.prometheus.client.Gauge;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerPing extends OnlinePlayerMetric {
    private static final Gauge PLAYER_PING = Gauge.build()
        .name(prefix("player_ping"))
        .help("Online player ping")
        .labelNames("name", "uid")
        .create();

    public PlayerPing(Plugin plugin) {
        super(plugin, PLAYER_PING);
    }

    @Override
    public void collect() {
        PLAYER_PING.clear();
        super.collect();
    }

    @Override
    protected void collect(Player player) {
        PLAYER_PING.labels(getName(player), getUid(player)).set(player.getPing());
    }
}
