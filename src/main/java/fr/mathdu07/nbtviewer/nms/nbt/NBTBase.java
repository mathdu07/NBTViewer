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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.mathdu07.nbtviewer.NBTViewerPlugin;

/*
 * TODO Make NBT Tag classes wrap the native one, instead of copying them
 * TODO Make JUnit Test Case
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

    //TODO Replace Class.forName(...) by static field NMS_CLASS
	public static Class<?> getNBTClass(byte id) {
    	try {
    		switch (id) {
    		case 0:
    			return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagEnd");
            case 1:
                return NBTTagByte.NMS_CLASS;
            case 2:
                return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagShort");
            case 3:
                return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagInt");
            case 4:
                return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagLong");
            case 5:
                return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagFloat");
            case 6:
                return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagDouble");
            case 7:
                return NBTTagByteArray.NMS_CLASS;
            case 8:
                return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagString");
            case 9:
                return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagList");
            case 10:
                return NBTTagCompound.NMS_CLASS;
            case 11:
                return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagIntArray");
    		default:
    			return null;
    		}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
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
    		final Class<?> baseNBTClass = Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTBase");
			final Method name = baseNBTClass.getMethod("getName", new Class<?>[0]);
			Field data;
    	
			if (getNBTClass((byte) 0).isInstance(o))
				return new NBTTagEnd();
			else if (getNBTClass((byte) 1).isInstance(o))
				return new NBTTagByte(o);
			else if (getNBTClass((byte) 2).isInstance(o)) {
				data = getNBTClass((byte) 2).getField("data");
				return new NBTTagShort((String) name.invoke(o), (Short) data.get(o));
			} else if (getNBTClass((byte) 3).isInstance(o)) {
				data = getNBTClass((byte) 3).getField("data");
				return new NBTTagInt((String) name.invoke(o), (Integer) data.get(o));
			}  else if (getNBTClass((byte) 4).isInstance(o)) {
				data = getNBTClass((byte) 4).getField("data");
				return new NBTTagLong((String) name.invoke(o), (Long) data.get(o));
			}  else if (getNBTClass((byte) 5).isInstance(o)) {
				data = getNBTClass((byte) 5).getField("data");
				return new NBTTagFloat((String) name.invoke(o), (Float) data.get(o));
			}  else if (getNBTClass((byte) 6).isInstance(o)) {
				data = getNBTClass((byte) 6).getField("data");
				return new NBTTagDouble((String) name.invoke(o), (Double) data.get(o));
			}  else if (getNBTClass((byte) 7).isInstance(o))
				return new NBTTagByteArray(o);
			else if (getNBTClass((byte) 8).isInstance(o)) {
				data = getNBTClass((byte) 8).getField("data");
				return new NBTTagString((String) name.invoke(o), (String) data.get(o));
			}  else if (getNBTClass((byte) 9).isInstance(o)) {
				final Class<?> NBTClass = getNBTClass((byte) 9);
				int size = (Integer) NBTClass.getMethod("size", new Class<?>[0]).invoke(o);
				final Method get = NBTClass.getMethod("get", int.class);
				final NBTTagList tag = new NBTTagList((String) name.invoke(o));
				
				for (int i = 0; i < size; i++)
					tag.add(NMSToTag(get.invoke(o, i)));
				
				return tag;
			} else if (getNBTClass((byte) 10).isInstance(o))
				return new NBTTagCompound(o);
			else if (getNBTClass((byte) 11).isInstance(o)) {
				data = getNBTClass((byte) 11).getField("data");
				return new NBTTagIntArray((String) name.invoke(o), (int[]) data.get(o));
			}
			
    	
    	} catch (ClassNotFoundException e) {e.printStackTrace();} 
    	catch (NoSuchMethodException e) {e.printStackTrace();}
    	catch (SecurityException e) {e.printStackTrace();}
    	catch (IllegalAccessException e) {e.printStackTrace();}
    	catch (IllegalArgumentException e) {e.printStackTrace();}
    	catch (InvocationTargetException e) {e.printStackTrace();}
    	catch (NoSuchFieldException e) {e.printStackTrace();}
    	
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
