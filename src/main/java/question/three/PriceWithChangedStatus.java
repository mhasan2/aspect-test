package question.three;

import java.math.BigDecimal;

public class PriceWithChangedStatus {

  private final BigDecimal price;
  private boolean hasChanged;

  public PriceWithChangedStatus(BigDecimal price, boolean hasChanged) {
    this.price = price;
    this.hasChanged = hasChanged;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public boolean getHasChanged() {
    return hasChanged;
  }

  public void setHasChanged(boolean hasChanged) {
    this.hasChanged = hasChanged;
  }
}
