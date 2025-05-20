package com.example.llamachatbotapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {
    EditText etMessage;
    ImageButton btnSend;
    MessageAdapter adapter;
    List<Message> messageList = new ArrayList<>();
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ListView chatListView = findViewById(R.id.chatListView);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        username = getIntent().getStringExtra("username");
        messageList.add(new Message("Welcome " + username + "!", false));

        // MessageAdapter should extend ArrayAdapter<Message>
        adapter = new MessageAdapter(this, messageList);
        chatListView.setAdapter(adapter);

        btnSend.setOnClickListener(v -> {
            String userMsg = etMessage.getText().toString().trim();
            if (!userMsg.isEmpty()) {
                sendMessage(userMsg);
            }
        });
    }

    private void sendMessage(String text) {
        // Add user message
        messageList.add(new Message(text, true));
        adapter.notifyDataSetChanged();
        etMessage.setText("");

        // Prepare data for POST
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("userMessage", text);
        payload.put("chatHistory", new ArrayList<>()); // (optional) can pass chat history

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        apiService.sendMessage(payload).enqueue(new Callback<BotResponse>() {
            @Override
            public void onResponse(Call<BotResponse> call, Response<BotResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    messageList.add(new Message(response.body().getMessage(), false));
                } else {
                    messageList.add(new Message("No response from bot.", false));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BotResponse> call, Throwable t) {
                // Log the full error for debugging
                Log.e("ChatApp", "Network error: ", t);

                // Show the error message as a toast (optional but helpful)
                Toast.makeText(ChatActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();

                // Add your existing UI update
                messageList.add(new Message("Failed to connect to server.", false));
                adapter.notifyDataSetChanged();
            }
        });
    }
}
