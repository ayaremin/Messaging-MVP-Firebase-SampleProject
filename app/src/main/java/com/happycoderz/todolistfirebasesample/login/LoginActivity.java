package com.happycoderz.todolistfirebasesample.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.happycoderz.todolistfirebasesample.R;
import com.happycoderz.todolistfirebasesample.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    LoginPresenter presenter;

    @BindView(R.id.email_et)
    public EditText emailET;
    @BindView(R.id.password_et)
    public EditText passwordET;
    @BindView(R.id.login_btn)
    public Button loginButton;
    @BindView(R.id.signup_btn)
    public Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        presenter = new LoginPresenter(this, this);

    }

    @OnClick(R.id.login_btn) public void onLoginClicked () {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        presenter.onLoginButtonClicked(email, password);
    }

    @OnClick(R.id.signup_btn) public void onSignupClicked () {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        presenter.onSignupButtonClicked(email, password);
    }

    @Override
    public void onLoginResult(String email) {
        Toast.makeText(this, email, Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onSignupResult(String email) {
        Toast.makeText(this, email, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}
