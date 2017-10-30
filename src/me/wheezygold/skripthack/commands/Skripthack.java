package me.wheezygold.skripthack.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.wheezygold.skripthack.SkriptHack.hackSkript;

/**
 * Created by Blitz on 10/30/2017.
 */
public class Skripthack implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("true")) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.BLUE + "skript-hack >> " + ChatColor.GRAY + "Skript's registration system has been unlocked.");
                } else {
                    System.out.println(ChatColor.BLUE + "skript-hack >> " + ChatColor.GRAY + "Skript's registration system has been unlocked.");
                    return true;
                }
                try {
                    hackSkript(true);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return true;
            } else if (args[0].equalsIgnoreCase("false")) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.sendMessage(ChatColor.BLUE + "skript-hack >> " + ChatColor.GRAY + "Skript's registration system has been locked.");
                } else {
                    System.out.println(ChatColor.BLUE + "skript-hack >> " + ChatColor.GRAY + "Skript's registration system has been locked.");
                }
                try {
                    hackSkript(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.BLUE + "skript-hack >> " + ChatColor.GRAY + "Invalid command usage! Refer to command arguments: /skript-hack <true|false>");
            return true;
        }
        sender.sendMessage(ChatColor.BLUE + "skript-hack >> " + ChatColor.GRAY + "Invalid command usage! Refer to command arguments: /skript-hack <true|false>");
        return true;
    }
}
