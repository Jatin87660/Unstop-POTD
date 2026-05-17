import java.util.Scanner;

public class Main {
    public static int LongestConsecutiveCharacter(String s) {
        // Write your logic here.
        int n = s.length();
        if(n==1){
            return 1;
        }
        int ans =0;
        int count=1;
        for(int i=1;i<n;i++){
            int prev = s.charAt(i-1);
            int curr = s.charAt(i);
            if( prev == curr){
                count++;
            }
            else{
                ans = Math.max(ans,count);
                count=1;
            }
        }
        return Math.max(ans,count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().trim();
        scanner.close();
        
        // Call user logic function and print the output
        int result = LongestConsecutiveCharacter(s);
        System.out.println(result);
    }
}
