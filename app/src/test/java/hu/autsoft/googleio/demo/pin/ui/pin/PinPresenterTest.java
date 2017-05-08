package hu.autsoft.googleio.demo.pin.ui.pin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import hu.autsoft.googleio.demo.pin.Injection;
import hu.autsoft.googleio.demo.pin.R;
import hu.autsoft.googleio.demo.pin.network.AuthenticationApi;
import hu.autsoft.googleio.demo.pin.preferences.PreferenceApi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PinPresenterTest {

	@Mock
	private PinView view;
	private PreferenceApi preferences;
	private PinPresenter pinPresenter;

	@Before
	public void setUp() throws Exception {
		final AuthenticationApi authService = Injection.provideAuthenticationApi();
		preferences = Injection.providePreferenceApi();
		pinPresenter = new PinPresenter(view, authService, preferences);
	}

	@Test
	public void givenEmptyPin_whenWeSubmit_thenEmptyErrorMessageShouldShown() throws Exception {
		// Given
		String pin = "";
		// When
		pinPresenter.submitPin(pin);
		// Then
		verify(view).showPinErrorMessage(R.string.pin_error_empty);
	}

	@Test
	public void givenPinBelowLength_whenWeSubmit_thenErrorMessageShouldShown() throws Exception {
		// Given
		String pin = "123";
		// When
		pinPresenter.submitPin(pin);
		// Then
		verify(view).showPinErrorMessage(R.string.pin_error_length);
	}

	@Test
	public void givenPinAboveLength_whenWeSubmit_thenErrorMessageShouldShown() throws Exception {
		// Given
		String pin = "1234567";
		// When
		pinPresenter.submitPin(pin);
		// Then
		verify(view).showPinErrorMessage(R.string.pin_error_length);
	}

	@Test
	public void givenValidPin_whenWeSubmit_thenMainScreenShouldShown() throws Exception {
		// Given
		String pin = "123456";
		// When
		pinPresenter.submitPin(pin);
		// Then
		verify(view).navigateToMainActivity();
	}

	@Test
	public void givenInvalidPin_whenWeSubmit_thenPinInvalidErrorMessageShouldShown() throws Exception {
		// Given
		String pin = "123455";
		// When
		pinPresenter.submitPin(pin);
		// Then
		verify(view).showPinErrorMessage(R.string.pin_error_invalid);
	}

	@Test
	public void givenValidPin_whenWeSubmit_thenPinIsStoredInPreferences() throws Exception {
		// Given
		String pin = "123456";
		// When
		pinPresenter.submitPin(pin);
		// Then
		assertEquals(pin, preferences.getString(PinActivity.PREFERENCE_KEY_PIN, null));
	}

}