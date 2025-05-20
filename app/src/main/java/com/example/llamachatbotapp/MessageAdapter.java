package com.example.llamachatbotapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {
    public MessageAdapter(@NonNull Context context, @NonNull List<Message> messages) {
        super(context, 0, messages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Message msg = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    msg.isUser() ? R.layout.item_message : R.layout.item_message_bot, parent, false);
        }
        TextView tvMsg = convertView.findViewById(R.id.tvMessage);
        tvMsg.setText(msg.getText());
        return convertView;
    }
}
