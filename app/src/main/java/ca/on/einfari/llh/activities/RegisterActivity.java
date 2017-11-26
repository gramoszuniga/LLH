/*
    RegisterActivity.java
    Assignment 1

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.09.27: Created
 */

package ca.on.einfari.llh.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.data.LLHDatabase;
import ca.on.einfari.llh.data.User;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        radMale = findViewById(R.id.radMale);
        radFemale = findViewById(R.id.radFemale);
        txtEmail = findViewById(R.id.txtEmail);
    }

    public void register(View view) throws ExecutionException, InterruptedException {
        txtUsername.setError(null);
        txtPassword.setError(null);
        txtConfirmPassword.setError(null);
        txtFirstName.setError(null);
        txtLastName.setError(null);
        radMale.setError(null);
        txtEmail.setError(null);
        View focusedView;
        final RadioButton checkedRadioButton;

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

        if (radMale.isChecked()) {
            checkedRadioButton = radMale;
        } else {
            checkedRadioButton = radFemale;
        }

        long rowId = new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... voids) {
                return LLHDatabase.getDatabase(getApplicationContext()).userDao().create(new User(
                        txtUsername.getText().toString(),
                        txtPassword.getText().toString(),
                        txtFirstName.getText().toString(),
                        txtLastName.getText().toString(),
                        checkedRadioButton.getText().toString(),
                        txtEmail.getText().toString()));
            }

        }.execute().get();

        if (rowId == -1) {
            Toast.makeText(RegisterActivity.this,
                    "There has been a problem with your registration. Please try again.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegisterActivity.this, "You have registered successfully.",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this,
                    LoginActivity.class);
            startActivity(intent);
        }

    }

    public void onRadioButtonClicked(View view) {
        radMale.setFocusableInTouchMode(false);
        radMale.setError(null);
    }

    @Override
    protected void onDestroy() {
        LLHDatabase.destroyInstance();
        super.onDestroy();
    }

}