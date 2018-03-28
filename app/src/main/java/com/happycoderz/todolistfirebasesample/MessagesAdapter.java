package com.happycoderz.todolistfirebasesample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesAdapter extends ArrayAdapter<Message> {


    public MessagesAdapter(@NonNull Activity context) {
        super(context, 0, new ArrayList<Message>());
    }

    @Override
    public View getView(final int position, View contentView, ViewGroup parent) {
        final ViewHolder holder;
        if (contentView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            contentView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        final Message item = getItem(position);

        holder.text.setText(String.valueOf(item.getText()));
        holder.email.setText(item.getSenderEmail());

        return contentView;
    }

    public static class ViewHolder {
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.text)
        TextView text;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public  void addMessage (Message msg) {

        add(msg);
        notifyDataSetChanged();
    }
}
