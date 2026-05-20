import java.util.*;

public class Main {
    public static int shortestSubstringLength(String S, String L) {
        // Write your logic here.
        HashMap<Character,Integer> map = new HashMap<>();
        for(char ch : L.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        int i=0,j=0;
        int count =L.length();
        int minLen = Integer.MAX_VALUE;
        int n  = S.length();

        while(j<n){
            char ch = S.charAt(j);

            if(map.containsKey(ch)){
                map.put(ch,map.get(ch)-1);

                if(map.get(ch) >=0){
                    count--;
                }
                
                
            }

           
            while(count==0){
            
                minLen = Math.min(minLen,j-i+1);
                char ch2 = S.charAt(i);
               if(map.containsKey(ch2)){
                 map.put(ch2,map.get(ch2)+1);
                if(map.get(ch2)>0){
                    count++;
                }
               }
                i++;
            }
            j++;
        }


        
       
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();  // First line is the longer string S
        String L = scanner.nextLine();  // Second line is the secret code L
        
        // Call user logic function and print the output
        int result = shortestSubstringLength(S, L);
        System.out.println(result);
    }
}
