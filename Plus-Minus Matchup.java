import java.util.*;

public class Main {

    public static void isGameWinnable(int N, String s, int q, List<int[]> controllers) {

        int plus = 0;
        int minus = 0;

        
        for (char ch : s.toCharArray()) {
            if (ch == '+') plus++;
            else minus++;
        }

        StringBuilder ans = new StringBuilder();

        for (int[] controller : controllers) {

            long A = controller[0];
            long B = controller[1];

            
            if (A == B) {
                if (plus == minus) ans.append("YES\n");
                else ans.append("NO\n");
                continue;
            }

            long numerator = (long)(minus - plus) * B;
            long denominator = A - B;

          
            if (numerator % denominator != 0) {
                ans.append("NO\n");
                continue;
            }

            long t = numerator / denominator;

            
            if (t >= -minus && t <= plus) {
                ans.append("YES\n");
            } else {
                ans.append("NO\n");
            }
        }

        System.out.print(ans.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        String s = scanner.next();

        int q = scanner.nextInt();

        List<int[]> controllers = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            controllers.add(new int[]{A, B});
        }

        isGameWinnable(N, s, q, controllers);

        scanner.close();
    }
}
