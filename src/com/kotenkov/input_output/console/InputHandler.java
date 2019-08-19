package com.kotenkov.input_output.console;

import java.util.Scanner;

public class InputHandler {

    public static final String EMAIL_PAT = "[\\w\\d]+@\\w{2,5}\\.\\w{2,5}";
    public static final String BACK_PAT = "[Bb][Aa][Cc][Kk]";
    public static final String DATE_PAT = "\\d{2}\\.\\d{2}\\.\\d{4}";

    Scanner s;

    public InputHandler() {
        s = new Scanner(System.in);
    }

    public int enterInt(int from, int to){
        s.reset();
        int number = -1;

        while (!(number >= from && number <= to)) {
            System.out.println("\nEnter number from " + from + " to " + to + ":");
            while (!s.hasNextInt()) {
                s.next();
                System.out.println("\nEnter number from " + from + " to " + to + ":");
            }

            number = s.nextInt();

            if (number >= from && number <= to) {
                break;
            }
        }

        return number;
    }

    public String enterEmail(){
        StringBuilder sb = new StringBuilder();

        System.out.println("\nWaiting for input...");
        while (!(s.hasNext(EMAIL_PAT) || s.hasNext(BACK_PAT))) {
            s.next();
            System.out.println("\nWaiting for input...");
        }

        sb.append(s.next());

        return sb.toString();
    }

    public String enterPhrase() {
        StringBuilder sb = new StringBuilder();

        System.out.println("\nWaiting for input...");

        while(s.hasNextLine()){
            String str = s.nextLine();
            if(!str.isEmpty()){
                sb.append(str);
                break;
            }
        }

        return sb.toString();
    }

    public String enterDate(){

        System.out.println("\nWaiting for input...");
        while (!(s.hasNext(DATE_PAT) || s.hasNext(BACK_PAT))) {
            s.next();
            System.out.println("\nWaiting for input...");
        }

        return s.next();

    }
}
