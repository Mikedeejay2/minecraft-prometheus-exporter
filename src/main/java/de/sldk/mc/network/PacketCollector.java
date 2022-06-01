package de.sldk.mc.network;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.*;
import com.comphenix.protocol.injector.packet.PacketRegistry;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class PacketCollector {
    private static final ProtocolManager protocol = ProtocolLibrary.getProtocolManager();

    private final Map<PacketType, Integer> serverPackets = new HashMap<>();
    private final Map<PacketType, Integer> clientPackets = new HashMap<>();
    private static final BiFunction<PacketType, Integer, Integer> INCREMENT = (type, count) -> ++count;
    private static final BiFunction<PacketType, Integer, Integer> ZERO = (type, count) -> 0;

    public PacketCollector(Plugin plugin) {
        PacketRegistry.getClientPacketTypes().forEach(type -> clientPackets.put(type, 0));
        PacketRegistry.getServerPacketTypes().forEach(type -> serverPackets.put(type, 0));

        Set<PacketType> clientPacketTypes = PacketRegistry.getClientPacketTypes();
        clientPacketTypes.remove(PacketType.Status.Client.PING);
        protocol.addPacketListener(new PacketAdapter(
            plugin, ListenerPriority.HIGHEST,
            clientPacketTypes) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                clientPackets.computeIfPresent(event.getPacket().getType(), INCREMENT);
            }
        });

        Set<PacketType> serverPacketTypes = PacketRegistry.getServerPacketTypes();
        serverPacketTypes.remove(PacketType.Status.Server.PONG);
        protocol.addPacketListener(new PacketAdapter(
            plugin, ListenerPriority.HIGHEST,
            serverPacketTypes) {
            @Override
            public void onPacketSending(PacketEvent event) {
                serverPackets.computeIfPresent(event.getPacket().getType(), INCREMENT);
            }
        });
    }

    public Map<PacketType, Integer> getServerPackets() {
        return serverPackets;
    }

    public Map<PacketType, Integer> getClientPackets() {
        return clientPackets;
    }

    public void postCollect() {
        PacketRegistry.getClientPacketTypes().forEach(type -> clientPackets.compute(type, ZERO));
        PacketRegistry.getServerPacketTypes().forEach(type -> serverPackets.compute(type, ZERO));
    }
}
