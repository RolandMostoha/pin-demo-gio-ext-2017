package hu.autsoft.googleio.demo.pin.ui.pin;

import android.content.Context;
import android.support.annotation.StringRes;

public interface PinView {

	void showPinErrorMessage(@StringRes int resourceId);

	void navigateToMainActivity();

	Context provideContext();
}
