import java.util.*;

public class Main {

    @SuppressWarnings("unchecked")
    public static int userLogic(int n, int s, int[] arr) {

        List<Integer>[] pos = new ArrayList[s + 1];

        for (int i = 1; i <= s; i++) {
            pos[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            pos[arr[i]].add(i + 1);
        }

        long[] dp = new long[s + 1];

        dp[0] = 0;

        for (int sum = 1; sum <= s; sum++) {

            long best = 0;

            for (int val = 1; val <= sum; val++) {

                long prevPos = dp[sum - val];

                long nextPos = getNextPosition(prevPos, pos[val], n);

                best = Math.max(best, nextPos);
            }

            dp[sum] = best;
        }

        return (int) dp[s];
    }

    private static long getNextPosition(long currentPos,
                                        List<Integer> positions,
                                        int n) {

        long cycle = currentPos / n;
        int rem = (int) (currentPos % n);

        int idx = upperBound(positions, rem);

        if (idx < positions.size()) {
            return cycle * n + positions.get(idx);
        }

        return (cycle + 1) * n + positions.get(0);
    }

    private static int upperBound(List<Integer> list, int target) {

        int l = 0;
        int r = list.size();

        while (l < r) {

            int mid = (l + r) / 2;

            if (list.get(mid) <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int s = scanner.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int result = userLogic(n, s, arr);

        System.out.println(result);

        scanner.close();
    }
}
