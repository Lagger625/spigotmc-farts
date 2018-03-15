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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author Lagger625
 */
public class CommandFart implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player){
            FileConfiguration fc = FartsPlugin.getPlayerFileConfig();
            String path = ((Player)sender).getUniqueId() + ".farting";
            boolean farting = fc.getBoolean(path);
            fc.set(path, farting = !farting);
            sender.sendMessage(farting ? "You're now farting." : "You no longer fart.");
            try {
                fc.save(FartsPlugin.getPlayerConfigFilePath());
            } catch(IOException e){
                FartsPlugin.getPluginLogger().info("Exception occured while trying to save config file.");
            }
        } else {
            sender.sendMessage("Console cannot fart :(");
        }
        return true;
    }
}