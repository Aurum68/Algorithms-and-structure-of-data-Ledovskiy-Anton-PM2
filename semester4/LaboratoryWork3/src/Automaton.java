import java.util.ArrayList;
import java.util.List;

public class Automaton {
    private final static int ALPHABET_SIZE = 256;

    private final String pattern;
    private final int patternLength;
    private int[][] matrix;

    public Automaton(String pattern) {
        this.pattern = pattern;
        this.patternLength = pattern.length();
        buildMatrix();
    }

    private void buildMatrix() {
        this.matrix = new int[patternLength + 1][ALPHABET_SIZE];
        for (int stage = 0; stage <= patternLength; stage++) {
            for (int c = 97; c < ALPHABET_SIZE; c++) {
                char letter = (char) c;
                matrix[stage][c] = getNextStage(stage, letter);
            }
        }
    }

    private int getNextStage(int stage, char letter) {
        if (stage < patternLength && this.pattern.charAt(stage) == letter) return ++stage;

        for (int ns = stage - 1; ns >= 0; ns--){
            if (this.pattern.charAt(ns) == letter) {
                boolean match = true;
                for (int i = 0; i < ns; i++) {
                    if (this.pattern.charAt(i) != this.pattern.charAt(stage - ns + i)) {
                        match = false;
                        break;
                    }
                }
                if (match) return ++ns;
            }
        }
        return 0;
    }

    public List<Integer> search(String text) {
        List<Integer> result = new ArrayList<>();
        int stage = 0;
        for (int i = 0; i < text.length(); i++) {
            stage = matrix[stage][text.charAt(i)];
            if (stage == patternLength) {
                result.add(i - patternLength + 1);
            }
        }
        return result;
    }
}
