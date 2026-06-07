import java.util.*;

public class Main {

    static void specialmsg(String s, List<Map.Entry<String, String>> vocab) {

        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> entry : vocab) {
            map.put(entry.getKey(), entry.getValue());
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                int j = i + 1;
                StringBuilder key = new StringBuilder();

                while (j < s.length() && s.charAt(j) != ')') {
                    key.append(s.charAt(j));
                    j++;
                }

                result.append(map.getOrDefault(key.toString(), "?"));
                i = j; // skip till ')'
            } else {
                result.append(s.charAt(i));
            }
        }

        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        int n = Integer.parseInt(sc.nextLine());

        List<Map.Entry<String, String>> vocab = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] kv = sc.nextLine().split(" ");
            vocab.add(new AbstractMap.SimpleEntry<>(kv[0], kv[1]));
        }

        specialmsg(s, vocab);
    }
}
