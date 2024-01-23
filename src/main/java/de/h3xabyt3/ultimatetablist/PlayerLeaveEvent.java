package de.h3xabyt3.ultimatetablist;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerLeaveEvent implements Listener {
    @EventHandler
    public void onPlayerLeave(org.bukkit.event.player.PlayerQuitEvent event) {
        //Remove player from PlayerTimezones hashmap
        UltimateTablist.instance.PlayerTimezones.remove(event.getPlayer().getUniqueId());
    }
}