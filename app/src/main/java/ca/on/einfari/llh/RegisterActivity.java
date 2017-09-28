package ca.on.einfari.llh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;
    EditText txtConfirmPassword;
    EditText txtFirstName;
    EditText txtLastName;
    RadioButton radMale;
    RadioButton radFemale;
    EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        radMale = (RadioButton) findViewById(R.id.radMale);
        radFemale = (RadioButton) findViewById(R.id.radFemale);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
    }

    public void register(View view) {
        txtUsername.setError(null);
        txtPassword.setError(null);
        txtConfirmPassword.setError(null);
        txtFirstName.setError(null);
        txtLastName.setError(null);
        radFemale.setError(null);
        txtEmail.setError(null);
        View focusedView;

        if (TextUtils.isEmpty(txtUsername.getText().toString().trim())) {
            txtUsername.setError("Username is required.");
            focusedView = txtUsername;
            focusedView.requestFocus();
            return;
        } else if (TextUtils.isEmpty(txtPassword.getText().toString().trim())) {
            txtPassword.setError("Password is required.");
            focusedView = txtPassword;
            focusedView.requestFocus();
            return;
        } else if (TextUtils.isEmpty(txtConfirmPassword.getText().toString().trim())) {
            txtConfirmPassword.setError("Confirm password is required.");
            focusedView = txtConfirmPassword;
            focusedView.requestFocus();
            return;
        } else if (!txtConfirmPassword.getText().toString().trim()
                .equals(txtPassword.getText().toString().trim())) {
            txtConfirmPassword.setError("Confirm password must match password.");
            focusedView = txtConfirmPassword;
            focusedView.requestFocus();
            return;
        } else if (TextUtils.isEmpty(txtFirstName.getText().toString().trim())) {
            txtFirstName.setError("First name is required.");
            focusedView = txtFirstName;
            focusedView.requestFocus();
            return;
        } else if (TextUtils.isEmpty(txtLastName.getText().toString().trim())) {
            txtLastName.setError("Last name is required.");
            focusedView = txtLastName;
            focusedView.requestFocus();
            return;
        } else if (!radMale.isChecked() && !radFemale.isChecked()) {
            radMale.setFocusableInTouchMode(true);
            radMale.setError("Gender is required.");
            focusedView = radMale;
            focusedView.requestFocus();
            return;
        } else if (TextUtils.isEmpty(txtEmail.getText().toString().trim())) {
            txtEmail.setError("E-mail is required.");
            focusedView = txtEmail;
            focusedView.requestFocus();
            return;
        }

        // Code to insert user into the database

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(this, "You have registered successfully.", Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClicked(View view) {
        radMale.setFocusableInTouchMode(false);
        radMale.setError(null);
    }
}