package de.h3xabyt3.ultimatetablist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class UltimateTablist extends JavaPlugin {

    public static UltimateTablist instance;
    public String HEADER;
    public String FOOTER;
    public String IPDATAKEY;
    public String TIMEZONE;
    public boolean LOCALHOST;
    public boolean USEPLAYERTIMEZONES;
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
        if (UltimateTablist.instance.getConfig().getString("ipdatakey") != null) {IPDATAKEY = UltimateTablist.instance.getConfig().getString("ipdatakey");}
        if (UltimateTablist.instance.getConfig().getString("timezone") != null) {TIMEZONE = UltimateTablist.instance.getConfig().getString("timezone");}
        try {
            LOCALHOST = UltimateTablist.instance.getConfig().getBoolean("localhost");
        } catch (NullPointerException Ignored) {
            System.out.println("Configuration Error: You must specifiy wether the Server is a LocalHost or not.");
            getServer().getPluginManager().disablePlugin(this);
        }
        USEPLAYERTIMEZONES = usePlayerTimezones(IPDATAKEY, TIMEZONE, LOCALHOST);
        //----Kick Players----
        kickallPlayers();
        //----Scheduler----
        if (HEADER != null && FOOTER != null) {Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::run, 0, 20);}
    }

    private void run() {
        Tablist.SetTablist();
    }

    //----Create Default Config----
    private void createCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            saveResource("config.yml", false);
        }

        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public boolean usePlayerTimezones(String IPDATAKEY, String TIMEZONE, Boolean LOCALHOST) {
        if (LOCALHOST) {
            if (Objects.equals(TIMEZONE, "")) {
                System.out.println("-\n-\nConfiguration Error: You must specify a Timezone when using a LocalHost.\n-\n-");
                getServer().getPluginManager().disablePlugin(this);
            }
            return false;
        } else {
            if (Objects.equals(TIMEZONE, "") && Objects.equals(IPDATAKEY, "")) {
                System.out.println("-\n-\nConfiguration Error: You must specify a Timezone or an IpdataKey.\n-\n-");
                getServer().getPluginManager().disablePlugin(this);
                return false;
            }
            if (!Objects.equals(IPDATAKEY, "")) return true;
            if (!Objects.equals(TIMEZONE, "")) return false;
        }
        return true;
    }
    
    private void kickallPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer(ChatColor.translateAlternateColorCodes('§', "§6[§5Ultimate§9Tablist§6] §fKicked all players to avoid errors."));
        }
    }
}