package hu.autsoft.googleio.demo.pin.ui.pin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import hu.autsoft.googleio.demo.pin.Injection;
import hu.autsoft.googleio.demo.pin.R;
import hu.autsoft.googleio.demo.pin.network.AuthenticationApi;
import hu.autsoft.googleio.demo.pin.preferences.PreferenceApi;
import hu.autsoft.googleio.demo.pin.ui.home.HomeActivity;

public class PinActivity extends AppCompatActivity {

	public static final String PREFERENCE_KEY_PIN = "PREFERENCE_KEY_PIN";

	private Toolbar toolbar;
	private TextInputLayout inputLayoutPin;
	private EditText inputPin;
	private FloatingActionButton buttonSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pin);

		initViewReferences();
		initViews();
	}

	private void initViewReferences() {
		inputLayoutPin = (TextInputLayout) findViewById(R.id.input_layout_pin);
		inputPin = (EditText) findViewById(R.id.input_pin);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		buttonSubmit = (FloatingActionButton) findViewById(R.id.btn_submit);
	}

	private void initViews() {
		setSupportActionBar(toolbar);
		buttonSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				submitPin();
			}
		});
	}

	private void submitPin() {
		if (isPinValid()) {
			savePin();
			navigateToMainActivity();
		}
	}

	private boolean isPinValid() {
		if (TextUtils.isEmpty(inputPin.getText().toString())) {
			inputLayoutPin.setError(getString(R.string.pin_error_empty));
			return false;
		} else if (inputPin.getText().toString().length() != 6) {
			inputLayoutPin.setError(getString(R.string.pin_error_length));
			return false;
		}
		AuthenticationApi service = Injection.provideAuthenticationApi();
		if (!service.isPinValid(inputPin.getText().toString())) {
			inputLayoutPin.setError(getString(R.string.pin_error_invalid));
			return false;
		}
		return true;
	}

	private void savePin() {
		PreferenceApi preferenceApi = Injection.providePreferenceApi();
		preferenceApi.open(this);
		preferenceApi.saveString(PREFERENCE_KEY_PIN, inputPin.getText().toString());
	}

	private void navigateToMainActivity() {
		startActivity(new Intent(this, HomeActivity.class));
	}

}
