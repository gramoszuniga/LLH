/*
    LoginActivity.java
    Assignment 1

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.09.18: Created
 */

package ca.on.einfari.llh.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ca.on.einfari.llh.R;
import ca.on.einfari.llh.data.LLHDatabase;
import ca.on.einfari.llh.data.User;
import ca.on.einfari.llh.preferences.LLHSharedPreferences;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
    }

    public void login(View view) throws ExecutionException, InterruptedException {
        txtUsername.setError(null);
        txtPassword.setError(null);
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
        }

        User user = new AsyncTask<String, Void, User>() {

            @Override
            protected User doInBackground(String... strings) {
                return LLHDatabase.getDatabase(getApplicationContext()).userDao().read(strings[0]);
            }

        }.execute(txtUsername.getText().toString()).get();

        if (user != null && txtUsername.getText().toString().equals(user.getUserName()) &&
                txtPassword.getText().toString().equals(user.getPassword())) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Username or password is incorrect.", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    protected void onDestroy() {
        LLHDatabase.destroyInstance();
        super.onDestroy();
    }

}