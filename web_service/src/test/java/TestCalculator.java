import com.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Monika
 * Date  : 1/25/14
 * Time  : 3:15 PM
 */
public class TestCalculator {

    @Test(dataProvider = "add")
    public void testAdd(int value1, int value2, int expected) {
        System.out.printf("Adding %d, and %d and expecting %d\n", value1, value2, expected);
        Calculator calculator = new Calculator();
        int result = calculator.add(value1, value2);
        Assert.assertEquals(expected, result);
    }

    @DataProvider(name = "add")
    public Iterator<Object[]> dataForAdd() throws Exception {
        List<Object[]> data = new ArrayList<Object[]>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/smalik/Downloads/trends.csv"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] st = line.split(",");
            int value1 = Integer.parseInt(st[0]);
            int value2 = Integer.parseInt(st[1]);
            int expected = Integer.parseInt(st[2]);
            data.add(new Object[]{value1, value2, expected});
        }
        return data.iterator();
    }
}
