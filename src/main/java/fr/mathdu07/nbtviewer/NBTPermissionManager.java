package fr.mathdu07.nbtviewer;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;

public class NBTPermissionManager {
	
	/**
	 * Gives all plugin's permissions
	 */
	public static final Permission NBT_PERMISSION_ALL = getPerm("nbtexplorer.*");
	
	/**
	 * Permission to view an item's NBT
	 */
	public static final Permission NBT_PERMISSION_ITEM = getPerm("nbtexplorer.item");
	
	private static Permission getPerm(String perm) {
		return Bukkit.getPluginManager().getPermission(perm);
	}

}
