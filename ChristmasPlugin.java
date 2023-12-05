package org.example.ua.itristan.christmasplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChristmasPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Регистрируем нашего слушателя
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("snow")) {
            startSnowfall();
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Запуск анимации падения снега при присоединении новых игроков (если активна)
        if (isSnowfallActive()) {
            startSnowfall(event.getPlayer().getWorld());
        }
    }

    private void startSnowfall() {
        // Запуск анимации падения снега для всех миров на сервере
        for (World world : Bukkit.getWorlds()) {
            startSnowfall(world);
        }
    }

    private void startSnowfall(World world) {
        Location snowfallLocation = getRandomLocation(world);
        world.spawnEntity(snowfallLocation, EntityType.DROPPED_ITEM);
    }

    private boolean isSnowfallActive() {
        // Здесь можно добавить проверки, чтобы определить, является ли снег уже активным
        // Это может быть в виде переменной состояния плагина или других условий
        return true;
    }

    private Location getRandomLocation(World world) {
        // Генерируем случайную позицию для снега
        int x = (int) (Math.random() * 100);
        int z = (int) (Math.random() * 100);
        int y = world.getHighestBlockYAt(x, z);
        return new Location(world, x, y, z);
    }
}
