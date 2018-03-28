package com.happycoderz.todolistfirebasesample.login;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.happycoderz.todolistfirebasesample.User;

import org.w3c.dom.Text;

/**
 * Created by EminAyar on 28.03.2018.
 */

public class LoginPresenter implements LoginContract.Presenter  {

    private FirebaseAuth firebaseAuth;
    private Activity context;
    private LoginContract.LoginView loginView;

    public LoginPresenter(Activity context, LoginContract.LoginView view) {
        this.context = context;
        this.loginView = view;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onLoginButtonClicked(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            loginView.onError("Email ve şifrenizi boş bırakamazsınız");
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            loginView.onLoginResult(user.getEmail());
                        } else {
                            loginView.onError("Login başarısız oldu");
                        }
                    }
                });
    }

    @Override
    public void onSignupButtonClicked(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            loginView.onError("Email ve şifrenizi boş bırakamazsınız");
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            saveUserToFirebase (user);
                            loginView.onSignupResult(user.getEmail());
                        } else {
                           loginView.onError("Kaydolma başarısız");
                        }
                    }
                });
    }

    private void saveUserToFirebase(FirebaseUser user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersTable = database.getReference("users");

        User newuser = new User(user.getUid(), user.getEmail());

        usersTable.child(user.getUid()).setValue(newuser);
    }
}
