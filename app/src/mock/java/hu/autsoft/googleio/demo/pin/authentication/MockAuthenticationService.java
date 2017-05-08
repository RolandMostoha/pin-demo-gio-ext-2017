package hu.autsoft.googleio.demo.pin.authentication;

import hu.autsoft.googleio.demo.pin.network.AuthenticationApi;

public class MockAuthenticationService implements AuthenticationApi {
	@Override
	public boolean isPinValid(final String pin) {
		return pin.equals("123456");
	}
}
