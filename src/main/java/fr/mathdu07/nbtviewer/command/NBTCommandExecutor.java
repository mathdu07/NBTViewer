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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NBTCommandExecutor implements CommandExecutor {
	
	private final List<NBTCommand> commands;
	
	public NBTCommandExecutor() {
		this.commands = new ArrayList<NBTCommand>();
		this.commands.add(new NBTItemCommand(this));
	}

	public boolean onCommand(CommandSender send, Command cmd, String label, String[] args) {
		
		for (NBTCommand command : commands){
		
				if (command.getCommand().equals(cmd))
					return command.onCommand(send, label, args);
		}
		
		return false;
	}
	
	/**
	 * Shows the help to the command sender
	 * TODO Add in nbthelp command
	 * @param send
	 * @param label
	 * @param args
	 */
	/*private void showHelp(CommandSender send, String label, String[] args) {
		final int commandsPerPage = 5, pages = (int) Math.ceil(commands.size()/(commandsPerPage * 1.0));
		int page = 1;
		
		if (args.length >= 2) {
			try {
				page = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				send.sendMessage(ChatColor.RED + "\'" + args[1] + "\' isn't an integer");
			}
		}
		
		if (page < 1 || page > pages) {
			send.sendMessage(ChatColor.RED + "The page's number must be between 1 & " + pages);
			return;
		}
		
		send.sendMessage("+----------[NBT Help " + page + "/" + pages + "]----------+");
		for (int i = (page - 1) * commandsPerPage ; i < commands.size() && i < page * commandsPerPage; i++)  {
			NBTCommand command = commands.get(i);
			send.sendMessage(command.getUsage() + " " + ChatColor.GRAY + command.getDescription());
		}
		send.sendMessage("+----------------------------------+");
	}*/


}
