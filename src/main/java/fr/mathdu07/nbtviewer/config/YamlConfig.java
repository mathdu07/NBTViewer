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
