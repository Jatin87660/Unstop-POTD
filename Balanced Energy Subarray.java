import java.util.*;

public class Main {
    public static int countBalancedSubarrays(int[] arr, int k) {
        int n = arr.length;
        int half = k / 2;
        int count = 0;

        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }

        for (int i = 0; i <= n - k; i++) {

            long leftSum = prefix[i + half] - prefix[i];
            long rightSum;

            if (k % 2 == 0) {
                // Even k
                rightSum = prefix[i + k] - prefix[i + half];
            } else {
                // Odd k -> ignore middle element
                rightSum = prefix[i + k] - prefix[i + half + 1];
            }

            if (leftSum == rightSum) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int result = countBalancedSubarrays(arr, k);
        System.out.println(result);

        scanner.close();
    }
}
