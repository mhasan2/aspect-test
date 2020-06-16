package question.two;

import static org.junit.Assert.*;

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
  public void mostRecentPriceIsRetrieved() {
    BigDecimal expectedPrice = new BigDecimal("100");
    String key = "a";

    underTest.putPrice(key, new BigDecimal("20"));
    underTest.putPrice(key, new BigDecimal("400"));
    underTest.putPrice(key, expectedPrice);
    underTest.putPrice("b", new BigDecimal("150"));

    Assert.assertEquals(expectedPrice, underTest.getPrice(key));
  }

  @Test
  public void priceHasChangedAfterPut() {
    BigDecimal expectedPrice = new BigDecimal("250");
    String key = "a";

    underTest.putPrice(key, expectedPrice);

    assertTrue(underTest.hasPriceChanged(key));
  }
}