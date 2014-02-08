package fr.mathdu07.nbtviewer.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractConfig {
	
	protected Map<String, Object> values;
	protected boolean autoSave;
	
	/**
	 * An abstract configuration
	 * @param autoSave - if the config should be saved after any value set
	 */
	public AbstractConfig(boolean autoSave) {
		this.values = new HashMap<String, Object>();
		this.autoSave = autoSave;
	}
	
	public abstract void reload();
	
	public abstract void load();
	
	protected abstract void loadValues();
	
	protected abstract void clearValues();
	
	public abstract void save();
	
	/**
	 * @return is config's auto-save enabled
	 */
	public boolean isAutoSave() {
		return autoSave;
	}
	
	/**
	 * Set if the config should be auto-saved
	 * @param autosave
	 */
	public void setAutoSave(boolean autosave) {
		this.autoSave = autosave;
	}
	
	/**
	 * @param key
	 * @return the value's class of the key
	 */
	public Class<?> getValueClass(String key) {
		return values.get(key).getClass();
	}
	
	/**
	 * Set the given value
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value)  {
		values.put(key, value);
		
		if (autoSave)
			save();
	}
	
	/**
	 * @return the keys' set
	 */
	public Set<String> getKeys() {
		return values.keySet();
	}
	
	/**
	 * @param key
	 * @return the value of the given key, or null if not present
	 */
	public Object get(String key) {
		return values.get(key);
	}
	
	/**
	 * @param key
	 * @return the byte value of the given key, or null if not present
	 */
	public byte getByte(String key) {
		return (Byte) get(key);
	}
	
	/**
	 * @param key
	 * @return the short value of the given key, or null if not present
	 */
	public int getShort(String key) {
		return (Short) get(key);
	}
	
	/**
	 * @param key
	 * @return the integer value of the given key, or null if not present
	 */
	public int getInt(String key) {
		return (Integer) get(key);
	}
	
	/**
	 * @param key
	 * @return the long value of the given key, or null if not present
	 */
	public long getLong(String key) {
		return (Long) get(key);
	}
	
	/**
	 * @param key
	 * @return the float value of the given key, or null if not present
	 */
	public float getFloat(String key) {
		return (Float) get(key);
	}
	
	/**
	 * @param key
	 * @return the double value of the given key, or null if not present
	 */
	public double getDouble(String key) {
		return (Double) get(key);
	}
	
	/**
	 * @param key
	 * @return the boolean value of the given key, or null if not present
	 */
	public boolean getBoolean(String key) {
		return (Boolean) get(key);
	}
	
	/**
	 * @param String value of the given key, or null if not present
	 */
	public String getString(String key) {
		return (String) get(key);
	}

}
