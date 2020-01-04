package com.jamesyeon.passwordmanger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.Set;

import static com.jamesyeon.passwordmanger.MainActivity.getMainActLinearLayout;

public class AddAccountActivity extends AppCompatActivity {

    private EditText pageTitleText, idText, passwordText;
    private Button accountConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        pageTitleText = findViewById(R.id.pageTitleText);
        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);

        accountConfirmBtn = findViewById(R.id.accountConfirmBtn);

        /*
        confirms page title, account id, and password
        passes data into the database and stores
        creates new button in the "MainActivity" screen with the name of page title
         */
         accountConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*
                  "page_button_key" is a SharedPreferences. It is a key that stores Set of Strings.

                  NOTE: "page_button_key" has empty Set of Strings as a default value (created in "CreatePasswordActivity".
                  So empty indicates that we have no page buttons to restore.

                  ex) Create 4 page title buttons; Google, Amazon, NCR, Facebook (added in given order)

                  "page_button_key" -> Set: {"Google", "Amazon", "NCR", "Facebook"}

                  We can then restore buttons by calling key-value pairs via iteration when entering to
                  MainActivity screen.
                 */

                 // load data from SharedPreferences
                SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(AddAccountActivity.this);
                Set<String> pageTitleSet = setting.getStringSet("page_button_key", null);

                // change EditText to String
                String pageTitle = pageTitleText.getText().toString();
                String id = idText.getText().toString();
                String password = passwordText.getText().toString();

                // update String set
                pageTitleSet.add(pageTitle);

                /*
                  update SharedPreference : Note that for String Set in SharedPreferences needs extra care.
                        Modifying returned value from getStringSet() method leads to inconsistency in SharedPreferences
                        and therefore data is no more guaranteed to be stored safely.

                        Possible solution is -> modify the StringSet -> remove key-value pair in the SharedPreferences
                                ->  then putStringSet(StringSetModified).apply()
                 */

                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(AddAccountActivity.this);
                SharedPreferences.Editor editor = settings.edit();
                editor.remove("page_button_key").apply();
                editor.putStringSet("page_button_key", pageTitleSet).apply();

                finish();
            }
        });

    }


}

