package hu.autsoft.googleio.demo.pin.network;

/**
 * The service represents a PIN validator backend service. In real project there should be a valid network
 * communication request. This call runs on the UI thread. In production environment we should move this to background
 * thread and notify via callback.
 */
public class DefaultAuthenticationService implements AuthenticationApi {

	@Override
	public boolean isPinValid(final String pin) {
		// There should be an authentication network call
		return pin.equals("123456");
	}

}
