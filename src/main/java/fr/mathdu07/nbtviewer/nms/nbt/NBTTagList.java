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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.mathdu07.nbtviewer.NBTViewerPlugin;

public class NBTTagList extends NBTBase {
    private final List<NBTBase> list = new ArrayList<NBTBase>();
    private byte type;

    public NBTTagList() {
        super("");
    }

    public NBTTagList(String s) {
        super(s);
    }

    public byte getTypeId() {
        return (byte) 9;
    }

    public String toString() {
        return name + ":";
    }

    public void add(NBTBase nbtbase) {
        this.type = nbtbase.getTypeId();
        this.list.add(nbtbase);
    }

    public NBTBase get(int i) {
        return (NBTBase) this.list.get(i);
    }

    public int size() {
        return this.list.size();
    }

    public NBTBase clone() {
        NBTTagList nbttaglist = new NBTTagList(this.getName());

        nbttaglist.type = this.type;
        Iterator<NBTBase> iterator = this.list.iterator();

        while (iterator.hasNext()) {
            NBTBase nbtbase = (NBTBase) iterator.next();
            NBTBase nbtbase1 = nbtbase.clone();

            nbttaglist.list.add(nbtbase1);
        }

        return nbttaglist;
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            NBTTagList nbttaglist = (NBTTagList) object;

            if (this.type == nbttaglist.type) {
                return this.list.equals(nbttaglist.list);
            }
        }

        return false;
    }

    public int hashCode() {
        return super.hashCode() ^ this.list.hashCode();
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
