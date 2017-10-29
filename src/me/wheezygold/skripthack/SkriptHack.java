package me.wheezygold.skripthack;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.lang.reflect.Field;

public class SkriptHack extends JavaPlugin implements Listener {

    private static SkriptHack instance;

    @Override
    public void onEnable() {
        instance = this;
        if (getServer().getPluginManager().getPlugin("Skript")!=null) {
            log("Starting Metrics...");
            Metrics metrics = new Metrics(this);
            metrics.addCustomChart(new Metrics.SimplePie("skript_version", () -> Bukkit.getServer().getPluginManager().getPlugin("Skript").getDescription().getVersion()));
            log("Loaded Metrics!");
            try {
                Skript.registerAddon(this).loadClasses("me.wheezygold.skripthack", "skript");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log("We cannot hack skript when skript is not loaded.");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("skript-hack")) {
            if (args.length >= 1) {
                if (sender instanceof Player) {
                    final Player p = (Player) sender;
                    if (args[0].equalsIgnoreCase("true")) {
                        p.sendMessage(ChatColor.BLUE + "skript-hack >> " + ChatColor.GRAY + "Skript's registration system has been unlocked.");
                        try {
                            hackSkript(true);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return true;
                    } else if (args[0].equalsIgnoreCase("false")) {
                        p.sendMessage(ChatColor.BLUE + "skript-hack >> " + ChatColor.GRAY + "Skript's registration system has been locked.");
                        try {
                            hackSkript(false);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return true;
                    } else {
                        return false;
                    }

                } else {
                    if (args[0].equalsIgnoreCase("true")) {
                        System.out.println(ChatColor.BLUE + "skript-hack >> " + ChatColor.GRAY + "Skript's registration system has been unlocked.");
                        return true;
                    } else if (args[0].equalsIgnoreCase("false")) {
                        System.out.println(ChatColor.BLUE + "skript-hack >> " + ChatColor.GRAY + "Skript's registration system has been locked.");
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private void log(String message) {
        System.out.println("[skript-hack] " + message);
    }

    public static SkriptHack getInstace() {
        return instance;
    }

    public void hackSkript(boolean allowReg) throws NoSuchFieldException, IllegalAccessException {
        Field field;
        field = Skript.class.getDeclaredField("acceptRegistrations");
        field.setAccessible(true);
        field.set(null, allowReg);
        log("Skript's Registrations was hacked to: " + String.valueOf(allowReg));
    }

    @SuppressWarnings("unused")
    public static void enableRegistrations() throws NoSuchFieldException, IllegalAccessException {
        instance.hackSkript(true);
    }

    @SuppressWarnings("unused")
    public static void disableRegistrations() throws NoSuchFieldException, IllegalAccessException {
        instance.hackSkript(false);
    }

}
