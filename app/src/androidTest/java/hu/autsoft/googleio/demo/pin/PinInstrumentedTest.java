package hu.autsoft.googleio.demo.pin;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import android.view.View;
import hu.autsoft.googleio.demo.pin.*;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PinInstrumentedTest {

	@Rule
	public ActivityTestRule<PinActivity> mActivityRule = new ActivityTestRule<>(PinActivity.class);

	@Test
	public void givenEmptyPin_whenWeSubmit_thenEmptyErrorMessageShouldShown() throws Exception {
		// Given
		onView(ViewMatchers.withId(R.id.input_pin))
				.perform(typeText(""))
				.perform(closeSoftKeyboard());	
		// When
		onView(ViewMatchers.withId(R.id.btn_submit))
				.perform(click());
		// Then
		onView(ViewMatchers.withId(R.id.input_layout_pin))
				.check(matches(withError(getString(hu.autsoft.googleio.demo.pin.R.string.pin_error_empty))));
	}

	@Test
	public void givenPinBelowLength_whenWeSubmit_thenErrorMessageShouldShown() throws Exception {
		// Given
		onView(ViewMatchers.withId(R.id.input_pin))
				.perform(typeText("123"))
				.perform(closeSoftKeyboard());
		// When
		onView(ViewMatchers.withId(R.id.btn_submit))
				.perform(click());
		// Then
		onView(ViewMatchers.withId(R.id.input_layout_pin))
				.check(matches(withError(getString(R.string.pin_error_length))));
	}

	@Test
	public void givenPinAboveLength_whenWeSubmit_thenErrorMessageShouldShown() throws Exception {
		// Given
		onView(ViewMatchers.withId(R.id.input_pin))
				.perform(typeText("1234567"))
				.perform(closeSoftKeyboard());
		// When
		onView(ViewMatchers.withId(R.id.btn_submit))
				.perform(click());
		// Then
		onView(ViewMatchers.withId(R.id.input_layout_pin))
				.check(matches(withError(getString(R.string.pin_error_length))));
	}

	@Test
	public void givenValidPin_whenWeSubmit_thenMainScreenShouldShown() throws Exception {
		// Given
		onView(ViewMatchers.withId(R.id.input_pin))
				.perform(typeText("123456"))
				.perform(closeSoftKeyboard());
		// When
		onView(ViewMatchers.withId(R.id.btn_submit))
				.perform(click());
		// Then
		onView(ViewMatchers.withText(R.string.home_title))
				.check(matches(isDisplayed()));
	}

	private static Matcher<View> withError(final String expected) {
		return new TypeSafeMatcher<View>() {

			@Override
			public boolean matchesSafely(View view) {
				if (!(view instanceof TextInputLayout)) {
					return false;
				}
				TextInputLayout textInputLayout = (TextInputLayout) view;
				return textInputLayout.getError() != null && textInputLayout.getError().toString().equals(expected);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Expected: " + expected);
			}
		};
	}

	private String getString(int id) {
		Context targetContext = InstrumentationRegistry.getTargetContext();
		return targetContext.getResources().getString(id);
	}

}
