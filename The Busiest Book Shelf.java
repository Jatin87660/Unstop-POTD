import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        HashMap<Long, Integer> freq = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long id = sc.nextLong();
            freq.put(id, freq.getOrDefault(id, 0) + 1);
        }

        long answer = Long.MAX_VALUE;
        int maxFreq = 0;

        for (Map.Entry<Long, Integer> entry : freq.entrySet()) {
            long id = entry.getKey();
            int count = entry.getValue();

            if (count > maxFreq) {
                maxFreq = count;
                answer = id;
            } else if (count == maxFreq && id < answer) {
                answer = id;
            }
        }

        System.out.println(answer);

        sc.close();
    }
}
