package com.example.first.model;

public class chatmessage {
    public enum MessageType{
        ENTER, TALK
    }
    private MessageType type;
    private String sessionNum;
    private String sender;
    private String message;
}
