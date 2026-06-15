import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        long[] value = new long[n];
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            value[i] = Long.parseLong(st.nextToken());
            left[i] = Integer.parseInt(st.nextToken());
            right[i] = Integer.parseInt(st.nextToken());
        }

       
        List<Integer> order = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            order.add(node);

            if (left[node] != -1) stack.push(left[node]);
            if (right[node] != -1) stack.push(right[node]);
        }

        long[] subtreeMax = new long[n];
        int peakCount = 0;

        
        for (int i = order.size() - 1; i >= 0; i--) {
            int node = order.get(i);

            long maxDescendant = Long.MIN_VALUE;

            if (left[node] != -1) {
                maxDescendant = Math.max(maxDescendant, subtreeMax[left[node]]);
            }
            if (right[node] != -1) {
                maxDescendant = Math.max(maxDescendant, subtreeMax[right[node]]);
            }

            
            if (left[node] == -1 && right[node] == -1) {
                peakCount++;
            } else {
                if (value[node] > maxDescendant) {
                    peakCount++;
                }
            }

            subtreeMax[node] = Math.max(value[node], maxDescendant);
        }

        System.out.println(peakCount);
    }
}
