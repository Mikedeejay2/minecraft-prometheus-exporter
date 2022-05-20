package de.sldk.mc.metrics;

import com.comphenix.protocol.PacketType;
import de.sldk.mc.network.PacketCollector;
import io.prometheus.client.Counter;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class Packets extends Metric {

    private static final Counter PACKETS = Counter.build()
            .name(prefix("packets"))
            .help("Minecraft packets")
            .labelNames("side", "type")
            .create();

    private final PacketCollector networkCollector;

    public Packets(Plugin plugin) {
        super(plugin, PACKETS);
        this.networkCollector = new PacketCollector(plugin);
    }

    @Override
    public void doCollect() {
        for(Map.Entry<PacketType, Integer> entry : networkCollector.getClientPackets().entrySet()) {
            PACKETS.labels("client", entry.getKey().name()).inc(entry.getValue());
        }

        for(Map.Entry<PacketType, Integer> entry : networkCollector.getServerPackets().entrySet()) {
            PACKETS.labels("server", entry.getKey().name()).inc(entry.getValue());
        }

        networkCollector.postCollect();
    }
}
