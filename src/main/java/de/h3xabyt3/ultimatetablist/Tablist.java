package de.h3xabyt3.ultimatetablist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Tablist {
    public static void SetTablist() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String players = String.valueOf(Bukkit.getOnlinePlayers().size());
            try {
                player.setPlayerListHeader(ChatColor.translateAlternateColorCodes('&', UltimateTablist.instance.HEADER.replace("{players}", players).replace("{time}", TimeUtil.getTime(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId()))).replace("{seconds}", TimeUtil.getSeconds(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId()))).replace("{date}", TimeUtil.getDate(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId()))).replace("{weekday}", TimeUtil.getWeekDay(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId()))).replace("{month}", TimeUtil.getMonth(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId())))));
                player.setPlayerListFooter(ChatColor.translateAlternateColorCodes('&', UltimateTablist.instance.FOOTER.replace("{players}", players).replace("{time}", TimeUtil.getTime(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId())).replace("{seconds}", TimeUtil.getSeconds(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId()))).replace("{date}", TimeUtil.getDate(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId()))).replace("{weekday}", TimeUtil.getWeekDay(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId()))).replace("{month}", TimeUtil.getMonth(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId()))))));
            } catch (Exception e) {
                System.out.println("--------------------!!!IF THIS SHOWS UP, YOU MISSPELLED YOUR TIMEZONE!!!--------------------");
                e.printStackTrace();
                Bukkit.getScheduler().cancelTasks(UltimateTablist.instance);
                UltimateTablist.instance.getServer().getPluginManager().disablePlugin(UltimateTablist.instance);
            }
        }
    }

}