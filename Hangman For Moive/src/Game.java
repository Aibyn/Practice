
import java.util.Scanner;
import java.io.File;

public class Game {

    public static void Input(String[] Movies) throws Exception {
        File file = new File("Movies.txt");
        Scanner scanner = new Scanner(file);
        int l = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            boolean start = false;
            String Name = "";
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ' && s.charAt(i + 1) == '(') {
                    start = false;
                }
                if (start) {
                    Name += s.charAt(i);
                }
                if (s.charAt(i) == '%') {
                    start = true;
                    i++;
                }
            }
            Movies[l++] = Name;
        }
    }

    public static void main(String args[]) {
        String[] Movies = new String[100];
        try {
            Input(Movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        while (true) {
            int index = (int) (Math.random() * 100);
            System.out.println("The game starts:");
            HiddenWord word = new HiddenWord(Movies[index]);
            System.out.println("The movie title have " + word.letters + " letters");
            int lives = 10;
            Scanner scan = new Scanner(System.in);
            while (lives > 0) {
                System.out.println("You have " + lives + " live(s) remain. Enter you letter:");
                char ch = scan.next().toLowerCase().charAt(0);
                while (!(ch >= 'a' && ch <= 'z')) {
                    System.out.println("Not a letter. Enter new letter: ");
                    ch = scan.next().toLowerCase().charAt(0);
                }
                boolean have = word.find(ch);
                if (word.found) {
                    break;
                }
                if (!have) {
                    lives--;
                    System.out.println("No letter \"" + ch + "\" in the movie");
                }
                System.out.print("You guessed so far: ");
                System.out.println(word.Guessed);
            }
            if (word.found) {
                System.out.println("Congratulations you WON THE GAME");
            } else {
                System.out.println("YOU LOSED");
                System.out.println("The Movie name was: " + word.HiddenWord);
            }
//        }

    }
}
