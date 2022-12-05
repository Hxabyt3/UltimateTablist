package de.h3xabyt3.ultimatetablist;

import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {

        try {
            IPResponse response = UltimateTablist.instance.ipInfo.lookupIP(event.getPlayer().getAddress().toString().replace("/", ""));
            String zone = response.getTimezone();
            System.out.println("Player " + event.getPlayer() + " connected from " + zone);
        } catch (RateLimitedException ex) {
            UltimateTablist.instance.getServer().getConsoleSender().sendMessage("§cIPinfo :: RateLimitedExeption , Please contact H3xabyt3 immediatly");
        }
        Tablist.SetTablist();
    }
}