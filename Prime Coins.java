import java.util.*;

public class Main {

    static final long MOD = 1000000007L;

    
    public static int countPrimes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) count++;
        }

        return count;
    }

    
    public static long factorial(int n) {
        long result = 1;

        for (int i = 2; i <= n; i++) {
            result = (result * i) % MOD;
        }

        return result;
    }

    public static int numPrimeArrangements(int n) {

        int primeCount = countPrimes(n);

        long ans = (factorial(primeCount) *
                   factorial(n - primeCount)) % MOD;

        return (int) ans;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(numPrimeArrangements(n));
    }
}
