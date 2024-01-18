package de.h3xabyt3.ultimatetablist;

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
        Player player = event.getPlayer();
        try {
            UltimateTablist.instance.PlayerTimezones.put(player.getUniqueId(), (UltimateTablist.instance.LOCALHOST || UltimateTablist.instance.IPDATAKEY == null ? UltimateTablist.instance.TIMEZONE : getZoneFromIpAddress(String.valueOf(player.getAddress()), UltimateTablist.instance.IPDATAKEY)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public String getZoneFromIpAddress(String ip, String apikey) throws IOException {
        String zone = "inconnu";
        try {
            JSONObject json = readJsonFromUrl("https://api.ipdata.co/"+ip+"?api-key=" + apikey);
            zone = String.valueOf(json.get("time_zone")).split("\"") [7];
            System.out.println(zone);
            zone = zone.split("\"") [0];
            System.out.println(zone);
        } catch (Exception e) {
            e.printStackTrace();
        }
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