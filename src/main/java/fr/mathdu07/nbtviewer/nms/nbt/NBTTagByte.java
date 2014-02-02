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
package fr.mathdu07.nbtviewer.nms.nbt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import fr.mathdu07.nbtviewer.NBTViewerPlugin;

public class NBTTagByte extends NBTBase {
	
	public static final Class<?> NMS_CLASS;
	private static final Method getTypeId, toString, clone, equals, hashCode;
	private static final Field data;

    /**
     * Creates a wrapper of NBT Tag Byte
     * @param nmsTagByte - the Net Minecraft Server tag
     */
    public NBTTagByte(Object nmsTagByte) {
        super(nmsTagByte);
    }
    
    /**
     * @return the tag's data
     */
    public byte getData() {
    	try {
			return data.getByte(nmsTag);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
    }
    
    /**
     * @return the type id of the tag, -1 if an exception is thrown
     */
    public byte getTypeId() {
        try {
			return (Byte) getTypeId.invoke(nmsTag);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
    }

    /**
     * @return null if exception is an thrown
     */
    @Override
    public String toString() {
        try {
			return (String) toString.invoke(nmsTag);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * @return a clone of this NBT Tag, or null if an exception is thrown
     */
    public NBTBase clone() {
        try {
			return new NBTTagByte(clone.invoke(nmsTag));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    public boolean equals(Object object) {
    	if (object instanceof NBTTagByte) {
			try {
				return (Boolean) equals.invoke(nmsTag, ((NBTTagByte)object).nmsTag);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
    	} else
    		return false;
    }

    public int hashCode() {
        try {
			return (Integer) hashCode.invoke(nmsTag);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
    }
    
    static {
    	NMS_CLASS = getNMSClass();
    	Method _getTypeId = null, _toString = null, _clone = null, _equals = null, _hashCode = null;
    	Field _data = null;
    	
    	try {
    		_getTypeId = NMS_CLASS.getMethod("getTypeId");
    		_toString = NMS_CLASS.getMethod("toString");
    		_clone = NMS_CLASS.getMethod("clone");
    		_equals = NMS_CLASS.getMethod("equals", Object.class);
    		_hashCode = NMS_CLASS.getMethod("hashCode");
    		_data = NMS_CLASS.getField("data");
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		getTypeId = _getTypeId;
    		toString = _toString;
    		clone = _clone;
    		equals = _equals;
    		hashCode = _hashCode;
    		data = _data;
    	}
    }
    
    public static Class<?> getNMSClass() {
    	try {
			return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagByte");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }

}
