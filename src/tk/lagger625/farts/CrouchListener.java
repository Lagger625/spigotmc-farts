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

import java.io.IOException;
import java.util.Random;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 *
 * @author Lagger625
 */
public class CrouchListener implements Listener {
    private final Random r = new Random();
    
    public CrouchListener(FartsPlugin fp){
        fp.getServer().getPluginManager().registerEvents(this, fp);
    }
    
    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        FileConfiguration fc = FartsPlugin.getPlayerFileConfig();
        String path = player.getUniqueId() + ".farting";
        if (player.hasPermission("farts.fart")) {
            if (!fc.getBoolean(path)){
                return;
            }
            if (!player.isSneaking()) {
                Server s = getServer();
                Player p = event.getPlayer();
                Location l = p.getLocation();
                p.playSound(l, "f" + r.nextInt(20), (float)FartsPlugin.getFileConfig().getDouble("volume"), r.nextFloat() + 0.5f);
            }
        } else {
            fc.set(path, false);
            try {
                fc.save(FartsPlugin.getPlayerConfigFilePath());
            } catch(IOException e){
                FartsPlugin.getPluginLogger().info("Exception occured while trying to save config file.");
            }
        }
    }
}
