package com.happycoderz.todolistfirebasesample;

/**
 * Created by EminAyar on 28.03.2018.
 */

public class User {
    private String uid;
    private String email;

    public User(String uid, String email) {
        this.uid = uid;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
