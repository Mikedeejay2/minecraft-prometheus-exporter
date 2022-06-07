package de.sldk.mc.metrics;

import io.prometheus.client.Gauge;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

public class PlayerViewDistance extends PlayerMetric {
    private static final Gauge PLAYER_VIEW_DISTANCE = Gauge.build()
        .name(prefix("players_view_distance"))
        .help("Online player view distance")
        .labelNames("name", "uid")
        .create();

    public PlayerViewDistance(Plugin plugin) {
        super(plugin, PLAYER_VIEW_DISTANCE);
    }

    @Override
    protected void collect(OfflinePlayer player) {
        if(!player.isOnline() || player.getPlayer() == null) return;
        PLAYER_VIEW_DISTANCE.labels(getNameOrUid(player), getUid(player)).set(player.getPlayer().getClientViewDistance());
    }
}
