package com.happycoderz.todolistfirebasesample;

/**
 * Created by EminAyar on 28.03.2018.
 */

public class Message {

    private String senderEmail;
    private String text;

    public Message() {
    }

    public Message(String senderEmail, String text) {
        this.senderEmail = senderEmail;
        this.text = text;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
