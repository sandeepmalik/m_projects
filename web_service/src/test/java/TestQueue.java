import org.junit.Assert;
import org.testng.annotations.Test;
import sun.misc.Queue;

/**
 * Author: Monika
 * Date  : 2/3/14
 * Time  : 12:18 PM
 */
public class TestQueue<T> {

    @Test
    public void enqueue(T item,T expected) {
        Queue queue = new Queue();
        T result = queue.enqueue();
        Assert.assertEquals();
    }
}
