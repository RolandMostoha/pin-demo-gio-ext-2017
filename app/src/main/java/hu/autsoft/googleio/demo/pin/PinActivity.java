package hu.autsoft.googleio.demo.pin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import hu.autsoft.googleio.demo.pin.main.MainActivity;

public class PinActivity extends AppCompatActivity {

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
			navigateToMainActivity();
		}
	}

	private boolean isPinValid() {
		boolean isPinValid = true;
		if (inputPin.getText().toString() == null || inputPin.getText().toString().isEmpty()) {
			isPinValid = false;
			inputLayoutPin.setError("Cannot be empty");
		} else if (inputPin.getText().toString().length() != 6) {
			isPinValid = false;
			inputLayoutPin.setError("PIN length should be 6 character");
		}
		return isPinValid;
	}

	private void navigateToMainActivity() {
		startActivity(new Intent(this, MainActivity.class));
	}

}
