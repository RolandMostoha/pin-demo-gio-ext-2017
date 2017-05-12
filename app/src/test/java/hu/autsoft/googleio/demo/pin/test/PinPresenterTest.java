package hu.autsoft.googleio.demo.pin.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import android.support.annotation.StringRes;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import hu.autsoft.googleio.demo.pin.Injection;
import hu.autsoft.googleio.demo.pin.R;
import hu.autsoft.googleio.demo.pin.network.AuthenticationApi;
import hu.autsoft.googleio.demo.pin.preferences.PreferenceApi;
import hu.autsoft.googleio.demo.pin.ui.pin.PinActivity;
import hu.autsoft.googleio.demo.pin.ui.pin.PinPresenter;
import hu.autsoft.googleio.demo.pin.ui.pin.PinView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PinPresenterTest {

	private PinView view;
	private PreferenceApi preferences;
	private PinPresenter pinPresenter;

	@BeforeEach
	public void setUp() throws Exception {
		view = mock(PinView.class);
		final AuthenticationApi authService = Injection.provideAuthenticationApi();
		preferences = Injection.providePreferenceApi();
		pinPresenter = new PinPresenter(view, authService, preferences);
	}

	@TestFactory
	@DisplayName("Invalid PIN tests")
	Collection<DynamicTest> givenInvalidPins_WhenWeSubmitThem_ThenProperErrorMessagesShouldShown() {
		Set<DynamicTest> tests = new LinkedHashSet<>();
		tests.add(createInvalidPinTest("", R.string.pin_error_empty, "empty"));
		tests.add(createInvalidPinTest("123", R.string.pin_error_length, "short"));
		tests.add(createInvalidPinTest("123455", R.string.pin_error_invalid, "long"));
		tests.add(createInvalidPinTest("1234567", R.string.pin_error_length, "invalid"));
		return tests;
	}

	private DynamicTest createInvalidPinTest(final String pin, @StringRes final int expectedResourceId,
			final String inputDescription) {
		String displayName = String.format(Locale.getDefault(),
				"Given %s input, when we submit it, then the proper error message should shown", inputDescription);
		return dynamicTest(displayName, new Executable() {
			@Override
			public void execute() throws Throwable {
				setUp();
				assumeTrue(pinPresenter != null);

				// When
				pinPresenter.submitPin(pin);
				// Then
				verify(view).showPinErrorMessage(expectedResourceId);
			}
		});
	}

	@Nested
	@DisplayName("Given valid PIN code, when we submit")
	class ValidPinTests {

		@DisplayName("then Main screen should shown")
		@Test
		public void mainScreenShouldShown() throws Exception {
			// Given
			String pin = "123456";
			// When
			pinPresenter.submitPin(pin);
			// Then
			verify(view).navigateToMainActivity();
		}

		@DisplayName("then PIN is stored")
		@Test
		public void pinIsStoredInPreferences() throws Exception {
			// Given
			String pin = "123456";
			// When
			pinPresenter.submitPin(pin);
			// Then
			assertEquals(pin, preferences.getString(PinActivity.PREFERENCE_KEY_PIN, null));
		}
	}

}