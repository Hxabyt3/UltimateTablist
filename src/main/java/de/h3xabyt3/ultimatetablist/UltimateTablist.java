package de.h3xabyt3.ultimatetablist;

import io.ipinfo.api.IPinfo;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.UUID;

public final class UltimateTablist extends JavaPlugin {

    public static UltimateTablist instance;

    public String HEADER;
    public String FOOTER;

    public HashMap<UUID, String> PlayerIPs = new HashMap<UUID, String>();

    public IPinfo ipInfo;

    @Override
    public void onEnable() {
        ipInfo = new IPinfo.Builder()
                .setToken("4fd0a3dc68a736")
                .build();
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
        createCustomConfig();
        saveDefaultConfig();
        HEADER = UltimateTablist.instance.getConfig().getString("header");
        FOOTER = UltimateTablist.instance.getConfig().getString("footer");
        if (HEADER.contains("{time}") || (HEADER.contains("{seconds}") || (HEADER.contains("{date}")) || (HEADER.contains("{weekday}")) || (HEADER.contains("{month}")) ||FOOTER.contains("{time}") || (FOOTER.contains("{seconds}") || (FOOTER.contains("{date}")) || (FOOTER.contains("{weekday}")) || (FOOTER.contains("{month}"))|| FOOTER.contains("{tps}")))) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::run, 0, 20);
        }
    }

    private void run() {
        Tablist.SetTablist();
    }

    private void createCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}