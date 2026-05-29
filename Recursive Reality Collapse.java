import java.util.*;

class Main {

    public static int maxCollapsingRealities(int N, int M, int K,
                                             List<Integer> unstableRealities,
                                             List<int[]> dependencies) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[N];

        for (int[] edge : dependencies) {
            int A = edge[0];
            int B = edge[1];

            graph.get(B).add(A);
            indegree[A]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        boolean[] unstable = new boolean[N];

        for (int x : unstableRealities) {
            unstable[x] = true;
        }

        for (int x : unstableRealities) {
            if (indegree[x] == 0) {
                queue.offer(x);
            }
        }

        boolean[] collapsed = new boolean[N];
        int count = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (collapsed[curr]) continue;

            collapsed[curr] = true;
            count++;

            for (int next : graph.get(curr)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int K = scanner.nextInt();

        List<Integer> unstableRealities = new ArrayList<>();
        for (int i = 0; i < K; ++i) {
            unstableRealities.add(scanner.nextInt());
        }

        List<int[]> dependencies = new ArrayList<>();
        for (int i = 0; i < M; ++i) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            dependencies.add(new int[]{A, B});
        }

        int result = maxCollapsingRealities(N, M, K, unstableRealities, dependencies);
        System.out.println(result);

        scanner.close();
    }
}
