package com.archobyte.SimpleDeathChest;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * simple-death-chest java plugin
 */
public class Plugin extends JavaPlugin
{
  public static final Logger LOGGER=Logger.getLogger("simple-death-chest");

  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(new DeathListener(), this);
    LOGGER.info("simple-death-chest enabled");
  }

  public void onDisable()
  {
    LOGGER.info("simple-death-chest disabled");
  }
}
