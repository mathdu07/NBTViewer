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
