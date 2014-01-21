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

import org.bukkit.ChatColor;

import fr.mathdu07.nbtviewer.NBTViewerPlugin;

public class NBTTagLong extends NBTBase {

    public final long data;

    public NBTTagLong(String name) {
        super(name);
        this.data = 0l;
    }

    public NBTTagLong(String name, long value) {
        super(name);
        this.data = value;
    }

    public byte getTypeId() {
        return (byte) 4;
    }

    public String toString() {
        return name + ": " + ChatColor.DARK_AQUA + this.data;
    }

    public NBTBase clone() {
        return new NBTTagLong(this.getName(), this.data);
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagLong nbttaglong = (NBTTagLong) object;

            return this.data == nbttaglong.data;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode() ^ (int) (this.data ^ this.data >>> 32);
    }
    
    public static Class<?> getNMSClass() {
    	try {
			return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagLong");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }

}
