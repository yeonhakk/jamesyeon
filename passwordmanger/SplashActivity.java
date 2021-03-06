package com.jamesyeon.passwordmanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Space;

public class SplashActivity extends AppCompatActivity {

    private static SplashActivity splashActivity;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        password = settings.getString("password", "");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (password.equals("")) {
                    // if password does not exist, send user to password creation screen.
                    Intent intent = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    // if password does exist, send user to login screen
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2000);

    }
    public static SplashActivity getSplashActivity() {
        return splashActivity;
    }
}
