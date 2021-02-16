
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Game {

    public static void Input(String[] Movies) throws Exception {
        File file = new File("Movies.txt");
        try (Scanner scanner = new Scanner(file);) {
            int l = 0;
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                boolean start = false;
                String Name = "";
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '%') {
                        start = true;
                        i++;
                        continue;
                    }
                    if (start) {
                        Name += s.charAt(i);
                    }
                    if (s.charAt(i) == ' ' && 
                        i < s.length() - 1 && s.charAt(i + 1) == '(') {
                        start = false;
                    }
                }
                Movies[l++] = Name;
            }
        }
    }

    public static void main(String args[]) throws IOException {
        String[] Movies = new String[100];
        try {
            Input(Movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(System.in);
        while (wishToContinue(scan)) {
            Random rand = new Random();
            int index = rand.nextInt(100);
            System.out.println("The game starts:");
            HiddenWord word = new HiddenWord(Movies[index]);
            System.out.println("The movie title have " + word.getLetters() + " letters");
            int lives = 10;
            while (lives > 0 && !word.isFound()) {
                System.out.println("You have " + lives + " live(s) remain. Enter you letter:");
                char ch = correctCharacter(scan);
                boolean have = word.find(ch);
                if (!have) {
                    lives--;
                    System.out.println("No letter \"" + ch + "\" in the movie");
                }
                System.out.print("You guessed so far: ");
                System.out.println(word.getGuessed());
            }
            if (word.isFound()) {
                System.out.println("Congratulations you WON THE GAME");
            } else {
                System.out.println("YOU LOSE");
                System.out.println("The movie name was: " + word.getHiddenWord());
            }
       }

    }

    private static boolean wishToContinue(Scanner scan) {
        System.out.println("If you want to finish type \"q\" or other character to play:");
        return (correctCharacter(scan) == 'q' ? false : true);
    }

    private static char correctCharacter(Scanner scan) {
        char ch = scan.next().toLowerCase().charAt(0);
        while (!(ch >= 'a' && ch <= 'z')) {
            System.out.println("Not a letter. Enter new letter: ");
            ch = scan.next().toLowerCase().charAt(0);
        }
        return ch;
    }
}
