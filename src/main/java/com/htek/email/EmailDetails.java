package com.htek.email;

import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;
import jakarta.inject.Singleton;

@Singleton
@AerospikeRecord(namespace = "test", set = "email")
public class EmailDetails {

    private int id;
    private String subject;

    private String message;


    private String to;

    @AerospikeKey
    private String email;

    private String password;

    public EmailDetails(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public EmailDetails() {
    }

    public EmailDetails(String subject, String message, String to) {

        this.subject = subject;
        this.message = message;
        this.to = to;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EmailDetails{" +
                "id='" + id + '\'' +
                "subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", to='" + to + '\'' +
                '}';
    }

}
