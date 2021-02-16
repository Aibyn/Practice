import java.util.Arrays;

public class HiddenWord {
    private char[] Guessed;
    private String HiddenWord;
    private boolean found;
    private int letters;
    HiddenWord(String word) {
        letters = word.length();
        Guessed = word.toLowerCase().toCharArray();
        found = false;
        HiddenWord = word;
        for (int i = 0; i < letters; i++) {
            if ((Guessed[i] >= 'a' && Guessed[i] <= 'z')) {
                Guessed[i] = '_';
            }
        }
    }
    boolean find(char x) {
        boolean have = false;
        boolean wonOrNot = true;
        for (int i = 0; i < letters; i++) {
            if (HiddenWord.toLowerCase().charAt(i) == x) {
                Guessed[i] = HiddenWord.charAt(i);
                have = true;
            }
            if (Guessed[i] == '_') {
                wonOrNot = false;
            }
        }
        if (wonOrNot) found = true;
        return have;
    }
    String getGuessed() {return String.valueOf(this.Guessed);}
    int getLetters() {return this.letters;}
    boolean isFound() {return this.found;}
	public String getHiddenWord() {return this.HiddenWord;}
}
