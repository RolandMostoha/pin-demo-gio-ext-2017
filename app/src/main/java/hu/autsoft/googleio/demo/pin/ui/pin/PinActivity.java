package hu.autsoft.googleio.demo.pin.ui.pin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import hu.autsoft.googleio.demo.pin.Injection;
import hu.autsoft.googleio.demo.pin.R;
import hu.autsoft.googleio.demo.pin.ui.home.HomeActivity;

public class PinActivity extends AppCompatActivity implements PinView {

	public static final String PREFERENCE_KEY_PIN = "PREFERENCE_KEY_PIN";

	private Toolbar toolbar;
	private TextInputLayout inputLayoutPin;
	private EditText inputPin;
	private FloatingActionButton buttonSubmit;
	private PinPresenter pinPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pin);

		initViewReferences();
		initViews();
		pinPresenter = new PinPresenter(this, Injection.provideAuthenticationApi(), Injection.providePreferenceApi());
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
				pinPresenter.submitPin(inputPin.getText().toString());
			}
		});
	}

	@Override
	public void showPinErrorMessage(final int resourceId) {
		inputLayoutPin.setError(getString(resourceId));
	}

	@Override
	public void navigateToMainActivity() {
		startActivity(new Intent(this, HomeActivity.class));
	}

	@Override
	public Context provideContext() {
		return this;
	}
}
