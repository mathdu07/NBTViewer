package fr.mathdu07.nbtviewer.config;

import java.io.File;

public class Config extends YamlConfig {
	
	public static final String ENABLED = "enabled";

	public Config(boolean autoSave, File file) {
		super(autoSave, file);
	}

	@Override
	protected void loadValues() {
		values.put(ENABLED, true);
	}

}
