import java.io.IOException;
import java.util.Arrays;

public class Day01 {

    private static final String FILE_IN_NAME = "day-01.txt";

    public static void main(String[] args) throws IOException {
        String inputString = Util.getFileContent(FILE_IN_NAME);

        int sum = Arrays.stream(inputString.split("\n"))
            .mapToInt(Integer::valueOf)
            .sum();

        System.out.println("Sum = " + sum);
    }
}
