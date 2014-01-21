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

import java.util.Arrays;

import org.bukkit.ChatColor;

import fr.mathdu07.nbtviewer.NBTViewerPlugin;

public class NBTTagIntArray extends NBTBase {

    public final int[] data;

    public NBTTagIntArray(String name) {
        super(name);
        this.data = new int[0];
    }

    public NBTTagIntArray(String name, int[] value) {
        super(name);
        this.data = value;
    }

    public byte getTypeId() {
        return (byte) 11;
    }

    public String toString() {
        return name + ": " + ChatColor.DARK_GREEN + Arrays.toString(data);
    }

    public NBTBase clone() {
        int[] aint = new int[this.data.length];

        System.arraycopy(this.data, 0, aint, 0, this.data.length);
        return new NBTTagIntArray(this.getName(), aint);
    }

    public boolean equals(Object object) {
        if (!super.equals(object)) {
            return false;
        } else {
            NBTTagIntArray nbttagintarray = (NBTTagIntArray) object;

            return this.data == null && nbttagintarray.data == null || this.data != null && Arrays.equals(this.data, nbttagintarray.data);
        }
    }

    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.data);
    }
    
    public static Class<?> getNMSClass() {
    	try {
			return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTabIntArray");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }

}
