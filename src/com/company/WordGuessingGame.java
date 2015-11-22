package com.company;

import java.util.Scanner;
import java.io.*;

/**
 * Created by Capt Inzane on 11/22/2015.
 */

public class WordGuessingGame {

    public static void main(String[] args) throws IOException {
        go();
    }

    public static void go() throws IOException {
        Scanner kb = new Scanner(System.in);
        char again = 'n';
        String secret;
        StringBuffer dashes;
        final int MAXPARTS = 6;
        int bodyparts;
        boolean done;
        String guess;
        String guesses;
        char letter;

        Scanner infile = new Scanner(new FileReader("C:\\Users\\Capt Inzane\\IdeaProjects\\timer\\src\\com\\company\\hangWords.txt"));

        do {
            secret = infile.next();
            guesses = "";
            done = false;
            bodyparts = MAXPARTS;

            dashes = makeDashes(secret);

            while (!done) {
                System.out.println("Here is your word: " + dashes);
                System.out.println("Guesses so far: " + guesses);
                System.out.print("enter a guess (letter or word): ");
                guess = kb.next();
                // process the guess

                if (guess.length() > 1) {
                    if (guess.equals(secret))
                        System.out.println("you win!");
                    else
                        System.out.println("you lose");
                    done = true;
                } else {
                    letter = guess.charAt(0);
                    guesses += letter;
                    if (secret.indexOf(letter) < 0) {
                        --bodyparts;
                        System.out.print("bad guess - ");
                    } else {
                        matchLetter(secret, dashes, letter);
                    }
                    System.out.println(bodyparts + " bodyparts are left");
                    if (bodyparts == 0) {
                        System.out.println("you lose");
                        done = true;
                    }
                    if (secret.equals(dashes.toString())) {
                        System.out.println("you win!");
                        done = true;
                    }
                }

            }

            if (infile.hasNext()) {
                System.out.print("play again (y/n)?: ");
                again = kb.next().charAt(0);
            } else
                System.out.println("thanks for playing (no more words)");
        } while (infile.hasNext() && (again == 'Y' || again == 'y'));
    }

    public static void matchLetter(String secret, StringBuffer dashes, char letter) {
        for (int index = 0; index < secret.length(); index++)
            if (secret.charAt(index) == letter)
                dashes.setCharAt(index, letter);
        System.out.print("good guess - ");
    }

    public static StringBuffer makeDashes(String s) {
        StringBuffer dashes = new StringBuffer(s.length());
        for (int count = 0; count < s.length(); count++)
            dashes.append('-');
        return dashes;
    }
}