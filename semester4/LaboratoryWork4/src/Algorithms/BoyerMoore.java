package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoyerMoore {

    public static List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();

        int[] badCharTable = buildBadCharTable(pattern);
        char[] coincidences = new char[pattern.length()];

        int index = 0;
        while (index < text.length() - pattern.length()) {
            int j = pattern.length() - 1;

            while (j >= 0 && text.charAt(index + j) == pattern.charAt(j)) {
                coincidences[j] = pattern.charAt(j);
                j--;
            }

            if (j < 0){
                result.add(index);
                index += badCharTable[coincidences[pattern.length() - 1]];
                continue;
            }

            if (j == pattern.length() - 1) {
                index += badCharTable[text.charAt(index + pattern.length() - 1)];
                continue;
            }

            index += badCharTable[coincidences[pattern.length() - 1]];
        }
        return result;
    }

    private static int[] buildBadCharTable(String pattern) {
        int[] table = new int[256];
        Arrays.fill(table, pattern.length());

        for (int i = 1; i < pattern.length(); i++) {
            table[pattern.charAt(i - 1)] = pattern.length() - i;
        }

        return table;
    }
}
