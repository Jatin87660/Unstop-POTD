import java.util.Scanner;

public class Main {
    // User logic function
    public static int userLogic(String s) {
        // Write your logic here.
       
       int n = s.length();
       int happy=0;
       int sad =0;

       for(int i=0;i<n-1;i++){
        if(s.charAt(i) == ':' && s.charAt(i+1) == ')'){
            happy++;
        }
        if(s.charAt(i) == ':' && s.charAt(i+1) == '('){
            sad++;
        }

       }
       int pairs = Math.min(happy,sad);
       return pairs*2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine(); // Read the entire line as input

        // Call user logic function and print the output
        int result = userLogic(s);
        System.out.println(result);
    }
}
