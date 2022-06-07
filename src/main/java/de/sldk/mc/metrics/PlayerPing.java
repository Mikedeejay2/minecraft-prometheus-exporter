package de.sldk.mc.metrics;

import io.prometheus.client.Gauge;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

public class PlayerPing extends PlayerMetric {
    private static final Gauge PLAYER_PING = Gauge.build()
        .name(prefix("players_ping"))
        .help("Online player ping")
        .labelNames("name", "uid")
        .create();

    public PlayerPing(Plugin plugin) {
        super(plugin, PLAYER_PING);
    }

    @Override
    protected void collect(OfflinePlayer player) {
        if(!player.isOnline() || player.getPlayer() == null) return;
        PLAYER_PING.labels(getNameOrUid(player), getUid(player)).set(player.getPlayer().getPing());
    }
}
