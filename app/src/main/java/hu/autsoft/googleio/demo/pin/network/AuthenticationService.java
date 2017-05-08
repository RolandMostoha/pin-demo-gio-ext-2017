package hu.autsoft.googleio.demo.pin.network;

/**
 * The service represents a PIN validator backend service. In real project there should be a valid network communication
 * request.
 */
public class AuthenticationService {

	public boolean isPinValid(final String pin) {
		return pin.equals("123456");
	}

}
