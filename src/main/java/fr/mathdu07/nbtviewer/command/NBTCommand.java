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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

public abstract class NBTCommand {
	
	/**
	 * The bukkit's command
	 */
	private final Command bukkitCommand;
	
	protected final NBTCommandExecutor executor;
	
	public NBTCommand(PluginCommand command,  NBTCommandExecutor executor) {
		this.bukkitCommand = command;
		this.executor = executor;
		command.setExecutor(executor);
	}

	public final boolean onCommand(CommandSender sender, String label, String[] args) {
		
		if (onlyPlayer() && !(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "This command can only be executed by a player");
			return false;
		}
		
		if (args.length >= 1){
			if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")){
				sender.sendMessage(getDescription());
				return true;
			}
		}
		
		return performCommand(sender, label, args);
		
	}
	
	/**
	 * Execute the command
	 * @param sender - the sender (console, player ...) that has performed the command
	 * @param args - arguments of the command
	 */
	public abstract boolean performCommand(CommandSender sender, String label, String[] args);
	
	/**
	 * @return is the command can be only performed by a player
	 */
	protected abstract boolean onlyPlayer();
	
	/**
	 * @return the usage of the command, displayed on help
	 */
	public String getUsage() {
		return "/" + getName();  
	}
	/**
	 * @return description of the command, displayed on help
	 */
	public String getDescription() {
		return bukkitCommand.getDescription();
	}

	/**
	 * @return permission needed to execute the command
	 */
	public String getPermission() {
		return bukkitCommand.getPermission();
	}
	
	/**
	 * @return the name of the command
	 */
	public String getName() {
		return bukkitCommand.getName();
	}
	
	/**
	 * @return the aliases of the command
	 */
	public List<String> getAliases() {
		return getCommand().getAliases();
	}
	
	/**
	 * @return the bukkit command handle by this class
	 */
	public Command getCommand() {
		return bukkitCommand;
	}
}

