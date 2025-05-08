import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] S = {4, 8, 1, 4, 2, 1};
        int V = 10;
        int minBoxes = minNumberOfBoxes(S, V);
        System.out.println("Минимальное число ящиков: " + minBoxes);
    }

    public static int minNumberOfBoxes(int[] s, int v){
        int n = s.length;
        int maxState = 1 << n;
        int[] dp = new int[maxState];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 1;

        int[] subsetSum = new int[maxState];

        for(int mask = 0; mask < maxState; mask++){
            if (dp[mask] == Integer.MAX_VALUE) continue;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0){
                    int newMask = mask | (1 << i);
                    int newSum = subsetSum[mask] + s[i];

                    if (newSum <= v){
                        if (dp[mask] < dp[newMask]){
                            dp[newMask] = dp[mask];
                            subsetSum[newMask] = newSum;
                            continue;
                        }
                    }

                    if (dp[mask] + 1 < dp[newMask]){
                        dp[newMask] = dp[mask] + 1;
                        subsetSum[newMask] = s[i];
                    }
                }
            }
        }
        return dp[maxState-1];
    }
}