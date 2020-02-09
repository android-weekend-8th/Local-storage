package com.rathana.local_storage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rathana.local_storage.service.UserService;

public class LoginActivity extends AppCompatActivity {

    EditText userName, password;
    Button buttonLogin;

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        userService = new UserService(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo login
                String name = userName.getText().toString();
                String pass = password.getText().toString();
                if (userService.login(name.trim(), pass.trim())) {
                    // login success
                    openHome();
                } else {
                    Toast.makeText(LoginActivity.this, "login fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
