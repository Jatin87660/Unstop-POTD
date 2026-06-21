import java.util.*;

public class Main {

    private static boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    public static long talentBurst(int[] arr) {
        int n = arr.length;

        int[] val = new int[n + 2];
        val[0] = 1;
        val[n + 1] = 1;

        long[] bonus = new long[n + 2];

        for (int i = 0; i < n; i++) {
            val[i + 1] = arr[i];
            if (isPrime(arr[i])) {
                bonus[i + 1] = arr[i];
            }
        }

        long[][] dp = new long[n + 2][n + 2];

        for (int len = 2; len < n + 2; len++) {
            for (int left = 0; left + len < n + 2; left++) {
                int right = left + len;

                for (int k = left + 1; k < right; k++) {
                    long gain =
                            (long) (val[left] + 2) * val[k] * (val[right] + 2)
                            + bonus[k];

                    dp[left][right] = Math.max(
                            dp[left][right],
                            dp[left][k] + dp[k][right] + gain
                    );
                }
            }
        }

        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(talentBurst(arr));
    }
}
