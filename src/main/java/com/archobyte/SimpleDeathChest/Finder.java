package com.archobyte.SimpleDeathChest;

import java.util.LinkedList;
import java.util.Queue;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Finder {
    // Directions in which search can be performed
    private static final Vector[] ADJACENT = {
            new Vector(0, 0, 1),
            new Vector(0, 1, 0),
            new Vector(1, 0, 0),
            new Vector(0, 0, -1),
            new Vector(0, -1, 0),
            new Vector(-1, 0, 0)
    };

    public static Location getNearestAirBlock(Location loc) {
        Queue<Location> queue = new LinkedList<Location>();
        queue.add(loc);
        int maxY = loc.getWorld().getMaxHeight();
        // All worlds have 0 min build height except "world"
        int minY = 0;
        if (loc.getWorld().getName() == "world")
            minY = -64;
        // If initial location is under the world limit
        if (loc.getY() < minY)
            loc.setY(minY);
        // If initial location is over the world limit
        if (loc.getY() > maxY)
            loc.setY(maxY);
        while (!queue.isEmpty()) {
            // Check current location
            Location current = queue.remove();
            if (current.getBlock().getType().isAir())
                return current;
            // Get next candidates for chech
            for (Vector dxyz : ADJACENT) {
                Location next = new Location(loc.getWorld(),
                        current.getX() + dxyz.getX(),
                        current.getY() + dxyz.getY(),
                        current.getZ() + dxyz.getZ());
                if (next.getY() < maxY && next.getY() > minY)
                    queue.add(next);
            }
        }
        return null;
    }
}