package de.h3xabyt3.ultimatetablist;

import io.ipinfo.api.IPinfo;
import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.TimeZone;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {

        try {
            IPResponse response = UltimateTablist.instance.ipInfo.lookupIP(event.getPlayer().getAddress().toString().replace("/", ""));
            System.out.println("Player " + event.getPlayer() + " connected from " + TimeZone.getTimeZone(response.getTimezone()));
            UltimateTablist.instance.PlayerIPs.put(event.getPlayer().getUniqueId(), response.getTimezone());
        } catch (RateLimitedException ex) {
            UltimateTablist.instance.getServer().getConsoleSender().sendMessage("§cIPinfo :: RateLimitedExeption , Please contact H3xabyt3 immediately");
        }
        Tablist.SetTablist();
    }
}