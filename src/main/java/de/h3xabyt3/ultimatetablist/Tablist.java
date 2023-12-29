package de.h3xabyt3.ultimatetablist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Tablist {

    public static void SetTablist() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String players = String.valueOf(Bukkit.getOnlinePlayers().size());
            player.setPlayerListHeader(ChatColor.translateAlternateColorCodes('&', UltimateTablist.instance.HEADER.replace("{players}", players).replace("{time}", TimeUtil.getTime(UltimateTablist.instance.PlayerIPs.get(player.getUniqueId()))).replace("{seconds}", TimeUtil.getSeconds(UltimateTablist.instance.PlayerIPs.get(player.getUniqueId()))).replace("{date}", TimeUtil.getDate(UltimateTablist.instance.PlayerIPs.get(player.getUniqueId()))).replace("{weekday}", TimeUtil.getWeekDay(UltimateTablist.instance.PlayerIPs.get(player.getUniqueId()))).replace("{month}", TimeUtil.getMonth(UltimateTablist.instance.PlayerIPs.get(player.getUniqueId())))));
            player.setPlayerListFooter(ChatColor.translateAlternateColorCodes('&', UltimateTablist.instance.FOOTER.replace("{players}", players).replace("{time}", TimeUtil.getTime(UltimateTablist.instance.PlayerIPs.get(player.getUniqueId())).replace("{seconds}", TimeUtil.getSeconds(UltimateTablist.instance.PlayerIPs.get(player.getUniqueId()))).replace("{date}", TimeUtil.getDate(UltimateTablist.instance.PlayerIPs.get(player.getUniqueId()))).replace("{weekday}", TimeUtil.getWeekDay(UltimateTablist.instance.PlayerIPs.get(player.getUniqueId()))).replace("{month}", TimeUtil.getMonth(UltimateTablist.instance.PlayerIPs.get(player.getUniqueId()))))));
        }
    }
}
