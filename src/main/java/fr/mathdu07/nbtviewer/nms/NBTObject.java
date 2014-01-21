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

import fr.mathdu07.nbtviewer.nms.nbt.NBTTagCompound;

public abstract class NBTObject {
	
	protected final Class<?> objectClass;
	protected final Object object;
	protected NBTTagCompound rootTag;
	
	/**
	 * An Object that use NBT
	 * @param objectClass
	 * @param o - the NMS object using NBT
	 */
	protected NBTObject(Class<?> objectClass, Object o) {
		if (!objectClass.isInstance(o))
			throw new IllegalArgumentException("Object is not an instance of " + objectClass);
		
		this.objectClass = objectClass;
		this.object = o;
		try {
			this.rootTag = readNBT();
		} catch (Exception e) {
			e.printStackTrace();
			this.rootTag = null;
		}
	}
	
	protected abstract NBTTagCompound readNBT() throws Exception;
	
	/**
	 * @return the class for the object using NBT
	 */
	public Class<?> getNMSClass() {
		return objectClass;
	}
	
	/**
	 * @return the net minecraft server object
	 * </br>It's an instance of the class returned by {@link #getNMSClass()}
	 */
	public Object getNMSObject() {
		return object;
	}
	
	/**
	 * @return the root tag of the object
	 */
	public NBTTagCompound getRootTag() {
		return rootTag;
	}

}
