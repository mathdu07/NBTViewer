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
package fr.mathdu07.nbtviewer.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import fr.mathdu07.nbtviewer.NBTViewerPlugin;
import fr.mathdu07.nbtviewer.config.Config;
import fr.mathdu07.nbtviewer.nms.NBTEntity;

public class NBTPlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent env) {
		if (env == null) return;
		
		final ItemStack is = env.getPlayer().getItemInHand();
		if (is != null) {
			
			if (is.getTypeId() == NBTViewerPlugin.getNBTConfig().getInt(Config.ITEM_ID)) {
				final Player p = env.getPlayer();
				
				if (!p.hasPermission("nbtviewer.entity")) 
					p.sendMessage(ChatColor.RED + "You don't have the permission to do that.");
				else {
					final Entity ent = env.getRightClicked();
					NBTViewerPlugin.printNBT(NBTEntity.getNBTEntity(ent), p);
				}
				
			}
		}
	}

}
