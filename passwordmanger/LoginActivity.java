package com.jamesyeon.passwordmanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText passwordInput;
    private Button enterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // load actual password to "password"
        // SharedPreferences settings = getSharedPreferences("PREFS", 0);


        passwordInput = (EditText) findViewById(R.id.passwordInput);
        enterBtn = (Button) findViewById(R.id.enterBtn);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordAttempt = passwordInput.getText().toString();
                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                String password = settings.getString("password", "");

                // if password entered is correct then send user to the main app screen
                if(passwordAttempt.equals(password)) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                // password mismatch case
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
