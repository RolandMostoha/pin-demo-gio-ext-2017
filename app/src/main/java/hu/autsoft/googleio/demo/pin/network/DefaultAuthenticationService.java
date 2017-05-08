package hu.autsoft.googleio.demo.pin.network;

/**
 * The service represents a PIN validator backend service. In real project there should be a valid network communication
 * request.
 */
public class DefaultAuthenticationService implements AuthenticationApi {

	@Override
	public boolean isPinValid(final String pin) {
		// There should be an authentication network call
		return pin.equals("123456");
	}

}
