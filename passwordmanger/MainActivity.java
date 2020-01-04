package com.jamesyeon.passwordmanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public Button fullViewBtn, addAccountBtn;
    private static LinearLayout mainActLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullViewBtn = findViewById(R.id.fullViewBtn);
        addAccountBtn = findViewById(R.id.addAccountBtn);
        mainActLinearLayout = findViewById(R.id.mainActLinearLayout);

        // restore page buttons created from previous sessions
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> pageTitleSet = settings.getStringSet("page_button_key", null);

        if (!pageTitleSet.isEmpty()) {
            for (String pageTitleText : pageTitleSet) {
                Button pageBtn = new Button(this);
                pageBtn.setText(pageTitleText);
                mainActLinearLayout.addView(pageBtn);

                pageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }

        // sends user to the new screen where user can add account
        addAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAccountActivity.class);
                startActivity(intent);
            }
        });

        // sends user to full view screen
        fullViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FullViewActivity.class);
                startActivity(intent);
            }
        });
    }

    public static LinearLayout getMainActLinearLayout() {
        return mainActLinearLayout;
    }

}
