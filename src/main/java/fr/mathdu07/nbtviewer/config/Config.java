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
package fr.mathdu07.nbtviewer.config;

import java.io.File;

public class Config extends YamlConfig {
	
	public static final String ENABLED = "enabled";
	public static final String ITEM_ID = "wand-item-id";

	public Config(boolean autoSave, File file) {
		super(autoSave, file);
	}

	@Override
	protected void loadValues() {
		values.put(ENABLED, true);
		values.put(ITEM_ID, 369);
	}

}
