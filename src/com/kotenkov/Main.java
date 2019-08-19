package com.kotenkov;

//        Задание 2. Блокнот. Разработать консольное приложение, работающее с Заметками
//        в Блокноте. Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение).

//        Общие пояснения к практическому заданию.
//        • В начале работы приложения данные должны считываться из файла, в конце
//        работы – сохраняться в файл.
//        • У пользователя должна быть возможность найти запись по любому параметру
//        или по группе параметров (группу параметров можно определить
//        самостоятельно), получить требуемые записи в отсортированном виде, найти
//        записи, текстовое поле которой содержит определенное слово, а также
//        добавить новую запись.
//        • Особое условие: поиск, сравнение и валидацию вводимой информации
//        осуществлять с использованием регулярных выражений.
//        • Особое условие: проверку введенной информации на валидность должен
//        осуществлять код, непосредственно добавляющий информацию.

import com.kotenkov.entity.Note;
import com.kotenkov.entity.Notebook;
import com.kotenkov.input_output.console.InputHandler;
import com.kotenkov.input_output.file.FileProcessing;
import com.kotenkov.menu.Menu;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InputHandler ih = new InputHandler();
        FileProcessing fp = new FileProcessing();
        List<Note> noteList = null;
        Notebook notebook = null;

        try {
            noteList = fp.readNotes("notebook.txt");
            notebook = new Notebook(noteList);
            Menu menu = new Menu(ih, notebook);
            menu.doMainMenu();
            fp.writeNotes(notebook.getNotes(), "notebook.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
