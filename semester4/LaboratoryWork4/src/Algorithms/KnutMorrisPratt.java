package Algorithms;

import java.util.ArrayList;
import java.util.List;

public class KnutMorrisPratt {

    public static List<Integer> search(String text, String pattern) {
        List<Integer> res = new ArrayList<>();

        int[] pi = buildPrefixFunction(pattern);
        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {j = pi[j - 1];}

            if (text.charAt(i) == pattern.charAt(j)) j++;
            if (j == pattern.length()) {
                res.add(i - pattern.length() + 1);
                j = pi[j - 1];
            }
        }
        return res;
    }

    private static int[] buildPrefixFunction(String pattern) {
        int[] pi = new int[pattern.length()];
        int j = 0;

        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {j = pi[j - 1];}

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                pi[i] = j;
            }
        }
        return pi;
    }
}
