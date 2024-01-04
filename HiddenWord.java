/*
 * A simple word guessing game to be played in the console.
 * 
 * When run, this program initiates a game for the user to play in which
 * they must guess a word of the given length. Any '+' means that a letter is
 * in the word but in a different spot.
 * 
 * Author: Alvaro Deras
 * Date: January 3, 2024
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HiddenWord {
    private String word;

    public HiddenWord(String word1) {
        word = word1;
    }

    public String getHint(String guess) {
        String hint = "";
        for (int i = 0; i < guess.length(); i++) {
            if (guess.substring(i, i + 1).equals(word.substring(i, i + 1))) {
                hint += guess.substring(i, i + 1);
            }
            else if (word.indexOf(guess.substring(i, i + 1)) != -1) {
                hint += "+";
            }
            else {
                hint += "*";
            }
        }
        return hint;
    }

    public static String getRandomWord() {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    public static void main(String[] args) {
        String word = getRandomWord();
        HiddenWord puzzle = new HiddenWord(word);
        System.out.println("A '+' indicates a letter is in the word.");
        System.out.println("Your word is " + word.length() + " letters long");

        Scanner input = new Scanner(System.in);
        boolean flag = true;
        int counter = 0;
        while (flag) {
            System.out.println("Enter a guess: ");
            String userGuess = input.nextLine();

            if (!userGuess.equalsIgnoreCase(word)) {
                System.out.println("Hint: " + puzzle.getHint(userGuess));
                counter += 1;
            }     
            else {
                System.out.println("You were right! YAY!");
                flag = false;
            }
        }

    }
}
