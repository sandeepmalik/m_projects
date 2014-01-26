import com.Example1;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Monika
 * Date  : 1/25/14
 * Time  : 4:40 PM
 */
public class Test3Divisibility {

    @Test(dataProvider = "3_1")
    public void testDivisibleBy3(int number, boolean expected) {
        boolean actual = new Example1().isDivisibleBy3(number);
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name = "3")
    public Iterator<Object[]> testDivisibleBy3DP() {
        List<Object[]> objects = new ArrayList<Object[]>();
        objects.add(new Object[]{0, true});
        objects.add(new Object[]{3, true});
        objects.add(new Object[]{4, false});
        objects.add(new Object[]{-4, false});
        return objects.iterator();
    }

    @DataProvider(name = "3_1")
    public Iterator<Object[]> testDivisibleBy3DP_1() throws Exception {
        List<Object[]> objects = new ArrayList<Object[]>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/smalik/Downloads/div3.csv"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split(",");
            int number = Integer.parseInt(parts[0]);
            boolean expected = Boolean.parseBoolean(parts[1]);
            objects.add(new Object[]{number, expected});
        }
        return objects.iterator();
    }

}
