package com.archobyte.SimpleDeathChest;

import java.util.LinkedList;
import java.util.Queue;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class Finder {
    private static final Vector[] ADJACENT = {
        new Vector(0, 0, 1),
        new Vector(0, 1, 0),
        new Vector(1, 0, 0),
        new Vector(0, 0, -1),
        new Vector(0, -1, 0),
        new Vector(-1, 0, 0)
    };

    public static Location getNearestAirBlock(Location loc)
    {
        Queue<Location> queue = new LinkedList<>();
        queue.add(loc);
        int maxY = loc.getWorld().getMaxHeight();
        int minY = -64;
        while (!queue.isEmpty()) {
            Location current = queue.remove();
            if (current.getBlock().getType().isAir())
                return current;
            for (Vector dxyz : ADJACENT) {
                Location next = new Location(loc.getWorld(), current.getX() + dxyz.getX(), current.getY() + dxyz.getY(), current.getZ() + dxyz.getZ());
                if (next.getY() < maxY && next.getY() > minY)
                    queue.add(next);
            }
        }
        return null;
    }
}