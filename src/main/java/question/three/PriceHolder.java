package question.three;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public final class PriceHolder {

  private final ConcurrentHashMap<String, PriceWithChangedStatus> prices;

  public PriceHolder() {
    prices = new ConcurrentHashMap<>();
  }

  /**
   * Called when a price ‘p’ is received for an entity ‘e’
   */
  public void putPrice(String e, BigDecimal p) {
    PriceWithChangedStatus old = prices.put(e, new PriceWithChangedStatus(p, true));
    if (old != null) {
      synchronized (old) {
        old.notifyAll();
      }
    }
  }

  /**
   * Called to get the latest price for entity ‘e’
   *
   * @throws NullPointerException if there is no price for 'e'
   */
  public BigDecimal getPrice(String e) {
    return prices.computeIfPresent(e, (k, price) -> {
      price.setHasChanged(false);
      return price;
    }).getPrice();
  }

  /**
   * Called to determine if the price for entity ‘e’ has changed since the last call to getPrice(e)
   * or waitForNextPrice(e).
   *
   * @throws NullPointerException if there is no price for 'e'
   */
  public boolean hasPriceChanged(String e) {
    return prices.get(e).getHasChanged();
  }

  /**
   * Returns the next price for entity ‘e’. If the price has changed since the last call to
   * getPrice() or waitForNextPrice(), it returns immediately that price. Otherwise it blocks until
   * the next price change for entity ‘e’.
   *
   * @throws NullPointerException if there is currently no price for 'e'
   */
  public BigDecimal waitForNextPrice(String e) throws InterruptedException {
    PriceWithChangedStatus currentPrice = prices.get(e);
    if (currentPrice.getHasChanged()) {
      return getPrice(e);
    }

    synchronized (currentPrice) {
      try {
        currentPrice.wait();
      } catch (InterruptedException ignored) {
      }
      return getPrice(e);
    }
  }
}
