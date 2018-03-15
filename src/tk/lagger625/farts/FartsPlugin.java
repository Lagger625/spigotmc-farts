/*
 * Copyright (C) 2018 Arnoldo Adonai Barón Robles
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tk.lagger625.farts;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Lagger625
 */
public class FartsPlugin extends JavaPlugin {
    
    private static FileConfiguration config, playerConfig;
    private static String configFilePath, playerConfigFilePath;
    private static Logger logger;
    
    public static String getConfigFilePath(){
        return configFilePath;
    }
    
    public static String getPlayerConfigFilePath(){
        return playerConfigFilePath;
    }
    
    public static FileConfiguration getFileConfig(){
        return config;
    }
    
    public static FileConfiguration getPlayerFileConfig(){
        return playerConfig;
    }
    
    public static Logger getPluginLogger(){
        return logger;
    }

    @Override
    public void onEnable(){
        config = getConfig();
        logger = getServer().getLogger();
        configFilePath = getDataFolder() + "/config.yml";
        playerConfigFilePath = getDataFolder() + "/players.yml";
        this.getCommand("fart").setExecutor(new CommandFart());
        config.addDefault("volume", 1.0);
        config.options().copyDefaults(true);
        saveConfig();
        new CrouchListener(this);
        playerConfig = YamlConfiguration.loadConfiguration(new File(getPlayerConfigFilePath()));
    }
    
    @Override
    public void onDisable(){
        
    }
}
