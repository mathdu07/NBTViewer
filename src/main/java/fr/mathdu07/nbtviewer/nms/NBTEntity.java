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

import java.lang.reflect.Method;

import org.bukkit.entity.Entity;

import fr.mathdu07.nbtviewer.nms.nbt.NBTTagCompound;

public class NBTEntity extends NBTObject {
	
	public static final Class<?> entityNMSClass, craftEntityClass, NBTTagCompoundClass;
	private static final Method writeToNBT, getHandle;

	/**
	 * Creates a NBT Entity
	 * @param o
	 */
	protected NBTEntity(Object o)  {
		super(entityNMSClass, o);
	}

	@Override
	protected NBTTagCompound readNBT() throws Exception {
		NBTTagCompound compound = NBTTagCompound.createTag();
		writeToNBT.invoke(object, compound.getNmsTag());
		
		return compound;
	}
	
	/**
	 * @param e
	 * @return the NBT Entity, or null if an exception is thrown
	 */
	public static NBTEntity getNBTEntity(Entity e) {
		try {
			return new NBTEntity(getHandle.invoke(e));
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		} 
	}
	
	static {
		Class<?> _entityNMSClass = null, _craftEntityClass = null, _NBTTagCompoundClass = null;
		Method _writeToNBT = null, _getHandle = null;
		try {
			_entityNMSClass = Class.forName(NMSManager.getNMSPackage() + ".Entity");
			_craftEntityClass = Class.forName(NMSManager.getCBPackage() + ".entity.CraftEntity");
			_NBTTagCompoundClass = Class.forName(NMSManager.getNMSPackage() + ".NBTTagCompound");
			_getHandle = _craftEntityClass.getMethod("getHandle");
			_writeToNBT = _entityNMSClass.getMethod("e", _NBTTagCompoundClass);
		} catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (NoSuchMethodException e) {e.printStackTrace();}
		catch (SecurityException e) {e.printStackTrace();}
		finally {
			entityNMSClass = _entityNMSClass;
			craftEntityClass = _craftEntityClass;
			NBTTagCompoundClass = _NBTTagCompoundClass;
			getHandle = _getHandle;
			writeToNBT = _writeToNBT;
		}
	}

}
