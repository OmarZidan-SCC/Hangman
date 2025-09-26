import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Array of words to guess
        String[] words = {"java", "code", "keyboard", "hangman"};

        // Variable to control the game looping
        boolean playAgain = true;

        while (playAgain) {
            // Randomly pick a word
            int index = (int)(Math.random() * words.length);
            String word = words[index];

            // Create hidden word with asterisks
            String hidden = "";
            for (int i = 0; i < word.length(); i++) {
                hidden += "*";
            }

            int misses = 0;

            while (hidden.contains("*")) {
                System.out.print("(Guess) Enter a letter in word " + hidden + " > ");
                String guess = input.next().toLowerCase();

                // Check if guess is already revealed
                if (hidden.contains(guess)) {
                    System.out.println(guess + " is already in the word");
                    continue;
                }

                boolean correct = false;
                String newHidden = "";

                // Loop through each letter in word
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess.charAt(0)) {
                        newHidden += guess.charAt(0); // reveal correct letter
                        correct = true;
                    } else {
                        newHidden += hidden.charAt(i); // keep hidden letters
                    }
                }

                hidden = newHidden;

                if (!correct) {
                    System.out.println(guess + " is not in the word");
                    misses++;
                }
            }

            System.out.println("The word is " + word + ". You missed " + misses + " time(s).");

            // Ask if user wants to play again
            System.out.print("Do you want to guess another word? Enter Y or N: ");
            String answer = input.next();
            if (answer.equals("Y")) {
                playAgain = true;
            } else {
                playAgain = false;
                System.out.println("Thank you for playing");
            }
        }

    }
}