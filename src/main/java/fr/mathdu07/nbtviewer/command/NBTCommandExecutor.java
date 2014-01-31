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
		this.commands.add(new NBTHelpCommand(this));
	}

	public boolean onCommand(CommandSender send, Command cmd, String label, String[] args) {
		
		for (NBTCommand command : commands){
		
				if (command.getCommand().equals(cmd))
					return command.onCommand(send, label, args);
		}
		
		return false;
	}
	
	protected List<NBTCommand> getCommands() {
		return commands;
	}

}
