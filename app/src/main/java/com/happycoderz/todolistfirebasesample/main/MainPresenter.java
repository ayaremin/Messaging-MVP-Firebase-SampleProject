package com.happycoderz.todolistfirebasesample.main;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.happycoderz.todolistfirebasesample.Message;
import com.happycoderz.todolistfirebasesample.User;

/**
 * Created by EminAyar on 28.03.2018.
 */

public class MainPresenter implements MainContract.Presenter  {

    private FirebaseAuth firebaseAuth;
    private Activity context;
    private MainContract.View mainView;
    private FirebaseDatabase fbDatabase;
    private DatabaseReference messagesTable;

    public MainPresenter(Activity context, MainContract.View view) {
        this.context = context;
        this.mainView = view;
        firebaseAuth = FirebaseAuth.getInstance();
        fbDatabase = FirebaseDatabase.getInstance();
        messagesTable = fbDatabase.getReference("messages");

    }


    @Override
    public void startListeningMessages() {
        messagesTable.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mainView.onMessageAdded(dataSnapshot.getValue(Message.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onMessageSendClicked(String message) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        Message msg = new Message(user.getEmail(), message);

        messagesTable.push().setValue(msg);
    }
}
