package de.sldk.mc.metrics;

import io.prometheus.client.Info;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class ServerInfo extends Metric {

    private static final Info INFO = Info.build()
            .name(prefix("server"))
            .help("Server Information")
            .create();

    private final Map<String, String> serverInfo;

    public ServerInfo(Plugin plugin) {
        super(plugin, INFO);
        this.serverInfo = new HashMap<>();
        serverInfo.put("server_port", String.valueOf(Bukkit.getPort()));
        serverInfo.put("bukkit_version", Bukkit.getBukkitVersion());
        serverInfo.put("server_name", Bukkit.getName());
        serverInfo.put("server_motd", Bukkit.getMotd());
        serverInfo.put("server_ip", Bukkit.getIp());
    }

    @Override
    public void doCollect() {
        INFO.info(serverInfo);
    }
}
