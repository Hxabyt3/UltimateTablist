package de.h3xabyt3.ultimatetablist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Tablist {

    public static void SetTablist() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String players = String.valueOf(Bukkit.getOnlinePlayers().size());
            player.setPlayerListHeader(ChatColor.translateAlternateColorCodes('&', UltimateTablist.instance.HEADER.replace("{players}", players).replace("{time}", TimeUtil.getTime(System.currentTimeMillis())).replace("{seconds}", TimeUtil.getSeconds(System.currentTimeMillis())).replace("{date}", TimeUtil.getDate(System.currentTimeMillis())).replace("{weekday}", TimeUtil.getWeekDay(System.currentTimeMillis())).replace("{month}", TimeUtil.getMonth(System.currentTimeMillis()))));
            player.setPlayerListFooter(ChatColor.translateAlternateColorCodes('&', UltimateTablist.instance.FOOTER.replace("{players}", players).replace("{time}", TimeUtil.getTime(System.currentTimeMillis())).replace("{seconds}", TimeUtil.getSeconds(System.currentTimeMillis())).replace("{date}", TimeUtil.getDate(System.currentTimeMillis())).replace("{weekday}", TimeUtil.getWeekDay(System.currentTimeMillis())).replace("{month}", TimeUtil.getMonth(System.currentTimeMillis()))));
        }
    }
}
