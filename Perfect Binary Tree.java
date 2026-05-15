import java.util.*;

public class Main {

    static long ans = 0;

    private static long dfs(int node, int maxNode, int[] w) {

        if (node * 2 > maxNode) {
            return 0;
        }

        long left = dfs(node * 2, maxNode, w) + w[node * 2];
        long right = dfs(node * 2 + 1, maxNode, w) + w[node * 2 + 1];

        ans += Math.abs(left - right);

        return Math.max(left, right);
    }

    public static int userLogic(int n, int[] weights) {

        int totalNodes = (1 << (n + 1)) - 1;

        int[] w = new int[totalNodes + 1];

        for (int i = 2; i <= totalNodes; i++) {
            w[i] = weights[i - 2];
        }

        ans = 0;

        dfs(1, totalNodes, w);

        return (int) ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] weights = new int[(1 << (n + 1)) - 2];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = sc.nextInt();
        }

        int result = userLogic(n, weights);

        System.out.println(result);
    }
}
