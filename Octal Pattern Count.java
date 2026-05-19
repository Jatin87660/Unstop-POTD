import java.util.Scanner;

public class Main {
    static final long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.close();

        String result = userLogic(N);
        System.out.println(result);
    }

    public static String userLogic(int N) {

        if (N <= 0) {
            return "1";
        }

        // For length 1
        long a = 1, e = 1, i = 1, o = 1, u = 1;

        for (int len = 2; len <= N; len++) {

            long newA = (e + u) % MOD;
            long newE = (a + i) % MOD;
            long newI = (e + o) % MOD;
            long newO = (i + u) % MOD;
            long newU = (a + o) % MOD;

            a = newA;
            e = newE;
            i = newI;
            o = newO;
            u = newU;
        }

        long total = (a + e + i + o + u) % MOD;

        if (total == 0) {
            return "1";
        }

       
        return Long.toOctalString(total);
    }
}
