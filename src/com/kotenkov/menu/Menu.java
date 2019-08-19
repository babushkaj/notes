package com.kotenkov.menu;

import com.kotenkov.entity.Note;
import com.kotenkov.entity.Notebook;
import com.kotenkov.input_output.console.InputHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Menu {

    public static String MAIN_MENU_TEXT = "==============================\n" +
                                          "1. Show all notes.\n" +
                                          "2. Find note.\n" +
                                          "3. Add new note.\n" +
                                          "4. Exit.\n" +
                                          "==============================\n";

    public static String SEARCH_MENU_TEXT = "==============================\n" +
                                            "1. Find note by topic.\n" +
                                            "2. Find note by date.\n" +
                                            "3. Find note by email.\n" +
                                            "4. Find note by message.\n" +
                                            "5. Find note by topic and email.\n" +
                                            "6. Find note by message content.\n" +
                                            "7. Back.\n" +
                                            "==============================\n";

    public static String SORTING_MENU_TEXT = "==============================\n" +
                                            "1. Sort by topic.\n" +
                                            "2. Sort by date.\n" +
                                            "3. Sort by email.\n" +
                                            "4. Sort by message.\n" +
                                            "5. Back.\n" +
                                            "==============================\n";

    InputHandler ih;
    Notebook notebook;

    public Menu(InputHandler ih, Notebook notebook) {
        this.ih = ih;
        this.notebook = notebook;
    }

    public void doMainMenu(){
        List<Note> noteList = null;
        while (true){
            System.out.println(MAIN_MENU_TEXT);
            int menuNum = ih.enterInt(1,4);

            switch (menuNum){
                case 1: {
                    noteList = notebook.getNotes();
                    if(noteList != null && !noteList.isEmpty()){
                        System.out.print(Notebook.notesListToString(noteList));
                    }
                    break;
                }
                case 2:{
                    doSearchMenu();
                    break;
                }
                case 3:{
                    doAddNewNoteMenu();
                    break;
                }
            }
            if(menuNum == 4){
                break;
            }
        }
    }

    private void doSearchMenu(){
        List<Note> tmp = null;
        while (true){
            System.out.println(SEARCH_MENU_TEXT);
            int menuNum = ih.enterInt(1,7);

            switch(menuNum){
                case 1: {
                    System.out.println("Enter topic of note (or \'back\'):");
                    String topic = ih.enterPhrase();
                    if(topic.trim().equalsIgnoreCase("back")){
                        break;
                    }
                    tmp = notebook.getNotesByTopic(topic);
                    break;
                }
                case 2: {
                    System.out.println("Enter date (\'dd.MM.yyyy\' or \'back\'):");
                    String dateStr = ih.enterDate();
                    if(dateStr.equalsIgnoreCase("back")){
                        break;
                    }
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    Date date = null;
                    try {
                        date = format.parse(dateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    tmp = notebook.getNotesByDate(date);
                    break;
                }
                case 3: {
                    System.out.println("Enter email (or \'back\'):");
                    String email = ih.enterEmail();
                    if(email.trim().equalsIgnoreCase("back")){
                        break;
                    }
                    tmp = notebook.getNotesByEmail(email);
                    break;
                }
                case 4: {
                    System.out.println("Enter message (or \'back\'):");
                    String message = ih.enterPhrase();
                    if(message.trim().equalsIgnoreCase("back")){
                        break;
                    }
                    tmp = notebook.getNotesByMessage(message);
                    break;
                }
                case 5: {
                    System.out.println("Enter topic of note (or \'back\'):");
                    String topic = ih.enterPhrase();
                    if(topic.trim().equalsIgnoreCase("back")){
                        break;
                    }
                    System.out.println("Enter email (or \'back\'):");
                    String email = ih.enterEmail();
                    if(email.trim().equalsIgnoreCase("back")){
                        break;
                    }
                    tmp = notebook.getNotesByTopicAndEmail(topic, email);
                    break;
                }
                case 6: {
                    System.out.println("Enter a part of message (or \'back\'):");
                    String messagePart = ih.enterPhrase();
                    if(messagePart.trim().equalsIgnoreCase("back")){
                        break;
                    }
                    tmp = notebook.getNotesByMessageContent(messagePart);
                    break;
                }
            }

            if(menuNum == 7){
                break;
            }

            if(!(tmp.isEmpty()) && tmp != null){
                if(tmp.size() == 1){
                    System.out.println(Notebook.notesListToString(tmp));
                } else {
                    tmp = doSortingMenu(tmp);
                    if(tmp != null){
                        System.out.println(Notebook.notesListToString(tmp));
                    }
                }
            } else {
                System.out.println("Notes haven't been found.");
            }

        }
    }

    private List<Note> doSortingMenu(List<Note> list){
        List<Note> tmp = null;
        if(list != null && !list.isEmpty()){
            while (true){
                System.out.println(SORTING_MENU_TEXT);
                int menuNum = ih.enterInt(1,5);

                switch(menuNum){
                    case 1: {
                        tmp = notebook.sortNotesByTopic(list);
                        break;
                    }
                    case 2: {
                        tmp = notebook.sortNotesByDate(list);
                        break;
                    }
                    case 3: {
                        tmp = notebook.sortNotesByEmail(list);
                        break;
                    }
                    case 4: {
                        tmp = notebook.sortNotesByMessage(list);
                        break;
                    }
                }
                break;
            }
        }
        return tmp;

    }

    private void doAddNewNoteMenu() {
        System.out.println("Enter topic of note (or \'back\'):");
        String topic = ih.enterPhrase();
        if(topic.trim().equalsIgnoreCase("back")){
            return;
        }

        System.out.println("Enter email (or \'back\'):");
        String email = ih.enterEmail();
        if(email.trim().equalsIgnoreCase("back")){
            return;
        }

        System.out.println("Enter message (or \'back\'):");
        String message = ih.enterPhrase();
        if(message.trim().equalsIgnoreCase("back")){
            return;
        }

        notebook.addNote(new Note(topic, new Date(), email, message));
    }

}
