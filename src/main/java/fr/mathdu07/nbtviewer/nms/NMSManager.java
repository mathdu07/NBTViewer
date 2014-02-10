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
package fr.mathdu07.nbtviewer.nms;

import java.util.HashMap;
import java.util.Map;

public class NMSManager {
	
	private static final Map<String, String> mapPackageNMS = new HashMap<String, String>();
	private static final Map<String, String> mapPackageCB = new HashMap<String, String>();
	private static String packageNMS;
	private static String packageCB;
	
	/**
	 * Initialize the Net Minecraft Server Manager
	 * @param version - craftbukkit's version 
	 */
	public static void init(String version) {
		packageNMS = mapPackageNMS.get(version);
		packageCB = mapPackageCB.get(version);
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
		//Recommended builds
		mapPackageNMS.put("1.6.4-R2.0", "net.minecraft.server.v1_6_R3");
		mapPackageNMS.put("1.6.4-R1.0", "net.minecraft.server.v1_6_R3");
		mapPackageNMS.put("1.6.2-R1.0", "net.minecraft.server.v1_6_R2");
		
		//Recommended builds
		mapPackageCB.put("1.6.4-R2.0", "org.bukkit.craftbukkit.v1_6_R3");
		mapPackageCB.put("1.6.4-R1.0", "org.bukkit.craftbukkit.v1_6_R3");
		mapPackageCB.put("1.6.2-R1.0", "org.bukkit.craftbukkit.v1_6_R2");
	}
	
}
