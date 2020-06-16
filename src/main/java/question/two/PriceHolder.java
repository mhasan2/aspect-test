package question.two;

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
    prices.put(e, new PriceWithChangedStatus(p, true));
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
   * Called to determine if the price for entity ‘e’ has changed since the last call to
   * getPrice(e).
   *
   * @throws NullPointerException if there is no price for 'e'
   */
  public boolean hasPriceChanged(String e) {
    return prices.get(e).getHasChanged();
  }
}
