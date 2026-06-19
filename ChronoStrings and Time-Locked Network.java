import java.io.*;
import java.util.*;

class Main {

    static class Pair {
        int node;
        long dist;

        Pair(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static long getDecodeCost(String s) {
        long cost = 0;
        for (int i = 0; i < s.length(); i++) {
            cost += (s.charAt(i) - 'a' + 1);
        }
        return cost;
    }

    public static void chronoShortestPath(int n, String[] S, List<int[]>[] adj, int start, int end) {

        long[] decode = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            decode[i] = getDecodeCost(S[i - 1]);
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        dist[start] = decode[start];
        pq.offer(new Pair(start, dist[start]));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (cur.dist != dist[cur.node]) {
                continue;
            }

            int u = cur.node;

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int w = edge[1];

                long newDist = dist[u] + w + decode[v];

                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer(new Pair(v, newDist));
                }
            }
        }

        System.out.println(dist[end] == Long.MAX_VALUE ? -1 : dist[end]);
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] S = new String[N];

        for (int i = 0; i < N; i++) {
            S[i] = br.readLine().trim();
        }

        List<int[]>[] adj = (ArrayList<int[]>[]) new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adj[u].add(new int[]{v, t});
            adj[v].add(new int[]{u, t});
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        chronoShortestPath(N, S, adj, start, end);
    }
}
