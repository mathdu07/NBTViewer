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

import fr.mathdu07.nbtviewer.NBTViewerPlugin;

public class NBTTagEnd extends NBTBase {

	public NBTTagEnd() {
		super("");
	}

	@Override
	public byte getTypeId() {
		return 0;
	}

	@Override
	public NBTBase clone() {
		return new NBTTagEnd();
	}
	
	@Override
	public String toString() {
		return "END";
	}
	
	public static Class<?> getNMSClass() {
    	try {
			return Class.forName(NBTViewerPlugin.getNMSPackage() + ".NBTTagEnd");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }

}
