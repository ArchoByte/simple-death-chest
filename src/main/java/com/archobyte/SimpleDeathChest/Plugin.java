package com.archobyte.SimpleDeathChest;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * simple-death-chest java plugin
 */
public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("simple-death-chest");

  public void onEnable()
  {
    LOGGER.info("simple-death-chest enabled");
  }

  public void onDisable()
  {
    LOGGER.info("simple-death-chest disabled");
  }
}
