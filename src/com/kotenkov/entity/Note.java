package com.kotenkov.entity;

import java.util.Date;

public class Note {

    private String topic;
    private Date date;
    private String email;
    private String message;

    public Note() {
    }

    public Note(String topic, Date date, String email, String message) {
        this.topic = topic;
        this.date = date;
        this.email = email;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (!topic.equals(note.topic)) return false;
        if (!date.equals(note.date)) return false;
        if (!email.equals(note.email)) return false;
        return message.equals(note.message);
    }

    @Override
    public int hashCode() {
        int result = topic.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }
}
