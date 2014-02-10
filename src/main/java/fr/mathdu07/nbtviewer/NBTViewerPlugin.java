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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import fr.mathdu07.nbtviewer.command.NBTCommandExecutor;
import fr.mathdu07.nbtviewer.config.Config;
import fr.mathdu07.nbtviewer.listener.NBTPlayerListener;
import fr.mathdu07.nbtviewer.nms.NBTObject;
import fr.mathdu07.nbtviewer.nms.NMSManager;
import fr.mathdu07.nbtviewer.nms.nbt.NBTBase;
import fr.mathdu07.nbtviewer.nms.nbt.NBTTagCompound;
import fr.mathdu07.nbtviewer.nms.nbt.NBTTagList;

public class NBTViewerPlugin extends JavaPlugin {
	
	private NBTCommandExecutor executor;
	private static Logger logger;
	private static Config config;

	@Override
	public void onLoad() {		
		logger = getLogger();
		
		if (config == null) {
			config = new Config(true, new File(getDataFolder(), "config.yml"));
			config.load();
			config.save();
		} else
			config.reload();
		
		info("Bukkit version : " + getServer().getBukkitVersion());
		NMSManager.init(getServer().getBukkitVersion());
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
		if (!config.getBoolean(Config.ENABLED)) {
			warn("Plugin disabled by the config");
			this.setEnabled(false);
			return;
		}
		
		executor = new NBTCommandExecutor();
		getServer().getPluginCommand("nbtitem").setExecutor(executor);
		
		final NBTPlayerListener playerListener = new NBTPlayerListener();
		getServer().getPluginManager().registerEvents(playerListener, this);
	}

	@Override
	public void onDisable() {
		
	}
	
	public static Config getNBTConfig() {
		return config;
	}
	
	/**
	 * Print the object's NBT to the given sender
	 * @param nbt - object which use NBT
	 * @param sender 
	 */
	public static void printNBT(NBTObject nbt, CommandSender sender) {
		printNBT(nbt, sender, "");		
	}
	
	/**
	 * Print the object's NBT to the given sender
	 * @param nbt - object which use NBT
	 * @param sender 
	 */
	public static void printNBT(NBTObject nbt, CommandSender sender, String objectName) {
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
		
		list.add(spacer + (tag.getName() == "" ? "*"  : NBTBase.TagToString(tag)));
		
		spacer += "  ";
		
		for (NBTBase base : tag.getValues()) {
			list.add(spacer + NBTBase.TagToString(base));
			
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
						list.add(spacer + "  " + NBTBase.TagToString(child));
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

}
