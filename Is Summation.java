import java.util.*;

public class Main {
     static boolean solve(int N, int k , int[] arr, int i,int sum,HashMap<String,Boolean> map){
        if(sum ==k){
            return true;
         }
        if(i==N){
            return false;
        }

        String st = i+ "," + sum;
        if(map.containsKey(st)){
            return map.get(st);
        }
        
        boolean Nottake = solve(N,k,arr,i+1,sum,map);
        boolean Take = solve(N,k,arr,i+1,sum +arr[i],map);
        
         map.put(st,(Nottake || Take));
        return Nottake || Take;
         
        
        

    }
    public static void canSelectPeople(int N, int K, int[] arr) {
        // Write your logic here.
        HashMap<String,Boolean> map = new HashMap<>();
        if(solve(N,K,arr,0,0,map)){
            System.out.println("YES");
        }
        else{
              System.out.println("NO");
        }
        


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        canSelectPeople(N, K, arr);
    }
}
