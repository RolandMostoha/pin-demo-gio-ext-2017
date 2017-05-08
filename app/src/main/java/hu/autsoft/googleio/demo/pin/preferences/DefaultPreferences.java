package hu.autsoft.googleio.demo.pin.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Objects;

public class DefaultPreferences implements PreferenceApi {

	private SharedPreferences preferences;

	@Override
	public void open(final Context context) {
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	@Override
	public void saveString(final String key, final String value) {
		Objects.requireNonNull(preferences, "You should open the Context first");
		preferences.edit()
				.putString(key, value)
				.apply();
	}

	@Override
	public String getString(final String key, final String defaultValue) {
		Objects.requireNonNull(preferences, "You should open the Context first");
		if (preferences.contains(key)) {
			return preferences.getString(key, defaultValue);
		}
		return defaultValue;
	}

	@Override
	public void clear() {
		Objects.requireNonNull(preferences, "You should open the Context first");
		preferences.edit().clear().apply();
	}
}
