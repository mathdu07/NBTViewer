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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class YamlConfig extends AbstractConfig {
	
	protected final FileConfiguration config;
	protected File configFile;
	
	/**
	 * An YaML configuration file 
	 * @param autoSave - if the config should be saved after any value set
	 * @param path - the path to the YaML file
	 */
	public YamlConfig(boolean autoSave, File file) {
		super(autoSave);
		this.configFile = file;
		this.config = new YamlConfiguration();
	}

	@Override
	public void reload() {
		clearValues();
		load();
	}

	@Override
	public void load() {
		loadValues();
		
		try {
			if (configFile.exists())
				config.load(configFile);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (InvalidConfigurationException e) {e.printStackTrace();}
		
		Set<String> keys = config.getKeys(true);
		for (String key : keys)
			values.put(key, config.get(key));
	}

	@Override
	public void save() {
		Set<String> keys = getKeys();
		
		for (String key : keys)
			config.set(key, get(key));
		
		try {
			config.save(configFile);
		} catch (IOException e) {e.printStackTrace();}
	}
	
	@Override
	public void clearValues() {
		values.clear();
	}

}
