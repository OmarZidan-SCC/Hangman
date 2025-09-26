import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class HangmanMultiple {

    public static String[] readWordsFromFile(String filename) {
        ArrayList<String> wordsList = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.equals("")) {
                    wordsList.add(line);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Problem reading file: " + filename);
        }
        return wordsList.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] words = readWordsFromFile("words/programming.txt");

        boolean playAgain = true;

        while (playAgain) {
            System.out.println("Select a topic:");
            System.out.println("1: Programming");
            System.out.println("2: Fruits");
            System.out.println("3: Animals");
            System.out.print("Enter 1, 2, or 3: ");

            int choice = input.nextInt();
            String[] words;

            if (choice == 1) {
                words = topic1;
            } else if (choice == 2) {
                words = topic2;
            } else if (choice == 3) {
                words = topic3;
            } else {
                System.out.println("Invalid choice, defaulting to Programming");
                words = topic1;
            }

            // Randomly pick a word
            int index = (int) (Math.random() * words.length);
            String word = words[index];

            // Hidden word
            String hidden = "";
            for (int i = 0; i < word.length(); i++) {
                hidden += "*";
            }

            int misses = 0;

            while (hidden.contains("*")) {
                System.out.print("(Guess) Enter a letter in word " + hidden + " > ");
                String guess = input.next().toLowerCase();

                if (hidden.contains(guess)) {
                    System.out.println(guess + " is already in the word");
                    continue;
                }

                boolean correct = false;
                String newHidden = "";
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess.charAt(0)) {
                        newHidden += guess.charAt(0);
                        correct = true;
                    } else {
                        newHidden += hidden.charAt(i);
                    }
                }
                hidden = newHidden;

                if (!correct) {
                    System.out.println(guess + " is not in the word");
                    misses++;
                }
            }

            System.out.println("The word is " + word + ". You missed " + misses + " time(s).");

            System.out.print("Do you want to guess another word? Enter Y or N: ");
            String answer = input.next();
            if (!answer.equalsIgnoreCase("Y")) {
                playAgain = false;
                System.out.println("Thanks for playing!");
            }
        }
    }
}
