import java.util.*;

public class Main {
    public static long[] computeFinalSizes(int numMinerals, int[] initialSizes, int numDays) {
        long[] finalSizes = new long[numMinerals];

        // The user will write the logic here

        for(int i=0;i<numMinerals;i++){
            int size = initialSizes[i];
            int count=0;
            for(int j=0;j<numDays;j++){
                if(count==2){
                    size /=2;
                    count=0;
                }
                else{
                    size *=2;
                    count++;
                }
            
            }
            finalSizes[i] = size;
        }

        return finalSizes;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numMinerals = scanner.nextInt();
        int[] initialSizes = new int[numMinerals];
        for (int i = 0; i < numMinerals; ++i) {
            initialSizes[i] = scanner.nextInt();
        }
        int numDays = scanner.nextInt();

        long[] result = computeFinalSizes(numMinerals, initialSizes, numDays);

        for (long size : result) {
            System.out.print(size + " ");
        }
        System.out.println();
    }
}
