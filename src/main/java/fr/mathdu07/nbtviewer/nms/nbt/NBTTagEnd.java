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

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagEnd extends NBTBase {
	
	public static final Class<?> NMS_CLASS;
	private static final Method getTypeId, toString, clone;

    /**
     * Creates a wrapper of NBT Tag End
     * @param nmsTagENd - the Net Minecraft Server tag
     */
    protected NBTTagEnd(Object nmsTagEnd) {
        super(nmsTagEnd);
        
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
     * @return a clone of this NBT Tag, or null if an exception is thrown
     */
    public NBTBase clone() {
        try {
			return new NBTTagEnd(clone.invoke(nmsTag));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    static {
    	NMS_CLASS = getNMSClass();
    	Method _getTypeId = null, _toString = null, _clone = null;
    	
    	try {
    		_getTypeId = NMS_CLASS.getMethod("getTypeId");
    		_toString = NMS_CLASS.getMethod("toString");
    		_clone = NMS_CLASS.getMethod("clone");
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		getTypeId = _getTypeId;
    		toString = _toString;
    		clone = _clone;
    	}
    }
    
    /**
     * @param value
     * @return created tag, or null if an exception is thrown
     */
    public static NBTTagEnd createTag() {
    	try {
			return new NBTTagEnd(NMS_CLASS.getConstructor().newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public static Class<?> getNMSClass() {
    	try {
			return Class.forName(NMSManager.getNMSPackage() + ".NBTTagEnd");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }

}
