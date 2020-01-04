package com.jamesyeon.passwordmanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

public class CreatePasswordActivity extends AppCompatActivity {

   private EditText firstPasswordInput, secondPasswordInput;
   private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        firstPasswordInput = findViewById(R.id.firstPasswordInput);
        secondPasswordInput = findViewById(R.id.secondPasswordInput);
        confirmBtn = findViewById(R.id.confirmBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordInput1 = firstPasswordInput.getText().toString();
                String passwordInput2 = secondPasswordInput.getText().toString();

                // no password entered case
                if(passwordInput1.equals("") || passwordInput2.equals("")) {
                    Toast.makeText(CreatePasswordActivity.this, "Enter password!", Toast.LENGTH_SHORT).show();

                // password entered case
                } else {

                    // password inputs match case
                    if (passwordInput1.equals(passwordInput2)) {
                        // save password
                        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(CreatePasswordActivity.this);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("password", passwordInput1).putStringSet("page_button_key", new HashSet<String>()).apply();


                        // send user to main app screen
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    // password inputs mismatch case
                    } else {
                        Toast.makeText(CreatePasswordActivity.this, "Passwords does not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
