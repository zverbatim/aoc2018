import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Util {

    /**
     * Return the string content of a file
     *
     * @param fileName - file to be read
     * @return string content
     */
    public static String getFileContent(String fileName) throws IOException {
        ClassLoader classLoader = Day01.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return FileUtils.readFileToString(file, "UTF-8");
    }
}
