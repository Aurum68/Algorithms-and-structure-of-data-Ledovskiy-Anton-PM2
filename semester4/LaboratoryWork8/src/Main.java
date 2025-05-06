//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static int countWays(int[] coins, int amount) {
        if (amount == 0) return 1;

        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 20;
        System.out.println(countWays(coins, amount));
    }
}