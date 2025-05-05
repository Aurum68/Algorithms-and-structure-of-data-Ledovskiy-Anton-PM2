package Algorithms;

import java.util.ArrayList;
import java.util.List;

public class Rabin {
    private static final int BASE = 256;
    private static final int MOD = 251;

    public static List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();

        int patternLen = pattern.length();
        int textLen = text.length();

        int patternHash = 0;
        int windowHash = 0;
        int h = 1;

        for (int i = 0; i < patternLen - 1; i++) {
            h = (h * BASE) % MOD;
        }

        for (int i = 0; i < patternLen; i++) {
            patternHash = (BASE * patternHash + pattern.charAt(i)) % MOD;
            windowHash = (BASE * windowHash + text.charAt(i)) % MOD;
        }

        for (int i = 0; i < textLen - patternLen; i++) {
            if (patternHash == windowHash) {
                boolean match = true;
                for (int j = 0; j < patternLen; j++) {
                    if (pattern.charAt(j) != text.charAt(i + j)) {
                        match = false;
                        break;
                    }
                }
                if (match) result.add(i);
            }

            if (i < textLen - patternLen) {
                windowHash = (BASE * (windowHash - text.charAt(i) * h) + text.charAt(i + patternLen)) % MOD;
                if (windowHash < 0) windowHash += MOD;
            }
        }

        return result;
    }
}
