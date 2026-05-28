import java.util.*;

public class Main {

    public static int max_remaining_sum(int N, int[] A, int[][] T) {

        int remainingSum = 0;

        for (int j = 0; j < N; j++) {

            boolean removable = false;

            for (int i = 0; i < j; i++) {
                if (A[i] > A[j]) {
                    removable = true;
                    break;
                }
            }

            // Keep only non-removable elements
            if (!removable) {
                remainingSum += A[j];
            }
        }

        return remainingSum;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        int[][] T = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                T[i][j] = scanner.nextInt();
            }
        }

        int result = max_remaining_sum(N, A, T);

        System.out.println(result);
    }
}
