package com.happycoderz.todolistfirebasesample.login;

/**
 * Created by EminAyar on 28.03.2018.
 */

public class LoginContract {

    public interface Presenter {

        void onLoginButtonClicked (String email, String password);

        void onSignupButtonClicked (String email, String password);

    }


    public interface LoginView {

        void onLoginResult (String email);

        void onSignupResult (String email);

        void onError (String errorMessage);

    }


}
