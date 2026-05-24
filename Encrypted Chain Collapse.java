import java.util.Scanner;

public class Main {
    public static int energy(char ch) {

        
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a' + 1;
        }

     
        return (ch - '0') * 10;
    }

    public static void collapseChain(String s) {

        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {

            int currEnergy = energy(ch);
            if (stack.length() > 0) {

                char top = stack.charAt(stack.length() - 1);

                if (energy(top) == currEnergy) {
                    stack.deleteCharAt(stack.length() - 1);
                    continue;
                }
            }

            stack.append(ch);
        }

        if (stack.length() == 0) {
            System.out.print("-1");
        } else {
            System.out.print(stack.toString());
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine().trim();

        collapseChain(s);
    }
}
