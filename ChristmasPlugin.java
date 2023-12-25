package org.example.ua.itristan.christmasplugiт

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ChristmasPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Плагин включен!");

        startSnowEffect();

        startReedGrowth();
    }

    @Override
    public void onDisable() {
        getLogger().info("Плагин выключен!");
    }

    private void startSnowEffect() {
        BukkitTask snowTask = new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("world");
                if (world != null) {
                    int snowRadius = 20;
                    int centerX = 0;
                    int centerY = 64; 
                    int centerZ = 0; 

                    for (int x = centerX - snowRadius; x <= centerX + snowRadius; x++) {
                        for (int z = centerZ - snowRadius; z <= centerZ + snowRadius; z++) {
                            Location location = new Location(world, x, centerY, z);
                            world.setStorm(true); 
                            world.strikeLightningEffect(location); 
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0L, 20L * 60 * 20); 
    }

    private void startReedGrowth() {
        BukkitTask reedGrowthTask = new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("world");
                if (world != null) {
                    int growthRadius = 10;
                    int centerX = 0;
                    int centerZ = 0;

                    for (int x = centerX - growthRadius; x <= centerX + growthRadius; x++) {
                        for (int z = centerZ - growthRadius; z <= centerZ + growthRadius; z++) {
                            Location location = new Location(world, x, 0, z);
                            Block block = location.getBlock();

                            if (block.getType() == Material.SUGAR_CANE) {
                                block.setType(Material.AIR);
                                world.getBlockAt(location.add(0, 1, 0)).setType(Material.SUGAR_CANE);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0L, 20L * 60 * 20); 
    }
}
