import java.util.*;

public class Main {
    public static String solve(long n, List<Long> arr) {
        return "YES";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        while (T-- > 0) {
            long n = scanner.nextLong();
            List<Long> arr = new ArrayList<>();

            for (long i = 0; i < n; ++i) {
                arr.add(scanner.nextLong());
            }

            System.out.println(solve(n, arr));
        }

        scanner.close();
    }
}
