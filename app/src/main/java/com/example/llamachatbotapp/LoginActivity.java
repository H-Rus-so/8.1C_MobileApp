package com.example.llamachatbotapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername;
    Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        btnGo = findViewById(R.id.btnGo);

        btnGo.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, ChatActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}
