package de.sldk.mc.metrics;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import io.prometheus.client.Gauge;
import org.bukkit.plugin.Plugin;

public class Network extends Metric {

    private static final Gauge NETWORK = Gauge.build()
            .name(prefix("network"))
            .help("Minecraft network usage")
            .labelNames("type")
            .create();

    public Network(Plugin plugin) {
        super(plugin, NETWORK);
    }

    @Override
    public void doCollect() {
        NETWORK.labels("sending").set(Runtime.getRuntime().maxMemory());
        NETWORK.labels("receiving").set(Runtime.getRuntime().freeMemory());
    }
}
