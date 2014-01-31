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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.mathdu07.nbtviewer.NBTViewerPlugin;
import fr.mathdu07.nbtviewer.nms.NBTItemStack;

public class NBTItemCommand extends NBTCommand {
	
	/**
	 * The NBT Item command
	 */
	public NBTItemCommand(NBTCommandExecutor executor) {
		super(Bukkit.getPluginCommand("nbtitem"), executor);
	}

	@Override
	public boolean performCommand(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		
		final Player p = (Player) sender;
		final ItemStack is = p.getItemInHand();
		
		if (is.getType().equals(Material.AIR)) {
			sender.sendMessage(ChatColor.RED + "Your item can't be air");
			return true;
		}
		
		final NBTItemStack nbt = NBTItemStack.getNBTItemStack(is);
		
		if (nbt.getRootTag() == null)
			sender.sendMessage("This item does not contain NBT");
		else
			NBTViewerPlugin.printNBT(nbt, sender, ChatColor.GOLD + is.getType().toString() + ChatColor.RESET);
		
		return true;
	}

	@Override
	protected boolean onlyPlayer() {
		return true;
	}
}
