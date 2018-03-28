package com.happycoderz.todolistfirebasesample.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.happycoderz.todolistfirebasesample.Message;
import com.happycoderz.todolistfirebasesample.MessagesAdapter;
import com.happycoderz.todolistfirebasesample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainPresenter presenter;

    @BindView(R.id.list_view)
    public ListView listView;
    @BindView(R.id.message_et)
    public EditText messageET;
    @BindView(R.id.send_btn)
    public Button sendButton;

    private MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        adapter = new MessagesAdapter(this);
        listView.setAdapter(adapter);

        presenter = new MainPresenter(this, this);

        presenter.startListeningMessages();
    }

    @OnClick (R.id.send_btn) void onSendClicked () {
        String message = messageET.getText().toString();
        presenter.onMessageSendClicked(message);
    }

    @Override
    public void onMessageAdded(Message message) {
        adapter.addMessage(message);
        listView.smoothScrollToPosition(adapter.getCount());
    }
}
