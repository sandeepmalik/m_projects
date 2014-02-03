import com.RangeFinder;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.*;

/**
 * Author: Monika
 * Date  : 2/2/14
 * Time  : 11:31 PM
 */
public class TestRangeFinder {

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testEmptyList() {
        List<Integer> integers = Arrays.asList();
        RangeFinder rangeFinder = new RangeFinder();
        rangeFinder.findRange(integers);
    }

    @Test(dataProvider = "dataForRange")
    public void testRange(List<Integer> numbers, RangeFinder.Range expected) {
        RangeFinder rangeFinder = new RangeFinder();
        RangeFinder.Range result = rangeFinder.findRange(numbers);
        Assert.assertEquals(expected, result);
    }

    @DataProvider
    public Iterator<Object[]> dataForRange() {
        List<Object[]> data = new ArrayList<Object[]>();
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        List<Integer> numbers2 = Arrays.asList(-1, -2, -3, -4, -5);
        List<Integer> numbers4 = Arrays.asList(0, 1, 2, 4, 5);
        List<Integer> numbers5 = Arrays.asList(0, 0, 0, 0, 0);
        List<Integer> numbers6 = Arrays.asList(-1, -2, 3, 4, 5);
        data.add(new Object[]{numbers, new RangeFinder.Range(1, 5)});
        data.add(new Object[]{numbers2, new RangeFinder.Range(-5, -1)});
        data.add(new Object[]{numbers4, new RangeFinder.Range(0, 5)});
        data.add(new Object[]{numbers5, new RangeFinder.Range(0, 0)});
        data.add(new Object[]{numbers6, new RangeFinder.Range(-2, 5)});

        return data.iterator();


    }
}
