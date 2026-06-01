import java.util.Scanner;
import java.util.HashMap;

public class Main {
    private static final int MOD = 1000000007;

    public static int count_divisible_subarrays(int n, int k, int[] arr) {
        HashMap<Long, Long> freq = new HashMap<>();

        long prefixSum = 0;
        long ans = 0;

        freq.put(0L, 1L);

        for (int i = 0; i < n; i++) {
            prefixSum += arr[i];

            long rem = ((prefixSum % k) + k) % k;

            long count = freq.getOrDefault(rem, 0L);
            ans = (ans + count) % MOD;

            freq.put(rem, count + 1);
        }

        return (int) (ans % MOD);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = scanner.nextInt();
        }

        scanner.close();

        int result = count_divisible_subarrays(n, k, arr);
        System.out.println(result);
    }
}
