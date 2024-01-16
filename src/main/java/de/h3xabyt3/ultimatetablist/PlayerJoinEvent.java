package de.h3xabyt3.ultimatetablist;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        try {
            UltimateTablist.instance.PlayerTimezones.put(player.getUniqueId(), /*String.valueOf(player.getAddress())*/ getZoneFormIpAddress("93.204.0.72"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Tablist.SetTablist();
        try {
            System.out.println(getZoneFormIpAddress("93.204.0.72"));
            System.out.println(getZoneFormIpAddress("185.35.50.4"));
            System.out.println(getZoneFormIpAddress("219.118.201.119"));
            System.out.println(TimeUtil.getTime(getZoneFormIpAddress("93.204.0.72")));
            System.out.println(TimeUtil.getTime(getZoneFormIpAddress("185.35.50.4")));
            System.out.println(TimeUtil.getTime(getZoneFormIpAddress("219.118.201.119")));
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

    public String getZoneFormIpAddress(String ip) throws IOException {
        String zone = "inconnu";
        try {
            JSONObject json = readJsonFromUrl("https://api.ipdata.co/"+ip+"?api-key=116c250e011ab9e1c2071848989f87e33a4fa2bb3f2ed16d5c10cda9");
            zone = json.get("country_name")+"/"+json.get("city");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zone;
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        InputStream is = new URL(url).openStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}