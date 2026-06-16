import java.util.*;

public class Main {
    public static int findLuckyStonePairs(int p, int n, int m, int[] stones) {
        int count = 0;

        for (int stone : stones) {
            if (stone % n == 0 || stone % m == 0) {
                count++;
            }
        }

        return count * (count - 1) / 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int p = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] stones = new int[p];
        for (int i = 0; i < p; i++) {
            stones[i] = sc.nextInt();
        }

        int result = findLuckyStonePairs(p, n, m, stones);
        System.out.println(result);

        sc.close();
    }
}
