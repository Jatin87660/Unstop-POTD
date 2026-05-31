import java.util.*;

public class Main {

    public static void chronoLockedUpgrade(int n, int m, int[] chronoLock, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n];

        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;

            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();

        long[] parentMax = new long[n];

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int visited = 0;
        long answer = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            visited++;

            long currentTime = Math.max(parentMax[u] + 1L,
                                        (long) chronoLock[u]);

            answer = Math.max(answer, currentTime);

            for (int v : graph.get(u)) {
                parentMax[v] = Math.max(parentMax[v], currentTime);

                if (--indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        if (visited != n) {
            System.out.println("CYCLE DETECTED");
        } else {
            System.out.println(answer);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] chronoLock = new int[n];
        for (int i = 0; i < n; i++) {
            chronoLock[i] = sc.nextInt();
        }

        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }

        chronoLockedUpgrade(n, m, chronoLock, edges);
    }
}
