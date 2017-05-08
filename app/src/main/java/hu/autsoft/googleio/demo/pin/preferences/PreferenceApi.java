package hu.autsoft.googleio.demo.pin.preferences;

import android.content.Context;

public interface PreferenceApi {

	void open(Context context);

	void saveString(String key, String value);

	String getString(String key, String defaultValue);

	void clear();
}
