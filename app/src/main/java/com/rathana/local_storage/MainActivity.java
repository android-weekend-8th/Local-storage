package com.rathana.local_storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.rathana.local_storage.service.UserService;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView output;
    Button buttonSave, buttonRead;

    private UserService userService;

    static final String KEY_CONTENT = "content";
    static final String PREF_FILE_1 = "file_1_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.text);
        output = findViewById(R.id.output);
        buttonSave = findViewById(R.id.buttonSave);
        buttonRead = findViewById(R.id.buttonRead);

        userService = new UserService(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                writeToPref(text);
                Toast.makeText(
                        MainActivity.this,
                        "saved",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFromPref();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userService.getUser() == null) {
            openLogin();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout: {
                userService.logout();
                openLogin();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void openLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void writeToPref(String content) {
        //write content
        //SharedPreferences.Editor editor = getPref().edit();
        SharedPreferences.Editor editor = getSharePref(PREF_FILE_1).edit();
        editor.putString(KEY_CONTENT, content);
        editor.apply();
    }

    private void readFromPref() {
//      String content = getPref().getString(KEY_CONTENT, "");
        String content = getSharePref(PREF_FILE_1).getString(KEY_CONTENT, "");
        output.setText(content);
    }

    private SharedPreferences getPref() {
        return getPreferences(Context.MODE_PRIVATE);
    }

    private SharedPreferences getSharePref(String fileName) {
        return getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

}
