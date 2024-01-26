package de.h3xabyt3.ultimatetablist;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        //Save player to player variable
        Player player = event.getPlayer();
        //Check whether to use the ip of the player to get the timezone or to use the timezone in the config
        try {
            if (UltimateTablist.instance.USEPLAYERTIMEZONES) UltimateTablist.instance.PlayerTimezones.put(player.getUniqueId(), getZoneFromIpAddress(String.valueOf(player.getAddress()), UltimateTablist.instance.IPDATAKEY));
            if (!UltimateTablist.instance.USEPLAYERTIMEZONES) UltimateTablist.instance.PlayerTimezones.put(player.getUniqueId(), UltimateTablist.instance.TIMEZONE);
        } catch (Exception e) {
            //Exception can really only be wrong apikey
            System.out.println("--------------------!!!IF THIS SHOWS UP, YOU MISSPELLED YOUR APIKEY!!!--------------------");
            //Turn off scheduler
            Bukkit.getScheduler().cancelTasks(UltimateTablist.instance);
            //Disable plugin
            UltimateTablist.instance.getServer().getPluginManager().disablePlugin(UltimateTablist.instance);
        }
    }

    //I don't know how the code below works, I copied it
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public String getZoneFromIpAddress(String ip, String apikey) throws IOException{
        String zone;
        JSONObject json = readJsonFromUrl("https://api.ipdata.co/"+ip+"?api-key=" + apikey);
        zone = String.valueOf(json.get("time_zone")).split("\"") [7];
        System.out.println(zone);
        zone = zone.split("\"") [0];
        System.out.println(zone);
        return zone;
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        InputStream is = new URL(url).openStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } finally {
            is.close();
        }
    }
}