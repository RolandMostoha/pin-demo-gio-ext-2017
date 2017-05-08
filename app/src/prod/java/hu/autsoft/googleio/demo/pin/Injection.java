package hu.autsoft.googleio.demo.pin;

import hu.autsoft.googleio.demo.pin.network.AuthenticationApi;
import hu.autsoft.googleio.demo.pin.network.DefaultAuthenticationService;
import hu.autsoft.googleio.demo.pin.preferences.DefaultPreferences;
import hu.autsoft.googleio.demo.pin.preferences.PreferenceApi;

public class Injection {
	public static PreferenceApi providePreferenceApi() {
		return new DefaultPreferences();
	}
	public static AuthenticationApi provideAuthenticationApi() {
		return new DefaultAuthenticationService();
	}
}
