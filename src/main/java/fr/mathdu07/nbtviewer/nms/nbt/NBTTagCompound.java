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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import fr.mathdu07.nbtviewer.nms.NMSManager;

public class NBTTagCompound extends NBTBase {

	public static final Class<?> NMS_CLASS;
	private static final Method getTypeId, toString, clone, equals, hashCode;
	private static final Method getValues, hasKey, remove, isEmpty;
	private static final Method set, setByte, setShort, setInt, setLong, setFloat, setDouble, setString,
		setByteArray, setIntArray, setCompound, setBoolean;
	private static final Method get, getByte, getShort, getInt, getLong, getFloat, getDouble, getString,
	getByteArray, getIntArray, getCompound, getList, getBoolean;

    /**
     * Creates a wrapper of NBT Tag Compound
     * @param nmsTagCompound - the Net Minecraft Server tag
     */
    protected NBTTagCompound(Object nmsTagCompound) {
        super(nmsTagCompound);
        
	    if (!NMS_CLASS.isInstance(nmsTag))
	    	throw new IllegalArgumentException("Object's class must be : " + NMS_CLASS);
    }

    /**
     * @deprecated removed in 1.7.2
     * @return
     */
    @Deprecated
    public Collection<NBTBase> getValues() {
        try {
        	final Iterator<net.minecraft.server.v1_6_R3.NBTBase> values = ((Collection<net.minecraft.server.v1_6_R3.NBTBase>) getValues.invoke(nmsTag)).iterator();
			final Collection<NBTBase> nbtValues = new ArrayList<NBTBase>();
			
			while (values.hasNext())
				nbtValues.add(NBTBase.NMSToTag(values.next()));
			
        	return nbtValues;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
			return new NBTTagCompound(clone.invoke(nmsTag));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    public boolean equals(Object object) {
    	if (object instanceof NBTTagCompound) {
			try {
				return (Boolean) equals.invoke(nmsTag, ((NBTTagCompound)object).nmsTag);
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

    public void set(String name, NBTBase nbtbase) {
        try {
			set.invoke(nmsTag, name, nbtbase.nmsTag);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setByte(String name, byte value) {
        try {
			setByte.invoke(nmsTag, name, value);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setShort(String name, short value) {
        try {
			setShort.invoke(nmsTag, name, value);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setInt(String name, int value) {
        try {
			setInt.invoke(nmsTag, name, value);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setLong(String name, long value) {
        try {
			setLong.invoke(nmsTag, name, value);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setFloat(String name, float value) {
        try {
			setFloat.invoke(nmsTag, name, value);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setDouble(String name, double value) {
        try {
			setDouble.invoke(nmsTag, name, value);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setString(String name, String value) {
        try {
			setString.invoke(nmsTag, name, value);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setByteArray(String name, byte[] value) {
        try {
			setByteArray.invoke(nmsTag, name, value);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setIntArray(String name, int[] value) {
        try {
			setIntArray.invoke(nmsTag, name, value);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setCompound(String name, NBTTagCompound value) {
        try {
			setCompound.invoke(nmsTag, name, value.nmsTag);
		} catch (Exception e) {e.printStackTrace();}
    }

    public void setBoolean(String name, boolean value) {
        try {
			setBoolean.invoke(nmsTag, name, value);
		} catch (Exception e) {e.printStackTrace();}
    }

    public NBTBase get(String name) {
        try {
        	return NBTBase.NMSToTag(get.invoke(nmsTag, name));
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public boolean hasKey(String name) {
        try {
			return (Boolean) hasKey.invoke(nmsTag, name);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

    public byte getByte(String name) {
        try {
        	return (Byte) getByte.invoke(nmsTag, name);
        } catch (Exception e) {
        	e.printStackTrace();
        	return -1;
        }
    }

    public short getShort(String name) {
        try {
        	return (Short) getShort.invoke(nmsTag, name);
        } catch (Exception e) {
        	e.printStackTrace();
        	return -1;
        }
    }

    public int getInt(String name) {
        try {
        	return (Integer) getInt.invoke(nmsTag, name);
        } catch (Exception e) {
        	e.printStackTrace();
        	return -1;
        }
    }

    public long getLong(String name) {
        try {
        	return (Long) getLong.invoke(nmsTag, name);
        } catch (Exception e) {
        	e.printStackTrace();
        	return -1;
        }
    }

    public float getFloat(String name) {
        try {
        	return (Float) getFloat.invoke(nmsTag, name);
        } catch (Exception e) {
        	e.printStackTrace();
        	return -1;
        }
    }

    public double getDouble(String name) {
        try {
        	return (Double) getDouble.invoke(nmsTag, name);
        } catch (Exception e) {
        	e.printStackTrace();
        	return -1;
        }
    }

    public String getString(String name) {
        try {
        	return (String) getString.invoke(nmsTag, name);
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public byte[] getByteArray(String name) {
        try {
        	return (byte[]) getByteArray.invoke(nmsTag, name);
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public int[] getIntArray(String name) {
        try {
        	return (int[]) getIntArray.invoke(nmsTag, name);
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public NBTTagCompound getCompound(String name) {
        try {
        	return new NBTTagCompound(getCompound.invoke(nmsTag, name));
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public NBTTagList getList(String name) {
        try {
        	return new NBTTagList(getList.invoke(nmsTag, name));
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public boolean getBoolean(String name) {
        try {
        	return (Boolean) getBoolean.invoke(nmsTag, name);
        } catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
    }

    public void remove(String name) {
        try {
        	remove.invoke(nmsTag, name);
        } catch (Exception e) {e.printStackTrace();}
    }

    public boolean isEmpty() {
        try {
        	return (Boolean) isEmpty.invoke(nmsTag);
        } catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
    }
    
    static {
    	NMS_CLASS = getNMSClass();
    	Method _getTypeId = null, _toString = null, _clone = null, _equals = null, _hashCode = null;
    	Method _getValues = null, _hasKey = null, _remove = null, _isEmpty = null;
    	Method _set = null, _setByte = null, _setShort = null, _setInt = null, _setLong = null, _setFloat = null, _setDouble = null;
    	Method _setString = null, _setByteArray = null, _setIntArray = null, _setCompound = null, _setBoolean = null;
    	Method _get = null, _getByte = null, _getShort = null, _getInt = null, _getLong = null, _getFloat = null, _getDouble = null;
    	Method _getString = null, _getByteArray = null, _getIntArray = null, _getCompound = null, _getList = null, _getBoolean = null;
    	
    	try {
    		_getTypeId = NMS_CLASS.getMethod("getTypeId");
    		_toString = NMS_CLASS.getMethod("toString");
    		_clone = NMS_CLASS.getMethod("clone");
    		_equals = NMS_CLASS.getMethod("equals", Object.class);
    		_hashCode = NMS_CLASS.getMethod("hashCode");
    		
    		_getValues = NMS_CLASS.getMethod("c");
    		_hasKey = NMS_CLASS.getMethod("hasKey", String.class);
    		_remove = NMS_CLASS.getMethod("remove", String.class);
    		_isEmpty = NMS_CLASS.getMethod("isEmpty");
    		
    		_set = NMS_CLASS.getMethod("set", String.class, NBTBase.NMS_CLASS);
    		_setByte = NMS_CLASS.getMethod("setByte", String.class, byte.class);
    		_setShort = NMS_CLASS.getMethod("setShort", String.class, short.class);
    		_setInt = NMS_CLASS.getMethod("setInt", String.class, int.class);
    		_setLong = NMS_CLASS.getMethod("setLong", String.class, long.class);
    		_setFloat = NMS_CLASS.getMethod("setFloat", String.class, float.class);
    		_setDouble = NMS_CLASS.getMethod("setDouble", String.class, double.class);
    		_setString = NMS_CLASS.getMethod("setString", String.class, String.class);
    		_setByteArray = NMS_CLASS.getMethod("setByteArray", String.class, byte[].class);
    		_setIntArray = NMS_CLASS.getMethod("setIntArray", String.class, int[].class);
    		_setCompound = NMS_CLASS.getMethod("setCompound", String.class, NBTTagCompound.NMS_CLASS);
    		_setBoolean = NMS_CLASS.getMethod("setBoolean", String.class, boolean.class);
    		
    		_get = NMS_CLASS.getMethod("get", String.class);
    		_getByte = NMS_CLASS.getMethod("getByte", String.class);
    		_getShort = NMS_CLASS.getMethod("getShort", String.class);
    		_getInt = NMS_CLASS.getMethod("getInt", String.class);
    		_getLong = NMS_CLASS.getMethod("getLong", String.class);
    		_getFloat = NMS_CLASS.getMethod("getFloat", String.class);
    		_getDouble = NMS_CLASS.getMethod("getDouble", String.class);
    		_getString = NMS_CLASS.getMethod("getString", String.class);
    		_getByteArray = NMS_CLASS.getMethod("getByteArray", String.class);
    		_getIntArray = NMS_CLASS.getMethod("getIntArray", String.class);
    		_getCompound = NMS_CLASS.getMethod("getCompound", String.class);
    		_getBoolean = NMS_CLASS.getMethod("getBoolean", String.class);
    		_getList = NMS_CLASS.getMethod("getList", String.class);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		getTypeId = _getTypeId;
    		toString = _toString;
    		clone = _clone;
    		equals = _equals;
    		hashCode = _hashCode;
    		
    		getValues = _getValues;
    		hasKey = _hasKey;
    		remove = _remove;
    		isEmpty = _isEmpty;
    		
    		set = _set;
    		setByte = _setByte;
    		setShort = _setShort;
    		setInt = _setInt;
    		setLong = _setLong;
    		setFloat = _setFloat;
    		setDouble = _setDouble;
    		setString = _setString;
    		setByteArray = _setByteArray;
    		setIntArray = _setIntArray;
    		setCompound = _setCompound;
    		setBoolean = _setBoolean;
    		
    		get = _get;
    		getByte = _getByte;
    		getShort = _getShort;
    		getInt = _getInt;
    		getLong = _getLong;
    		getFloat = _getFloat;
    		getDouble = _getDouble;
    		getString = _getString;
    		getByteArray = _getByteArray;
    		getIntArray = _getIntArray;
    		getCompound = _getCompound;
    		getBoolean = _getBoolean;
    		getList = _getList;
    	}
    }
    
    /**
     * @param value
     * @return created tag, or null if an exception is thrown
     */
    public static NBTTagCompound createTag() {
    	try {
			return new NBTTagCompound(NMS_CLASS.getConstructor().newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public static Class<?> getNMSClass() {
    	try {
			return Class.forName(NMSManager.getNMSPackage() + ".NBTTagCompound");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }

}
