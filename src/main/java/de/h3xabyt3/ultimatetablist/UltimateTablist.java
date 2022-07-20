package de.h3xabyt3.ultimatetablist;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class UltimateTablist extends JavaPlugin {

    public static UltimateTablist instance;

    public String HEADER;
    public String FOOTER;
    public String TIME;
    public String SECONDS;
    public String DATE;
    public String WEEKDAY;
    public String MONTH;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
        createCustomConfig();
        saveDefaultConfig();
        HEADER = UltimateTablist.instance.getConfig().getString("header");
        FOOTER = UltimateTablist.instance.getConfig().getString("footer");
        if (HEADER.contains("{time}") || (HEADER.contains("{seconds}") || (HEADER.contains("{date}")) || (HEADER.contains("{weekday}")) || (HEADER.contains("{month}")) ||FOOTER.contains("{time}") || (FOOTER.contains("{seconds}") || (FOOTER.contains("{date}")) || (FOOTER.contains("{weekday}")) || (FOOTER.contains("{month}"))))) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
                run();
            }, 0, 20);
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