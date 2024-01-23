package de.h3xabyt3.ultimatetablist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Tablist {
    public static void SetTablist() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String players = String.valueOf(Bukkit.getOnlinePlayers().size());
            //Save all online players to players variable
            try {
                //Set header, replace "{players}" with player count
                player.setPlayerListHeader(ChatColor.translateAlternateColorCodes('&', UltimateTablist.instance.HEADER.replace("{players}", players)
                        //Set header, replace "{time}" with time HH.mm
                        .replace("{time}", TimeUtil.getTime(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId())))
                        //Set header, replace "{seconds}" with seconds ss
                        .replace("{seconds}", TimeUtil.getSeconds(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId())))
                        //Set header, replace "{date}" with current date dd.MM.yyyy
                        .replace("{date}", TimeUtil.getDate(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId())))
                        //Set header, replace "{weekday}" with weekday EEE
                        .replace("{weekday}", TimeUtil.getWeekDay(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId())))
                        //Set header, replace "{month} with month MMMM
                        .replace("{month}", TimeUtil.getMonth(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId())))));
                //Set footer, replace "{players}" with player count
                player.setPlayerListFooter(ChatColor.translateAlternateColorCodes('&', UltimateTablist.instance.FOOTER.replace("{players}", players)
                        //Set footer, replace "{time}" with time HH.mm
                        .replace("{time}", TimeUtil.getTime(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId()))
                        //Set footer, replace "{seconds}" with seconds ss
                        .replace("{seconds}", TimeUtil.getSeconds(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId())))
                        //Set footer, replace "{date}" with current date dd.MM.yyyy
                        .replace("{date}", TimeUtil.getDate(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId())))
                        //Set footer, replace "{weekday}" with weekday EEE
                        .replace("{weekday}", TimeUtil.getWeekDay(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId())))
                        //Set header, replace "{month} with month MMMM
                        .replace("{month}", TimeUtil.getMonth(UltimateTablist.instance.PlayerTimezones.get(player.getUniqueId()))))));
            } catch (Exception e) {
                //Catch possible exception, most likeley means the timezone was misspelled, printStackTrace(); just in case
                System.out.println(ChatColor.RED + "--------------------!!!IF THIS SHOWS UP, YOU MISSPELLED YOUR TIMEZONE!!!--------------------");
                e.printStackTrace();
                //Disable scheduler
                Bukkit.getScheduler().cancelTasks(UltimateTablist.instance);
                //Disable plugin
                UltimateTablist.instance.getServer().getPluginManager().disablePlugin(UltimateTablist.instance);
            }
        }
    }
}