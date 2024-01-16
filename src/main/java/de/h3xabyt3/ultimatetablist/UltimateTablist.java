package de.h3xabyt3.ultimatetablist;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public final class UltimateTablist extends JavaPlugin {

    public static UltimateTablist instance;
    public String HEADER;
    public String FOOTER;
    public boolean LOCALHOST;
    public HashMap<UUID, String> PlayerTimezones= new HashMap<>();
    @Override
    public void onEnable() {
        //----Instance----
        UltimateTablist.instance = this;
        //----Events----
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
        //----Create Config----
        createCustomConfig();
        saveDefaultConfig();
        //----Load Config----
        if (UltimateTablist.instance.getConfig().getString("header") != null) {HEADER = UltimateTablist.instance.getConfig().getString("header");}
        if (UltimateTablist.instance.getConfig().getString("footer") != null) {FOOTER = UltimateTablist.instance.getConfig().getString("footer");}
        LOCALHOST = UltimateTablist.instance.getConfig().getBoolean("localhost");
        //----Scheduler----
        if (HEADER != null && FOOTER != null) {Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::run, 0, 20);}
        //----Console Advertisement----
        Ad();
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

    private void Ad(){
        System.out.println("-");
        System.out.println("-");
        System.out.println("Consider leaving a review for UltimateTablist at https://www.planetminecraft.com/mod/ultimatetablist/ or at https://www.spigotmc.org/resources/ultimatetablist.103538/");
        System.out.println("-");
        System.out.println("-");
    }
}