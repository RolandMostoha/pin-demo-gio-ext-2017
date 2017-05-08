package hu.autsoft.googleio.demo.pin;

import hu.autsoft.googleio.demo.pin.authentication.MockAuthenticationService;
import hu.autsoft.googleio.demo.pin.network.AuthenticationApi;
import hu.autsoft.googleio.demo.pin.preferences.InMemoryPreferences;
import hu.autsoft.googleio.demo.pin.preferences.PreferenceApi;

public class Injection {
	public static PreferenceApi providePreferenceApi() {
		return new InMemoryPreferences();
	}
	public static AuthenticationApi provideAuthenticationApi() {
		return new MockAuthenticationService();
	}
}
