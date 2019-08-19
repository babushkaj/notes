package com.kotenkov.entity;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Notebook {

    private List<Note> notes;

    public Notebook(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> sortNotesByTopic(List<Note> notes){
        Collections.sort(notes, new CompareByTopic());
        return notes;
    }

    public List<Note> sortNotesByDate(List<Note> notes){
        Collections.sort(notes, new CompareByDate());
        return notes;
    }

    public List<Note> sortNotesByEmail(List<Note> notes){
        Collections.sort(notes, new CompareByEmail());
        return notes;
    }

    public List<Note> sortNotesByMessage(List<Note> notes){
        Collections.sort(notes, new CompareByMessage());
        return notes;
    }

    public List<Note> getNotesByTopic(String topic){
        List<Note> notes = new ArrayList<>();

        for (Note n: this.notes) {
            if(n.getTopic().equalsIgnoreCase(topic.trim())){
                notes.add(n);
            }
        }

        return notes;
    }

    public List<Note> getNotesByEmail(String email){
        List<Note> notes = new ArrayList<>();

        for (Note n: this.notes) {
            if(n.getEmail().equals(email.trim())){
                notes.add(n);
            }
        }

        return notes;
    }

    public List<Note> getNotesByDate(Date date){
        List<Note> notes = new ArrayList<>();

        for (Note n: this.notes) {
            if(n.getDate().equals(date)){
                notes.add(n);
            }
        }

        return notes;
    }

    public List<Note> getNotesByMessage(String message){
        List<Note> notes = new ArrayList<>();

        for (Note n: this.notes) {
            if(n.getMessage().equals(message.trim())){
                notes.add(n);
            }
        }

        return notes;
    }

    public List<Note> getNotesByTopicAndEmail(String topic, String email){
        List<Note> notes = new ArrayList<>();

        for (Note n: this.notes) {
            if(n.getTopic().equalsIgnoreCase(topic.trim()) && n.getEmail().equals(email.trim())){
                notes.add(n);
            }
        }

        return notes;
    }

    public List<Note> getNotesByMessageContent(String content){
        List<Note> notes = new ArrayList<>();

        Pattern p = Pattern.compile(".*" + content + ".*");
        for (Note n: this.notes) {
            Matcher m = p.matcher(n.getMessage());
            if(m.find()){
                notes.add(n);
            }
        }

        return notes;
    }

    public void addNote(Note note){
        if(note != null){
            this.notes.add(note);
            System.out.println("The note has been added.");
        } else {
            System.out.println("The note haven't been added. Note can't be \'null\'.");
        }
    }

    public static String oneNoteToString(Note note){
        StringBuilder sb = new StringBuilder();

        sb.append("Topic: ");
        sb.append(note.getTopic());
        sb.append("\n");
        sb.append("Date: ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sb.append(sdf.format(note.getDate()));
        sb.append("\n");
        sb.append("Email: ");
        sb.append(note.getEmail());
        sb.append("\n");
        sb.append("Message: ");
        sb.append(note.getMessage());
        sb.append("\n\n----------------------------------\n");

        return sb.toString();
    }

    public static String notesListToString(List<Note> notes){
        StringBuilder sb = new StringBuilder();

        for (Note n: notes) {
            sb.append(oneNoteToString(n));
        }

        return sb.toString();
    }

    private class CompareByTopic implements Comparator<Note>{
        @Override
        public int compare(Note n1, Note n2) {
            return n1.getTopic().compareTo(n2.getTopic());
        }
    }

    private class CompareByDate implements Comparator<Note>{
        @Override
        public int compare(Note n1, Note n2) {
            return n1.getDate().compareTo(n2.getDate());
        }
    }

    private class CompareByEmail implements Comparator<Note>{
        @Override
        public int compare(Note n1, Note n2) {
            return n1.getEmail().compareTo(n2.getEmail());
        }
    }

    private class CompareByMessage implements Comparator<Note>{
        @Override
        public int compare(Note n1, Note n2) {
            return n1.getMessage().compareTo(n2.getMessage());
        }
    }


}
