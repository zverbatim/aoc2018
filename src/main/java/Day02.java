
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day02 {

    private static final String FILE_IN_NAME = "day-02.txt";

    private static boolean containsExact(int limit, String s) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            String current = s.substring(i, i + 1);
            if (!frequencyMap.containsKey(current)) {
                frequencyMap.put(current, 1);
            } else {
                int newFrequency = frequencyMap.get(current) + 1;
                frequencyMap.put(current, newFrequency);
            }
        }

        int c = (int) frequencyMap.values()
            .stream()
            .filter(it -> it == limit)
            .count();

        return c > 0;
    }

    public static void main(String[] args) throws IOException {
        String inputString = Util.getFileContent(FILE_IN_NAME);

        int exact2 = (int) Arrays.stream(inputString.split("\n"))
            .filter(it -> containsExact(2, it))
            .count();

        int exact3 = (int) Arrays.stream(inputString.split("\n"))
            .filter(it -> containsExact(3, it))
            .count();

        System.out.println("exact 2 = " + exact2);
        System.out.println("exact 3 = " + exact3);
        System.out.println("Checksum = " + (exact2 * exact3));
    }
}
