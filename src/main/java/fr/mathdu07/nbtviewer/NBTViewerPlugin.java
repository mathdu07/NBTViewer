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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import fr.mathdu07.nbtviewer.command.NBTCommandExecutor;
import fr.mathdu07.nbtviewer.nms.NBTItemStack;
import fr.mathdu07.nbtviewer.nms.nbt.NBTBase;
import fr.mathdu07.nbtviewer.nms.nbt.NBTTagCompound;

public class NBTViewerPlugin extends JavaPlugin {
	
	private NBTCommandExecutor executor;
	private static Logger logger;
	
	private static final Map<String, String> mapPackageNMS = new HashMap<String, String>();
	private static final Map<String, String> mapPackageCB = new HashMap<String, String>();
	private static String packageNMS;
	private static String packageCB;

	@Override
	public void onLoad() {		
		logger = getLogger();
		
		info("Bukkit version : " + getServer().getBukkitVersion());
		packageNMS = mapPackageNMS.get(getServer().getBukkitVersion());
		packageCB = mapPackageCB.get(getServer().getBukkitVersion());
		
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
		final NBTTagCompound compound = nbt.getRootTag();
		sender.sendMessage("NBT :");
		sender.sendMessage(developNBTTree(compound));
		
	}
	
	private static String[] developNBTTree(NBTTagCompound tag) {
		final List<String> list = new ArrayList<String>();
		
		for (NBTBase base : tag.getValues()) {
			list.add(base.toString());
			
			if (base instanceof NBTTagCompound) {
				final String[] tags = developNBTTree((NBTTagCompound) base);
				
				for (String str : tags) 
					list.add("  " + str);
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
	 * @return the net minecraft server package for this version
	 */
	public static String getNMSPackage() {
		return packageNMS;
	}
	
	/**
	 * @return the net minecraft server package for this version
	 */
	public static String getCBPackage() {
		return packageCB;
	}
	
	static {
		//Beta builds
		mapPackageNMS.put("1.7.2-R0.2", "net.minecraft.server.v1_7_R1");
		mapPackageNMS.put("1.7.2-R0.1", "net.minecraft.server.v1_7_R1");
		//Recommended builds
		mapPackageNMS.put("1.6.4-R2.0", "net.minecraft.server.v1_6_R3");
		mapPackageNMS.put("1.6.4-R1.0", "net.minecraft.server.v1_6_R3");
		mapPackageNMS.put("1.6.2-R1.0", "net.minecraft.server.v1_6_R2");
		mapPackageNMS.put("1.5.2-R1.0", "net.minecraft.server.v1_5_R3");
		
		//Beta builds
		mapPackageCB.put("1.7.2-R0.2", "org.bukkit.craftbukkit.v1_7_R1");
		mapPackageCB.put("1.7.2-R0.1", "org.bukkit.craftbukkit.v1_7_R1");
		//Recommended builds
		mapPackageCB.put("1.6.4-R2.0", "org.bukkit.craftbukkit.v1_6_R3");
		mapPackageCB.put("1.6.4-R1.0", "org.bukkit.craftbukkit.v1_6_R3");
		mapPackageCB.put("1.6.2-R1.0", "org.bukkit.craftbukkit.v1_6_R2");
		mapPackageCB.put("1.5.2-R1.0", "org.bukkit.craftbukkit.v1_5_R3");
	}

}
