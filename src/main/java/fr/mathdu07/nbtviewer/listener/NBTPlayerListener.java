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
