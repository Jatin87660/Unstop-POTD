import java.util.*;

public class Main {

    static class SegmentTree {
        int[][] tree;
        int[] lazy;
        int n;

        SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n][8];
            lazy = new int[4 * n];
            build(1, 0, n - 1, arr);
        }

        void build(int node, int l, int r, int[] arr) {
            if (l == r) {
                tree[node][arr[l]] = 1;
                return;
            }
            int mid = (l + r) / 2;
            build(node * 2, l, mid, arr);
            build(node * 2 + 1, mid + 1, r, arr);
            pull(node);
        }

        void pull(int node) {
            for (int i = 0; i < 8; i++)
                tree[node][i] = tree[node * 2][i] + tree[node * 2 + 1][i];
        }

        void apply(int node, int mask) {
            int[] temp = new int[8];
            for (int i = 0; i < 8; i++)
                temp[i ^ mask] = tree[node][i];
            tree[node] = temp;
            lazy[node] ^= mask;
        }

        void push(int node) {
            if (lazy[node] != 0) {
                apply(node * 2, lazy[node]);
                apply(node * 2 + 1, lazy[node]);
                lazy[node] = 0;
            }
        }

        void update(int node, int l, int r, int ql, int qr, int mask) {
            if (ql <= l && r <= qr) {
                apply(node, mask);
                return;
            }
            push(node);
            int mid = (l + r) / 2;
            if (ql <= mid)
                update(node * 2, l, mid, ql, qr, mask);
            if (qr > mid)
                update(node * 2 + 1, mid + 1, r, ql, qr, mask);
            pull(node);
        }

        int[] query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr)
                return tree[node].clone();

            push(node);
            int mid = (l + r) / 2;

            if (qr <= mid)
                return query(node * 2, l, mid, ql, qr);
            if (ql > mid)
                return query(node * 2 + 1, mid + 1, r, ql, qr);

            int[] left = query(node * 2, l, mid, ql, qr);
            int[] right = query(node * 2 + 1, mid + 1, r, ql, qr);

            int[] res = new int[8];
            for (int i = 0; i < 8; i++)
                res[i] = left[i] + right[i];
            return res;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        SegmentTree st = new SegmentTree(arr);

        StringBuilder out = new StringBuilder();

        while (q-- > 0) {
            int type = sc.nextInt();

            if (type == 1) {
                int l = sc.nextInt() - 1;
                int r = sc.nextInt() - 1;
                int mask = sc.nextInt();
                st.update(1, 0, n - 1, l, r, mask);
            } else {
                int l = sc.nextInt() - 1;
                int r = sc.nextInt() - 1;
                int[] freq = st.query(1, 0, n - 1, l, r);
                int ans = 0;
                for (int x : freq)
                    ans = Math.max(ans, x);
                out.append(ans).append('\n');
            }
        }

        System.out.print(out);
    }
}
