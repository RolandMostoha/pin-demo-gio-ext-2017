package hu.autsoft.googleio.demo.pin.ui.pin;

import hu.autsoft.googleio.demo.pin.R;
import hu.autsoft.googleio.demo.pin.network.AuthenticationApi;
import hu.autsoft.googleio.demo.pin.preferences.PreferenceApi;

public class PinPresenter {

	private PinView pinView;
	private AuthenticationApi authenticationApi;
	private PreferenceApi preferenceApi;

	public PinPresenter(PinView pinView, AuthenticationApi authenticationApi, PreferenceApi preferenceApi) {
		this.pinView = pinView;
		this.authenticationApi = authenticationApi;
		this.preferenceApi = preferenceApi;
	}

	public void submitPin(final String pin) {
		if (isPinValid(pin)) {
			savePin(pin);
			navigateToMainActivity();
		}
	}

	private boolean isPinValid(final String pin) {
		if (pin == null || pin.isEmpty()) {
			pinView.showPinErrorMessage(R.string.pin_error_empty);
			return false;
		} else if (pin.length() != 6) {
			pinView.showPinErrorMessage(R.string.pin_error_length);
			return false;
		}
		if (!authenticationApi.isPinValid(pin)) {
			pinView.showPinErrorMessage(R.string.pin_error_invalid);
			return false;
		}
		return true;
	}

	private void savePin(final String pin) {
		preferenceApi.open(pinView.provideContext());
		preferenceApi.saveString(PinActivity.PREFERENCE_KEY_PIN, pin);
	}

	private void navigateToMainActivity() {
		pinView.navigateToMainActivity();
	}

}
