import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Main {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Stack<Integer> st = new Stack<>();

        for(int i=0;i<N;i++){
            String op = sc.next();
            if(op.equals("ADD")){
                int num = sc.nextInt();
                st.push(num);
            }
            else{
                st.pop();
            }

        }
        if(st.isEmpty()){
            System.out.print("-1");
        }
        else{
            System.out.print(st.pop());
        }
        
    }
}
