package com.lfbl.gymglishsites;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lfbl.gymglishsites.data.GymglishContract.LoginEntry;
import com.lfbl.gymglishsites.data.GymglishDbHelper;

public class LoginActivity extends Activity {

    View mRootView;

    private static final String[] LOGIN_COLUMNS = {
            LoginEntry._ID,
            LoginEntry.COLUMN_USER,
            LoginEntry.COLUMN_PASS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        deleteDatabase(GymglishDbHelper.DATABASE_NAME);

        mRootView = findViewById(android.R.id.content);

        Button buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        EditText inputUsername = (EditText) findViewById(R.id.inputUsername);
        EditText inputPassword = (EditText) findViewById(R.id.inputPassword);

        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        if (!username.isEmpty() && !password.isEmpty()) {
            Cursor c = getContentResolver().query(
                    LoginEntry.CONTENT_URI,
                    LOGIN_COLUMNS,
                    LoginEntry.COLUMN_USER + " = ? AND " + LoginEntry.COLUMN_PASS + " = ?",
                    new String[]{inputUsername.getText().toString(), inputPassword.getText().toString()},
                    null);

            if (c != null && c.moveToFirst()) {
                // Successfully logged in
                Intent intent = new Intent(this, WebsitesActivity.class);
                startActivity(intent);
            } else {
                showMessage(getString(R.string.error_field_incorrect));
            }

            if (c != null) c.close();
        } else {
            // Empty fields not allowed
            showMessage(getString(R.string.error_fields_required));
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
