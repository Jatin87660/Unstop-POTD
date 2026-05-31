import java.util.*;

public class Main {

    static class Edge {
        int to;
        int cost;
        int bm;
        int flipMask;

        Edge(int to, int cost, int bm, int flipMask) {
            this.to = to;
            this.cost = cost;
            this.bm = bm;
            this.flipMask = flipMask;
        }
    }

    static class State implements Comparable<State> {
        int node;
        int mask;
        long dist;

        State(int node, int mask, long dist) {
            this.node = node;
            this.mask = mask;
            this.dist = dist;
        }

        @Override
        public int compareTo(State other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static int starlight_jumps(int N, int M, int K, int[] bits, int[][] edges) {

        int startMask = 0;
        for (int bit : bits) {
            startMask |= (1 << bit);
        }

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int cost = edges[i][2];
            int bm = edges[i][3];
            int flipMask = edges[i][4];

            graph.get(u).add(new Edge(v, cost, bm, flipMask));
        }

        final int MASKS = 1024;
        final long INF = Long.MAX_VALUE / 4;

        long[][] dist = new long[N + 1][MASKS];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();

        dist[1][startMask] = 0;
        pq.offer(new State(1, startMask, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.dist != dist[cur.node][cur.mask]) {
                continue;
            }

            for (Edge e : graph.get(cur.node)) {

                if ((cur.mask & e.bm) != e.bm) {
                    continue;
                }

                int nextMask = cur.mask ^ e.flipMask;
                long newDist = cur.dist + e.cost;

                if (newDist < dist[e.to][nextMask]) {
                    dist[e.to][nextMask] = newDist;
                    pq.offer(new State(e.to, nextMask, newDist));
                }
            }
        }

        return dist[N][startMask] == INF ? -1 : (int) dist[N][startMask];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();

        int[] bits = new int[K];
        for (int i = 0; i < K; i++) {
            bits[i] = scanner.nextInt();
        }

        int[][] edges = new int[M][5];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 5; j++) {
                edges[i][j] = scanner.nextInt();
            }
        }

        System.out.println(starlight_jumps(N, M, K, bits, edges));

        scanner.close();
    }
}
