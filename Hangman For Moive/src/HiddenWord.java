public class HiddenWord {
    char[] Guessed;
    String HiddenWord;
    boolean found;
    int letters;
    HiddenWord(String word) {
        letters = word.length();
        Guessed = word.toLowerCase().toCharArray();
        boolean found = false;
        HiddenWord = word;
        for (int i = 0; i < letters; i++) {
            if ((Guessed[i] >= 'a' && Guessed[i] <= 'z')) {
                Guessed[i] = '_';
            }
        }
    }
    boolean find(char x) {
        boolean have = false;
        boolean wonornot = true;
        for (int i = 0; i < letters; i++) {
            if (HiddenWord.toLowerCase().charAt(i) == x) {
                Guessed[i] = HiddenWord.charAt(i);
                have = true;
            }
            if (Guessed[i] == '_') {
                wonornot = false;
            }
        }
        if (wonornot) found = true;
        return have;
    }
}
