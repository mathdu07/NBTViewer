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
package fr.mathdu07.nbtviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import fr.mathdu07.nbtviewer.command.NBTCommandExecutor;
import fr.mathdu07.nbtviewer.nms.NBTItemStack;
import fr.mathdu07.nbtviewer.nms.NMSManager;
import fr.mathdu07.nbtviewer.nms.nbt.NBTBase;
import fr.mathdu07.nbtviewer.nms.nbt.NBTTagCompound;
import fr.mathdu07.nbtviewer.nms.nbt.NBTTagList;


public class NBTViewerPlugin extends JavaPlugin {
	
	private NBTCommandExecutor executor;
	private static Logger logger;

	@Override
	public void onLoad() {		
		logger = getLogger();
		
		info("Bukkit version : " + getServer().getBukkitVersion());
		String packageNMS = NMSManager.getNMSPackage();
		String packageCB = NMSManager.getCBPackage();
		
		if (packageNMS == null || packageCB == null) {
			severe("Unsupported version : " + getServer().getBukkitVersion());
			info("Please check your version");
			info("If you're running on a Recommended Build, please consider to open an issue about");
			setEnabled(false);
		}
	}

	@Override
	public void onEnable() {
		this.executor = new NBTCommandExecutor();
		this.getServer().getPluginCommand("nbtitem").setExecutor(executor);
	}

	@Override
	public void onDisable() {

	}
	
	/**
	 * Print the object's NBT to the given sender
	 * @param nbt - object which use NBT
	 * @param sender 
	 */
	public static void printNBT(NBTItemStack nbt, CommandSender sender) {
		printNBT(nbt, sender, "");		
	}
	
	/**
	 * Print the object's NBT to the given sender
	 * @param nbt - object which use NBT
	 * @param sender 
	 */
	public static void printNBT(NBTItemStack nbt, CommandSender sender, String objectName) {
		final NBTTagCompound compound = nbt.getRootTag();
		sender.sendMessage("--------------------------------------");
		sender.sendMessage((objectName.isEmpty() ? "" : objectName + "'s ") + "NBT : ");
		sender.sendMessage(developNBTTree(compound));
		sender.sendMessage("--------------------------------------");
		
	}
	
	private static String[] developNBTTree(NBTTagCompound tag) {
		return developNBTTree(tag, 0);
	}
	
	private static String[] developNBTTree(NBTTagCompound tag, int level) {
		final List<String> list = new ArrayList<String>();
		String spacer = "";
		
		for (int i = 0; i < level; i++)
			spacer += "  ";
		
		list.add(spacer + (tag.getName() == "" ? "*"  : tag.toString()));
		
		spacer += "  ";
		
		for (NBTBase base : tag.getValues()) {
			list.add(spacer + base.toString());
			
			if (base instanceof NBTTagCompound) {
				final String[] tags = developNBTTree((NBTTagCompound) base, level + 1);
				list.remove(list.size() - 1);
				
				for (String str : tags) 
					list.add(str);
			} else if (base instanceof NBTTagList) {
				final NBTTagList tagList = (NBTTagList) base;
				
				for (int i = 0; i < tagList.size(); i++) {
					final NBTBase child = tagList.get(i);
					
					if (child instanceof NBTTagCompound) {
						final String[] tags = developNBTTree((NBTTagCompound) child, level + 1);
						
						for (String s : tags)
							list.add(spacer + s);
					} else
						list.add(spacer + "  " + child);
				}
					
			}
		}
		
		return list.toArray(new String[list.size()]);
	}

	public static void log(String msg, Level level) {
		logger.log(level, msg);
	}
	
	public static void info(String msg) {
		log(msg, Level.INFO);
	}
	
	public static void warn(String msg) {
		log(msg, Level.WARNING);
	}
	
	public static void severe(String msg) {
		log(msg, Level.SEVERE);
	}
	
	/**
	 * @deprecated Use {@link NMSManager#getNMSPackage()} instead
	 * @return the net minecraft server package for this version
	 */
	@Deprecated
	public static String getNMSPackage() {
		return NMSManager.getNMSPackage();
	}
	
	/**
	 * @deprecated Use {@link NMSManager#getCBPackage()} instead
	 * @return the net minecraft server package for this version
	 */
	@Deprecated
	public static String getCBPackage() {
		return NMSManager.getCBPackage();
	}

}
