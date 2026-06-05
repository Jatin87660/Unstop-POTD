import java.util.Scanner;

public class Main {
    public static void decodeMessage(String S) {
        int i = S.length() - 1;
        StringBuilder res = new StringBuilder();

        while (i >= 0) {
            if (S.charAt(i) == '#') {
                int num = Integer.parseInt(S.substring(i - 2, i));
                res.append((char) ('a' + num - 1));
                i -= 3;
            } else {
                res.append((char) ('a' + (S.charAt(i) - '0') - 1));
                i--;
            }
        }

        System.out.print(res.reverse());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine().trim();
        decodeMessage(S);
    }
}
