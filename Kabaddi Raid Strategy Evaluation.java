import java.util.*;



class Pair{
    int num;
    int idx;
    Pair(int num, int idx){
        this.num = num;
        this.idx = idx;
    }
}

public class Main {
    public static List<Integer> evaluateRaids(int n, List<String> raids, List<Integer> stamina) {
        // Your logic here
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if(a.num != b.num){
                 return (a.num - b.num);
            }
            return (a.idx - b.idx);
        });

        for(int i=0;i<n;i++){
            String raid = raids.get(i);
            if(raid.contains("RR") || raid.lastIndexOf('T') > raid.lastIndexOf('R') || !raid.contains("T")) continue;
            

            int staminaRequire= stamina.get(i);
            int staminaUse = 0;
            for(char ch : raid.toCharArray()){
                if(ch == 'F'){
                    staminaUse += 1;
                }
                else if(ch == 'T'){
                    staminaUse += 3;
                }
                else{
                    staminaUse += 2;
                }
            } 
            if(staminaUse <= staminaRequire){
                pq.add(new Pair(staminaUse,i));
            }


        }
        ArrayList<Integer> list = new ArrayList<>();
        while(!pq.isEmpty()){
            int index = pq.poll().idx;
            list.add(index);
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline
        List<String> raids = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            raids.add(scanner.nextLine());
        }
        List<Integer> stamina = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stamina.add(scanner.nextInt());
        }

        List<Integer> result = evaluateRaids(n, raids, stamina);

        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
