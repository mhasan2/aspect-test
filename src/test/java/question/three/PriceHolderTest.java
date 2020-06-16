package question.three;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PriceHolderTest {

  private PriceHolder underTest;

  @Before
  public void setUp() {
    underTest = new PriceHolder();
  }

  @Test
  public void priceCanBeSavedAndRetrieved() {
    BigDecimal expectedPrice = new BigDecimal("100");
    String key = "a";

    underTest.putPrice(key, expectedPrice);

    Assert.assertEquals(expectedPrice, underTest.getPrice(key));

    assertFalse(underTest.hasPriceChanged(key));
  }

  @Test
  public void priceHasChangedAfterPut() {
    BigDecimal expectedPrice = new BigDecimal("250");
    String key = "a";

    underTest.putPrice(key, expectedPrice);

    assertTrue(underTest.hasPriceChanged(key));
  }

  @Test(timeout = 1000)
  public void waitForPriceReturnsIfPriceHasChanged() throws InterruptedException {
    BigDecimal expectedPrice = new BigDecimal("150");
    String key = "a";

    underTest.putPrice(key, expectedPrice);

    Assert.assertEquals(expectedPrice, underTest.waitForNextPrice(key));
  }

  @Test(timeout = 1000)
  public void waitForPriceReturnsAfterPut() throws InterruptedException {
    BigDecimal expectedPrice = new BigDecimal("150");
    String key = "a";

    underTest.putPrice(key, new BigDecimal("50"));
    underTest.getPrice(key);

    new Thread(() -> {
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
      }
      underTest.putPrice(key, expectedPrice);
    }).start();

    Assert.assertEquals(expectedPrice, underTest.waitForNextPrice(key));
  }
}
