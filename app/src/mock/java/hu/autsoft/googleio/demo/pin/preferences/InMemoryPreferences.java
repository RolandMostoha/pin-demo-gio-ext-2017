package hu.autsoft.googleio.demo.pin.preferences;

import android.content.Context;

import java.util.Hashtable;
import java.util.Map;

public class InMemoryPreferences implements PreferenceApi {

	private static Map<String, String> preferenceMap;

	@Override
	public void open(final Context context) {
		preferenceMap = new Hashtable<>();
	}

	@Override
	public void saveString(final String key, final String value) {
		preferenceMap.put(key, value);
	}

	@Override
	public String getString(final String key, final String defaultValue) {
		if (preferenceMap.containsKey(key)) {
			return preferenceMap.get(key);
		}
		return defaultValue;
	}

	@Override
	public void clear() {
		preferenceMap.clear();
	}

}
