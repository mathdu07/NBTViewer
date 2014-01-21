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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fr.mathdu07.nbtviewer.NBTViewerPlugin;

public class NBTTagCompound extends NBTBase {

    private final Map<String, NBTBase> map = new HashMap<String, NBTBase>();

    public NBTTagCompound() {
        super("");
    }

    public NBTTagCompound(String name) {
        super(name);
    }

    public Collection<NBTBase> getValues() {
        return this.map.values();
    }

    public byte getTypeId() {
        return (byte) 10;
    }

    public void set(String name, NBTBase nbtbase) {
        this.map.put(name, nbtbase);
    }

    public void setByte(String name, byte value) {
        this.map.put(name, new NBTTagByte(name, value));
    }

    public void setShort(String name, short value) {
        this.map.put(name, new NBTTagShort(name, value));
    }

    public void setInt(String name, int value) {
        this.map.put(name, new NBTTagInt(name, value));
    }

    public void setLong(String name, long value) {
        this.map.put(name, new NBTTagLong(name, value));
    }

    public void setFloat(String name, float value) {
        this.map.put(name, new NBTTagFloat(name, value));
    }

    public void setDouble(String name, double value) {
        this.map.put(name, new NBTTagDouble(name, value));
    }

    public void setString(String name, String value) {
        this.map.put(name, new NBTTagString(name, value));
    }

    public void setByteArray(String name, byte[] value) {
        this.map.put(name, new NBTTagByteArray(name, value));
    }

    public void setIntArray(String name, int[] value) {
        this.map.put(name, new NBTTagIntArray(name, value));
    }

    public void setCompound(String name, NBTTagCompound value) {
        this.map.put(name, value);
    }

    public void setBoolean(String name, boolean value) {
        this.setByte(name, (byte) (value ? 1 : 0));
    }

    public NBTBase get(String name) {
        return (NBTBase) this.map.get(name);
    }

    public boolean hasKey(String name) {
        return this.map.containsKey(name);
    }

    public byte getByte(String name) {
        return !this.map.containsKey(name) ? 0 : ((NBTTagByte) this.map.get(name)).data;
    }

    public short getShort(String name) {
        return !this.map.containsKey(name) ? 0 : ((NBTTagShort) this.map.get(name)).data;
    }

    public int getInt(String name) {
    	return !this.map.containsKey(name) ? 0 : ((NBTTagInt) this.map.get(name)).data;
    }

    public long getLong(String name) {
    	return !this.map.containsKey(name) ? 0L : ((NBTTagLong) this.map.get(name)).data;
    }

    public float getFloat(String name) {
    	return !this.map.containsKey(name) ? 0.0F : ((NBTTagFloat) this.map.get(name)).data;
    }

    public double getDouble(String name) {
        return !this.map.containsKey(name) ? 0.0D : ((NBTTagDouble) this.map.get(name)).data;
    }

    public String getString(String name) {
        return !this.map.containsKey(name) ? "" : ((NBTTagString) this.map.get(name)).data;
    }

    public byte[] getByteArray(String name) {
       return !this.map.containsKey(name) ? new byte[0] : ((NBTTagByteArray) this.map.get(name)).data;
    }

    public int[] getIntArray(String name) {
        return !this.map.containsKey(name) ? new int[0] : ((NBTTagIntArray) this.map.get(name)).data;
    }

    public NBTTagCompound getCompound(String name) {
        return !this.map.containsKey(name) ? new NBTTagCompound(name) : (NBTTagCompound) this.map.get(name);
    }

    public NBTTagList getList(String name) {
        return !this.map.containsKey(name) ? new NBTTagList(name) : (NBTTagList) this.map.get(name);
    }

    public boolean getBoolean(String name) {
        return this.getByte(name) != 0;
    }

    public void remove(String name) {
        this.map.remove(name);
    }

    public String toString() {
    	return name + ":";
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public NBTBase clone() {
        NBTTagCompound nbttagcompound = new NBTTagCompound(this.getName());
        Iterator<?> iterator = this.map.keySet().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            nbttagcompound.set(s, ((NBTBase) this.map.get(s)).clone());
        }

        return nbttagcompound;
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) object;

            return this.map.entrySet().equals(nbttagcompound.map.entrySet());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ this.map.hashCode();
    }
    
    public static Class<?> getNMSClass() {
    	try {
			return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagCompound");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }

}
