package de.sldk.mc.network;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.*;
import de.sldk.mc.PrometheusExporter;
import de.sldk.mc.metrics.Network;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import io.netty.handler.traffic.GlobalChannelTrafficCounter;
import io.netty.handler.traffic.GlobalChannelTrafficShapingHandler;
import io.netty.handler.traffic.TrafficCounter;

import java.util.concurrent.ScheduledExecutorService;

public class NetworkCollector {
    private final PrometheusExporter plugin;
    private static final ProtocolManager protocol = ProtocolLibrary.getProtocolManager();

    public NetworkCollector(PrometheusExporter plugin) {
        this.plugin = plugin;
//
//        protocol.addPacketListener(new PacketAdapter(
//            plugin, ListenerPriority.HIGHEST,
//            PacketType.Play.Server.getInstance()) {
//            @Override
//            public void onPacketSending(PacketEvent event) {
//                PacketContainer container = event.getPacket();
//                container.getStructures()
//            }
//        });

//        ChannelTrafficShapingHandler myHandler = new ChannelTrafficShapingHandler(1000);
//        ProtocolLib

    }


}
