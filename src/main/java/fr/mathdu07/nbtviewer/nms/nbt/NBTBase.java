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

/*
 * TODO Make constructor protected 
 */
public abstract class NBTBase {
	
	public static final Class<?> NMS_CLASS;
	
	private static final Method getName;
	private static final Method equals;
	private static final Method hashCode;

	public static final String[] NBT_TYPES = new String[] 
			{ "END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]"};

	protected final Object nmsTag;
	
    public abstract byte getTypeId();

    protected NBTBase(Object o) {
    	this.nmsTag = o;
    }

    /**
     * @deprecated method removed in 1.7.2
     * @return the tag's name, null if reflection exception
     */
    @Deprecated
    public String getName() {
        try {
			return (String) getName.invoke(nmsTag);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
    }
    
    /**
     * @return the Net Minecraft Server tag object
     */
    public Object getNmsTag() {
		return nmsTag;
	}

	public static Class<?> getNBTClass(byte id) {
		
    	switch (id) {
    	case 0:
    		return NBTTagEnd.NMS_CLASS;
        case 1:
            return NBTTagByte.NMS_CLASS;
        case 2:
            return NBTTagShort.NMS_CLASS;
        case 3:
            return NBTTagInt.NMS_CLASS;
        case 4:
            return NBTTagLong.NMS_CLASS;
        case 5:
            return NBTTagFloat.NMS_CLASS;
        case 6:
            return NBTTagDouble.NMS_CLASS;
        case 7:
            return NBTTagByteArray.NMS_CLASS;
        case 8:
            return NBTTagString.NMS_CLASS;
        case 9:
            return NBTTagList.NMS_CLASS;
        case 10:
            return NBTTagCompound.NMS_CLASS;
        case 11:
            return NBTTagIntArray.NMS_CLASS;
    	default:
    		return null;
    	}
    }

    public static String getTagName(byte id) {
    	
        switch (id) {
        case 0:
            return "TAG_End";
        case 1:
            return "TAG_Byte";
        case 2:
            return "TAG_Short";
        case 3:
            return "TAG_Int";
        case 4:
            return "TAG_Long";
        case 5:
            return "TAG_Float";
        case 6:
            return "TAG_Double";
        case 7:
            return "TAG_Byte_Array";
        case 8:
            return "TAG_String";
        case 9:
            return "TAG_List";
        case 10:
            return "TAG_Compound";
        case 11:
            return "TAG_Int_Array";
        default:
            return "UNKNOWN";
        }
    }
    
    /**
     * 
     * @param o - the Net Minecraft Server nbt object
     * @return the Plugin's Tag 
     */
    public static NBTBase NMSToTag(Object o) {
    	try {
			if (!Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTBase").isInstance(o))
				throw new IllegalArgumentException("Object o must inherit from NMS NBTBase");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			return null;
		}
    	
    	try {  	
			if (getNBTClass((byte) 0).isInstance(o))
				return new NBTTagEnd(o);
			else if (getNBTClass((byte) 1).isInstance(o))
				return new NBTTagByte(o);
			else if (getNBTClass((byte) 2).isInstance(o))
				return new NBTTagShort(o);
			else if (getNBTClass((byte) 3).isInstance(o))
				return new NBTTagInt(o);
			else if (getNBTClass((byte) 4).isInstance(o))
				return new NBTTagLong(o);
			else if (getNBTClass((byte) 5).isInstance(o))
				return new NBTTagFloat(o);
			else if (getNBTClass((byte) 6).isInstance(o))
				return new NBTTagDouble(o);
			else if (getNBTClass((byte) 7).isInstance(o))
				return new NBTTagByteArray(o);
			else if (getNBTClass((byte) 8).isInstance(o))
				return new NBTTagString(o);
			else if (getNBTClass((byte) 9).isInstance(o))
				return new NBTTagList(o);
			else if (getNBTClass((byte) 10).isInstance(o))
				return new NBTTagCompound(o);
			else if (getNBTClass((byte) 11).isInstance(o)) 
				return new NBTTagIntArray(o);			
    	
    	} catch (SecurityException e) {e.printStackTrace();}
    	catch (IllegalArgumentException e) {e.printStackTrace();}
    	
    	return null;
    }

    public abstract NBTBase clone();

    public boolean equals(Object object) {
    	if (object instanceof NBTBase) {
			try {
				return (Boolean) equals.invoke(nmsTag, ((NBTBase)object).nmsTag);
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
    	Method _getName = null;
    	Method _equals = null;
    	Method _hashCode = null;
    	
    	try {
			_getName = NMS_CLASS.getMethod("getName");
			_equals = NMS_CLASS.getMethod("equals", Object.class);
			_hashCode = NMS_CLASS.getMethod("hashCode");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getName = _getName;
			equals = _equals;
			hashCode = _hashCode;
		}
    }
    
    private static Class<?> getNMSClass() {
    	try {
			return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTBase");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }

}
