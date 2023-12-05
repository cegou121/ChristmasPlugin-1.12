package org.example.ua.itristan.christmasplugiт

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChristmasPlugin extends JavaPlugin implements Listener {

    private boolean snowEffectEnabled = true;

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("snow")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                snowFallEffect(player.getLocation());
            } else {
                sender.sendMessage("This command can only be executed by a player.");
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("snows")) {
            snowEffectEnabled = !snowEffectEnabled;
            sender.sendMessage("Snow effect " + (snowEffectEnabled ? "enabled" : "disabled"));
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (snowEffectEnabled) {
            snowFallEffect(event.getPlayer().getLocation());
        }
    }

    private void snowFallEffect(Location location) {
        location.getWorld().spawnFallingBlock(location, Material.SNOW, (byte) 0);
    }
}
