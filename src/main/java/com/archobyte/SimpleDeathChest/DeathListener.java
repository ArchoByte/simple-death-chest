package com.archobyte.SimpleDeathChest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class DeathListener implements Listener {
    private static final int MAX_CHEST_CAPACITY = 27;

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();
        Queue<ItemStack> items = new LinkedList<>();
        DeathChestPlugin.LOGGER.info(
                "Player " + player.getName() + " died at location: " + player.getLocation().toVector().toBlockVector()
                        + " in world: " + player.getLocation().getWorld().getName());

        // Get all items
        for (ItemStack itemStack : player.getInventory().getContents())
            if (itemStack != null)
                items.add(itemStack);

        ArrayList<ItemStack> itemsChunk;
        while (!items.isEmpty()) {
            // Get chink of items to store in current chest
            itemsChunk = new ArrayList<ItemStack>();
            while (itemsChunk.size() < MAX_CHEST_CAPACITY && !items.isEmpty())
                itemsChunk.add(items.remove());

            // Create chest block
            Location chestLoc = Finder.getNearestAirBlock(player.getLocation());
            chestLoc.getBlock().setType(Material.CHEST);
            // Store items in Chest
            Chest chest = (Chest) chestLoc.getBlock().getState();
            chest.getInventory().setContents(itemsChunk.toArray(new ItemStack[itemsChunk.size()]));

            // Clear dropped items
            event.getDrops().clear();
            // Tell player location
            Vector position = chestLoc.toVector();
            position.setX(Math.floor(position.getX()));
            position.setY(Math.floor(position.getY()));
            position.setZ(Math.floor(position.getZ()));

            player.sendMessage(
                    "Coordinates of chest with your loot: " +
                            position + " in world: " + chestLoc.getWorld().getName());
            // Clear list
            itemsChunk.clear();
        }
    }
}
