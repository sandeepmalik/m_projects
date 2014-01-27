import java.io.*;

/**
 * Author: Monika
 * Date  : 1/26/14
 * Time  : 5:40 PM
 */
public class Utils {

    public static String fileContent(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Utils.class.getClassLoader().getResourceAsStream(fileName)));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
