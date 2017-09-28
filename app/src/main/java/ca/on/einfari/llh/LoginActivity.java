package ca.on.einfari.llh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    EditText txtUsername;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }

    public void login(View view) {
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

        if (txtUsername.getText().toString().equals(USERNAME) &&
                txtPassword.getText().toString().equals(PASSWORD)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Username or password is incorrect.", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}