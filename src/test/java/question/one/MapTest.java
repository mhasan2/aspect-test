package question.one;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class MapTest {

  @Test
  public void testPlusOne() {
    List<Integer> input = Arrays.asList(1, 2, 3);
    List<Integer> expected = Arrays.asList(2, 3, 4);

    List<Integer> actual = Map.map(n -> n + 1, input);

    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testOriginalListNotModified() {
    List<Integer> input = Arrays.asList(1, 2, 3);

    Map.map(n -> n * 2, input);

    Assert.assertEquals(Arrays.asList(1, 2, 3), input);
  }
}