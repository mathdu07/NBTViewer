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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.inventory.ItemStack;

import fr.mathdu07.nbtviewer.nms.nbt.NBTBase;
import fr.mathdu07.nbtviewer.nms.nbt.NBTTagCompound;

public class NBTItemStack extends NBTObject {
	
	public static final Class<?> itemStackNMSClass;

	/**
	 * Creates a NBT ItemStack
	 * @param o
	 */
	protected NBTItemStack(Object o)  {
		super(itemStackNMSClass, o);
	}
	
	/**
	 * @return the NBT read from the NMS Object
	 */
	@Override
	protected NBTTagCompound readNBT() throws Exception{
		Field tag = itemStackNMSClass.getField("tag");
		
		if (tag.get(object) == null)
			return null;
		
		return (NBTTagCompound) NBTBase.NMSToTag(tag.get(object));
	}

	public static NBTItemStack getNBTItemStack(ItemStack is) {
		try {
			Class<?> craftItemClass = Class.forName(NMSManager.getCBPackage() + ".inventory.CraftItemStack");
			Method m = craftItemClass.getDeclaredMethod("asNMSCopy", new Class<?>[] {ItemStack.class});
			return new NBTItemStack(m.invoke(craftItemClass.cast(is), is));
		}
		catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (InvocationTargetException e) {e.printStackTrace();}
		catch (NoSuchMethodException e) {e.printStackTrace();}
		catch (SecurityException e) {e.printStackTrace();}
		
		return null;
	}
	
	static {
		Class<?> itemClass = null;
		try {
			itemClass = Class.forName(NMSManager.getNMSPackage() + ".ItemStack");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			itemStackNMSClass = itemClass;
		}
	}

}
