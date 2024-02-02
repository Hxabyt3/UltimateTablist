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
    //Create the instance
    public static UltimateTablist instance;
    //Create all the config variables
    public String HEADER;
    public String FOOTER;
    public String IPDATAKEY;
    public String TIMEZONE;
    public boolean LOCALHOST;
    //Boolean for time system, to tell whether to use the api or not
    public boolean USEPLAYERTIMEZONES;
    //Hashmap to save the players UUID + Timezone
    public HashMap<UUID, String> PlayerTimezones= new HashMap<>();
    @Override
    public void onEnable() {
        //Set the instance
        UltimateTablist.instance = this;
        //Register events
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveEvent(), this);
        //If the plugin has been installed for the first time, these functions will create the default config
        createCustomConfig();
        saveDefaultConfig();
        //Null-check and set all the config variables
        if (UltimateTablist.instance.getConfig().getString("header") != null) {HEADER = UltimateTablist.instance.getConfig().getString("header");}
        if (UltimateTablist.instance.getConfig().getString("footer") != null) {FOOTER = UltimateTablist.instance.getConfig().getString("footer");}
        if (UltimateTablist.instance.getConfig().getString("ipdatakey") != null) {IPDATAKEY = UltimateTablist.instance.getConfig().getString("ipdatakey");}
        if (UltimateTablist.instance.getConfig().getString("timezone") != null) {TIMEZONE = UltimateTablist.instance.getConfig().getString("timezone");}
        LOCALHOST = isLocalhostServer();
        //Check if the plugin should run off a timezone or an APIKey
        USEPLAYERTIMEZONES = usePlayerTimezones(IPDATAKEY, TIMEZONE, LOCALHOST);
        //Kick all players, to avoid NullPointerExceptions
        kickallPlayers();
        //Set the scheduler to set the tablist every tick
        if (HEADER != null && FOOTER != null) {Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::run, 0, 20);}
    }
    //Scheduler to set the tablist every tick
    private void run() {
        Tablist.SetTablist();
    }

    //Creates the default config if no config is existent
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
    //Returns whether to use the players IP to get their timezone or to use the one from the config
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

    private boolean isLocalhostServer() {
        try {
            Bukkit.getServer().getIp();
            java.net.InetAddress serverAddress = java.net.InetAddress.getByName(Bukkit.getServer().getIp());

            return serverAddress.isLoopbackAddress() || serverAddress.getHostAddress().equals("127.0.0.1");
        } catch (java.net.UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }
}