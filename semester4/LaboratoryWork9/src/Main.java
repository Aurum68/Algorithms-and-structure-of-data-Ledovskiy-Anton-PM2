import java.util.Arrays;

public class Main {
    public static final int INF = Integer.MAX_VALUE;

    public static int tsp(int[][] dist, int start) {
        int length = dist.length;
        int numStates = 1 << length;
        int[][] dp = new int[numStates][length];

        for (int[] row : dp) {Arrays.fill(row, INF);}

        dp[1 << start][start] = 0;

        for (int mask = 0; mask < numStates; mask++) {
            for (int v = 0; v < length; v++) {
                if (dp[mask][v] == INF) continue;

                for (int u = 0; u < length; u++) {
                    if ((mask & (1 << u)) != 0) continue;

                    int nextMask = mask | (1 << u);
                    int nextDist = dp[mask][v] + dist[v][u];
                    dp[nextMask][u] = Math.min(dp[nextMask][u], nextDist);
                }
            }
        }

        int fullMask = (1 << length) - 1;
        int minCost = INF;
        for (int v = 0; v < length; v++) {
            int cost = dp[fullMask][v] + dist[v][start];
            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    public static void main(String[] args) {
        int[][] dist = {
                {0, 10, 25, 25, 10},
                {1, 0, 10, 15, 2},
                {8, 9, 0, 20, 10},
                {14, 10, 24, 0, 15},
                {10, 8, 25, 27, 0}
        };
        System.out.println(tsp(dist, 0));
    }
}