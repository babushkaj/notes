package com.kotenkov.input_output.file;

import com.kotenkov.entity.Note;
import com.kotenkov.entity.Notebook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProcessing {

    public List<Note> readNotes(String path) throws IOException {
        List<Note> notes = new ArrayList<>();

        String allNotes = null;
        try {
            allNotes = readAllFileToString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String [] allNotesArr = allNotes.trim().split("----------------------------------");

        for (String oneNoteContent: allNotesArr) {
            Note note = fillNoteInformation(oneNoteContent.trim());
            notes.add(note);
        }

        for (Note n:notes
             ) {
            System.out.println(n.getTopic() + " " + n.getDate() + " " + n.getEmail() + " " + n.getMessage());
        }
        return notes;
    }

    public void writeNotes(List<Note> notes, String path) throws IOException {
        BufferedWriter bw = null;
        FileOutputStream fos = null;
        try {
            File file = new File(path);
            if(!file.exists()) {
                try {
                    Files.createFile(Paths.get(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos,"utf8"));

            for (Note n: notes) {
                bw.write(Notebook.oneNoteToString(n));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            bw.close();
            fos.close();
        }
    }

    private Note fillNoteInformation(String oneNoteContent){

        String[] oneNoteContentArr = oneNoteContent.split("\n");
        Note note = new Note();

        Pattern p = Pattern.compile("Topic:(.*)");
        Matcher m = p.matcher(oneNoteContentArr[0]);
        if(m.find()){
            note.setTopic(m.group(1).trim());
        }

        p = Pattern.compile("Date:\\s*(\\d{2}\\.\\d{2}\\.\\d{4})");
        m = p.matcher(oneNoteContentArr[1]);
        if(m.find()) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date = null;
            try {
                date = format.parse(m.group(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            note.setDate(date);
        }

        p = Pattern.compile("Email:\\s*([\\w\\d]+@\\w{2,5}\\.\\w{2,5})");
        m = p.matcher(oneNoteContentArr[2]);
        if(m.find()) {
            note.setEmail(m.group(1));
        }

        p = Pattern.compile("Message:([^\\n\\r]+)");
        m = p.matcher(oneNoteContentArr[3]);
        if(m.find()) {
            note.setMessage(m.group(1).trim());
        }

        return note;
    }

    private String readAllFileToString(String path) throws IOException {

        BufferedReader br = null;
        FileInputStream fis = null;
        StringBuilder tmp = new StringBuilder();
        StringBuilder allBooks = new StringBuilder();
        try {
            File file = new File(path);
            if(file.exists()) {
                fis = new FileInputStream(file);
                br = new BufferedReader(new InputStreamReader(fis,"utf-8"));
            }
            while (br.ready()){
                tmp.append(br.readLine());
                if(!tmp.toString().isEmpty()){
                    allBooks.append(tmp);
                    allBooks.append("\n");
                }
                tmp.delete(0, tmp.length());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            br.close();
            fis.close();
        }

        return allBooks.toString();
    }

}
