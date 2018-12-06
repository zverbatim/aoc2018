import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;

public class Day03 {

    private static final String FILE_IN_NAME = "day-03.txt";

    private static void markSuit(int[][] suit, String patch) {
        String[] data = patch.split(" ");
        int j = Integer.valueOf(data[2].split(",")[0]);
        int i = Integer.valueOf(data[2].split(",")[1].replace(":", ""));
        int jSize = Integer.valueOf(data[3].split("x")[0]);
        int iSize = Integer.valueOf(data[3].split("x")[1]);

        for (int line = i; line < i + iSize; line++) {
            for (int column = j; column < j + jSize; column++) {
                suit[line][column] = suit[line][column] == 0 ? 1 : 2;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String inputString = Util.getFileContent(FILE_IN_NAME);

        int[][] suit = new int[1000][1000];

        Arrays.stream(inputString.split("\n"))
            .forEach(it -> Day03.markSuit(suit, it));

        long overlap = Arrays.stream(suit)
            .flatMapToInt(Arrays::stream)
            .filter(it -> it == 2)
            .count();

        System.out.println("Overlap area = " + overlap);

//        for (int i = 0; i < suit.length; i++) {
//            for (int j = 0; j < suit[i].length; j++) {
//                System.out.print(suit[i][j] + " ");
//            }
//            System.out.println();
//        }
    }
}
