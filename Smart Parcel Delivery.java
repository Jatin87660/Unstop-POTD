import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int x, y;
        long cost;

        Node(int x, int y, long cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    public static long min_delivery_energy(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long[][] dist = new long[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[0][0] = grid[0][0];
        pq.offer(new Node(0, 0, grid[0][0]));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost != dist[cur.x][cur.y]) {
                continue;
            }

            if (cur.x == n - 1 && cur.y == m - 1) {
                return cur.cost;
            }

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (grid[nx][ny] == -1) {
                    continue;
                }

                long newCost = cur.cost + grid[nx][ny];

                if (newCost < dist[nx][ny]) {
                    dist[nx][ny] = newCost;
                    pq.offer(new Node(nx, ny, newCost));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        System.out.println(min_delivery_energy(grid));
    }
}
