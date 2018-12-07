import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Day04 {

    private static final String FILE_IN_NAME = "day-04.txt";

    public static void main(String[] args) throws IOException {
        String inputString = Util.getFileContent(FILE_IN_NAME);

        List<String> guardEntries = Arrays.stream(inputString.split("\n"))
            .sorted()
            .collect(Collectors.toList());

        int currentGuard = 0;
        int minuteFallsAsleep = 0;
        int minuteWakesUp = 0;
        Map<Integer, List<Integer>> guardMinutesSleep = new HashMap<>();
        for (String guardEntry : guardEntries) {
            if (guardEntry.contains("Guard")) {
                currentGuard = Integer.valueOf(guardEntry.split(" ")[3].replace("#", ""));

            } else {
                int minute = Integer.valueOf(guardEntry.split(" ")[1].split(":")[1].replace("]", ""));

                if (guardEntry.contains("falls asleep")) {
                    minuteFallsAsleep = minute;
                }

                if (guardEntry.contains("wakes up")) {
                    minuteWakesUp = minute;

                    List<Integer> asleepMinutes = new ArrayList<>(Collections.nCopies(60, 0));
                    if (guardMinutesSleep.containsKey(currentGuard)) {
                        asleepMinutes = guardMinutesSleep.get(currentGuard);
                    }

                    for (int i = minuteFallsAsleep; i < minuteWakesUp; i++) {
                        int currentMinute = asleepMinutes.get(i) + 1;
                        asleepMinutes.set(i, currentMinute);
                    }

                    // update the sleep "registry"
                    guardMinutesSleep.put(currentGuard, asleepMinutes);
                    minuteFallsAsleep = 0;
                    minuteWakesUp = 0;
                }
            }
        }

        // which guard sleep the most
        int maxGuard = 0;
        int maxTotalMinute = 0;
        for (Map.Entry<Integer, List<Integer>> guardSleep : guardMinutesSleep.entrySet()) {
            int sum = guardSleep.getValue().stream().mapToInt(a -> a).sum();
            if (sum > maxTotalMinute) {
                maxTotalMinute = sum;
                maxGuard = guardSleep.getKey();
            }
        }

        // which minute the most sleepy guard was asleep the most
        int indexMinuteMax = 0;
        int maxMinute = 0;
        List<Integer> maxGuardMinutes = guardMinutesSleep.get(maxGuard);
        for (int i = 0; i < maxGuardMinutes.size(); i++) {
            if (maxGuardMinutes.get(i) > maxMinute) {
                maxMinute = maxGuardMinutes.get(i);
                indexMinuteMax = i;
            }
        }

        System.out.println("maxGuard = " + maxGuard);
        System.out.println("indexMaxMinute = " + indexMinuteMax);
        System.out.println("the product of maxGuard * indexMinuteMax = " + maxGuard * indexMinuteMax);
    }
}
