import com.FizzBuzz;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Monika
 * Date  : 2/2/14
 * Time  : 5:47 PM
 */
public class TestFizzBuzz {

    @Test(dataProvider = "max")
    public void testMax(int[] array, int expected) {
        FizzBuzz fizzBuzz = new FizzBuzz();
        int result = fizzBuzz.findMaximum(array);
        Assert.assertEquals(expected, result);
    }

    @DataProvider(name = "max")
    public Iterator<Object[]> dataForAdd() {
        List<Object[]> data = new ArrayList<Object[]>();
        int[] array = {3,8,2};
        int[] array1 = {};
        int[] array2 = {-4,-3,-6};
        int[] array3 = {0,0,0,0};
        int[] array4 = {9,5,-7,8};
        int[] array5 = {1};
        data.add(new Object[]{array,8});
        data.add(new Object[]{array1,Integer.MIN_VALUE});
        data.add(new Object[]{array2,-3});
        data.add(new Object[]{array3,0});
        data.add(new Object[]{array4, 9});
        data.add(new Object[]{array5,1});

        return data.iterator();

    }

}
