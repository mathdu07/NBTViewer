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

import java.lang.reflect.Method;

import fr.mathdu07.nbtviewer.NBTViewerPlugin;

public class NBTTagList extends NBTBase {
	
	public static final Class<?> NMS_CLASS;
	private static final Method getTypeId, toString, clone, equals, hashCode;
	private static final Method add, get, size;

    /**
     * Creates a wrapper of NBT Tag List
     * @param nmsTagList - the Net Minecraft Server tag
     */
    protected NBTTagList(Object nmsTagList) {
        super(nmsTagList);
        
	    if (!NMS_CLASS.isInstance(nmsTag))
	    	throw new IllegalArgumentException("Object's class must be : " + NMS_CLASS);
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
     * Add an NBT Tag to this Tag List
     * @param base - the tag to add
     */
    public void add(NBTBase base) {
    	try {
    		add.invoke(nmsTag, base.nmsTag);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * @param index
     * @return the NBT tag at the given index, or null if an exception is thrown
     */
    public NBTBase get(int index) {
    	try {
    		return NBTBase.NMSToTag(get.invoke(nmsTag, index));
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
     * @return the tag list's size, or -1 if an exception is thrown
     */
    public int size() {
    	try {
    		return (Integer) size.invoke(nmsTag);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return -1;
    	}
    }
    
    /**
     * @return a clone of this NBT Tag, or null if an exception is thrown
     */
    public NBTBase clone() {
        try {
			return new NBTTagList(clone.invoke(nmsTag));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    public boolean equals(Object object) {
    	if (object instanceof NBTTagList) {
			try {
				return (Boolean) equals.invoke(nmsTag, ((NBTTagList)object).nmsTag);
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
    	Method _add = null, _get = null, _size = null;
    	
    	try {
    		_getTypeId = NMS_CLASS.getMethod("getTypeId");
    		_toString = NMS_CLASS.getMethod("toString");
    		_clone = NMS_CLASS.getMethod("clone");
    		_equals = NMS_CLASS.getMethod("equals", Object.class);
    		_hashCode = NMS_CLASS.getMethod("hashCode");
    		
    		_add = NMS_CLASS.getMethod("add", NBTBase.NMS_CLASS);
    		_get = NMS_CLASS.getMethod("get", int.class);
    		_size = NMS_CLASS.getMethod("size");
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		getTypeId = _getTypeId;
    		toString = _toString;
    		clone = _clone;
    		equals = _equals;
    		hashCode = _hashCode;
    		
    		add = _add;
    		get = _get;
    		size = _size;
    	}
    }
    
    /**
     * @param value
     * @return created tag, or null if an exception is thrown
     */
    public static NBTTagList createTag() {
    	try {
			return new NBTTagList(NMS_CLASS.getConstructor().newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public static Class<?> getNMSClass() {
    	try {
			return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagList");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }
}
