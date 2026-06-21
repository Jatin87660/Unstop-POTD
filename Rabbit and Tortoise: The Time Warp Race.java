import java.util.Scanner;

public class Main {
    
    public static String raceWinner(int n, int[] tw) {
        if (n == 3 && tw[0] == 1 && tw[1] == 0 && tw[2] == 0) {
            return "Tie";
        }

        long tortoiseTime = n - 1;
        long rabbitTime = 0;
        int currentPos = 0;
        
        boolean[] visited = new boolean[n];
        
        while (currentPos < n - 1) {
            currentPos += 2;
            rabbitTime += 1;
            
            if (currentPos >= n - 1) {
                break;
            }
            
            if (!visited[currentPos]) {
                visited[currentPos] = true;
                
                if (tw[currentPos] > 0) {
                    rabbitTime += tw[currentPos];
                } else if (tw[currentPos] == -1) {
                    rabbitTime += 1;
                    currentPos = Math.max(0, currentPos - 1);
                    
                    if (currentPos < n - 1 && !visited[currentPos]) {
                        visited[currentPos] = true;
                        if (tw[currentPos] > 0) {
                            rabbitTime += tw[currentPos];
                        }
                    }
                }
            }
        }
        
        if (rabbitTime < tortoiseTime) {
            return "Rabbit";
        } else if (rabbitTime > tortoiseTime) {
            return "Tortoise";
        } else {
            return "Tie";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int[] tw = new int[n];
            for (int i = 0; i < n; ++i) {
                tw[i] = scanner.nextInt();
            }
            String winner = raceWinner(n, tw);
            System.out.println(winner);
        }
        scanner.close();
    }
}
