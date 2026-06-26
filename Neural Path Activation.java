import java.io.*;
import java.util.*;

public class Main {

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    static int N;
    static ArrayList<Integer>[] tree;

    static int[] parent, depth, heavy, head, pos, size;
    static int curPos = 0;

    static int[] seg;
    static boolean[] lazy;

    static void dfs(int v, int p) {
        parent[v] = p;
        size[v] = 1;
        heavy[v] = -1;

        int max = 0;
        for (int u : tree[v]) {
            if (u == p) continue;
            depth[u] = depth[v] + 1;
            dfs(u, v);
            size[v] += size[u];
            if (size[u] > max) {
                max = size[u];
                heavy[v] = u;
            }
        }
    }

    static void decompose(int v, int h) {
        head[v] = h;
        pos[v] = curPos++;

        if (heavy[v] != -1)
            decompose(heavy[v], h);

        for (int u : tree[v]) {
            if (u != parent[v] && u != heavy[v]) {
                decompose(u, u);
            }
        }
    }

    static void apply(int node, int l, int r) {
        seg[node] = (r - l + 1) - seg[node];
        lazy[node] ^= true;
    }

    static void push(int node, int l, int r) {
        if (!lazy[node] || l == r) return;

        int mid = (l + r) / 2;

        apply(node * 2, l, mid);
        apply(node * 2 + 1, mid + 1, r);

        lazy[node] = false;
    }

    static void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            apply(node, l, r);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;
        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        seg[node] = seg[node * 2] + seg[node * 2 + 1];
    }

    static int query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;

        if (ql <= l && r <= qr)
            return seg[node];

        push(node, l, r);

        int mid = (l + r) / 2;

        return query(node * 2, l, mid, ql, qr)
                + query(node * 2 + 1, mid + 1, r, ql, qr);
    }

    static void updatePath(int a, int b) {
        while (head[a] != head[b]) {
            if (depth[head[a]] < depth[head[b]]) {
                int t = a;
                a = b;
                b = t;
            }

            update(1, 0, N - 1, pos[head[a]], pos[a]);
            a = parent[head[a]];
        }

        if (depth[a] > depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        // Exclude LCA node (edges only)
        if (a != b)
            update(1, 0, N - 1, pos[a] + 1, pos[b]);
    }

    static int queryPath(int a, int b) {
        int ans = 0;

        while (head[a] != head[b]) {
            if (depth[head[a]] < depth[head[b]]) {
                int t = a;
                a = b;
                b = t;
            }

            ans += query(1, 0, N - 1, pos[head[a]], pos[a]);
            a = parent[head[a]];
        }

        if (depth[a] > depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        // Exclude LCA node (edges only)
        if (a != b)
            ans += query(1, 0, N - 1, pos[a] + 1, pos[b]);

        return ans;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner();

        N = fs.nextInt();

        tree = (ArrayList<Integer>[]) new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            tree[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }

        parent = new int[N + 1];
        depth = new int[N + 1];
        heavy = new int[N + 1];
        head = new int[N + 1];
        pos = new int[N + 1];
        size = new int[N + 1];

        dfs(1, 0);
        decompose(1, 1);

        seg = new int[4 * N];
        lazy = new boolean[4 * N];

        int Q = fs.nextInt();

        StringBuilder sb = new StringBuilder();

        while (Q-- > 0) {
            int type = fs.nextInt();
            int u = fs.nextInt();
            int v = fs.nextInt();

            if (type == 1) {
                updatePath(u, v);
            } else {
                sb.append(queryPath(u, v)).append('\n');
            }
        }

        System.out.print(sb);
    }
}
