import java.util.*;

public class Main {

    public static void computeMaxIntensityAfterKHours(
            int N,
            int[] intensities,
            int K,
            List<int[]> queries) {

        int[] current = Arrays.copyOf(intensities, N);

        for (int hour = 0; hour < K; hour++) {

            int[] next = new int[N];

            if (N == 1) {
                next[0] = current[0];
            } else {
                next[0] = current[1];

                for (int i = 1; i < N - 1; i++) {
                    next[i] = (current[i - 1] + current[i + 1]) / 2;
                }

                next[N - 1] = current[N - 2];
            }

            current = next;
        }

        StringBuilder sb = new StringBuilder();

        for (int[] q : queries) {

            int l = q[0] - 1;
            int r = q[1] - 1;

            int maxVal = Integer.MIN_VALUE;

            for (int i = l; i <= r; i++) {
                maxVal = Math.max(maxVal, current[i]);
            }

            sb.append(maxVal).append("\n");
        }

        System.out.print(sb.toString());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] intensities = new int[N];

        for (int i = 0; i < N; i++) {
            intensities[i] = sc.nextInt();
        }

        int K = sc.nextInt();

        int Q = sc.nextInt();

        List<int[]> queries = new ArrayList<>();

        for (int i = 0; i < Q; i++) {

            int l = sc.nextInt();
            int r = sc.nextInt();

            queries.add(new int[]{l, r});
        }

        computeMaxIntensityAfterKHours(N, intensities, K, queries);

        sc.close();
    }
}
