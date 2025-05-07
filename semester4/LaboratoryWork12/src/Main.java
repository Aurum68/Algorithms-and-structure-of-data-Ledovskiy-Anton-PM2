import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int[] weights = {3, 4, 5, 8, 9};
    public static int[] prices = {1, 6, 4, 6, 7};
    public static int w = 13;
    public static int n = weights.length;
    public static int[][] a = new int[n][w];

    public static List<Integer> answer = new ArrayList<>();

    public static void findAnswer(int k, int s) {
        if (a[k][s] == 0) return;

        if (a[k - 1][s] == a[k][s]) findAnswer(k - 1, s);
        else {
            findAnswer(k - 1, s - weights[k]);
            answer.add(k + 1);
        }
    }

    public static void main(String[] args) {


        for (int i = 0; i < n; i++) {a[i][0] = 0;}
        for (int i = 0; i < w; i++) {a[0][i] = 0;}

        for (int k = 1; k < n; k++) {
            for (int s = 0; s < w; s++) {
                if (s >= weights[k]) a[k][s] = Math.max(a[k - 1][s], a[k - 1][s - weights[k]] + prices[k]);
                else a[k][s] = a[k - 1][s];
            }
        }

        findAnswer(n - 1, w - 1);
        System.out.println(answer);
    }
}