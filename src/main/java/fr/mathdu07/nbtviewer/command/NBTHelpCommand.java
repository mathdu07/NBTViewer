/*
 * Copyright © 2014 mathdu07 <mathdu07.minecraft@gmail.com>
 * 
 * This file is part of NBT Viewer.
 * 
 * NBT Viewer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * NBT Viewer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with NBT Viewer. If not, see <http://www.gnu.org/licenses/>.
 */
package fr.mathdu07.nbtviewer.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class NBTHelpCommand extends NBTCommand {

	public NBTHelpCommand(NBTCommandExecutor executor) {
		super(Bukkit.getPluginCommand("nbthelp"), executor);
	}

	@Override
	public boolean performCommand(CommandSender sender, String label, String[] args) {
		final List<NBTCommand> commands = executor.getCommands();
		final int commandsPerPage = 5, pages = (int) Math.ceil(commands.size()/(commandsPerPage * 1.0));
		int page = 1;
		
		if (args.length >= 1) {
			try {
				page = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				sender.sendMessage(ChatColor.RED + "\'" + args[0] + "\' isn't an integer");
				return true;
			}
		}
		
		if (page < 1 || page > pages) {
			sender.sendMessage(ChatColor.RED + "The page's number must be between 1 & " + pages);
			return true;
		}
		
		sender.sendMessage("+----------------[NBT Help " + page + "/" + pages + "]----------------+");
		for (int i = (page - 1) * commandsPerPage ; i < commands.size() && i < page * commandsPerPage; i++)  {
			NBTCommand command = commands.get(i);
			sender.sendMessage(command.getUsage() + " " + ChatColor.GRAY + command.getDescription());
		}
		sender.sendMessage("+--------------------------------------------+");
		
		return false;
	}
	
	

	@Override
	protected boolean onlyPlayer() {
		return false;
	}

}
