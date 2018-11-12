import java.util.Date;

/**
 * The type Stock.
 */
public class Stock {

  private final String ticker;
  private final int shares;
  private final float purchasePrice;
  private final Date dateTime;
  private final float currentPrice;

  /**
   * Instantiates a new Stock.
   *
   * @param ticker        the ticker
   * @param shares        the shares
   * @param purchasePrice the purchase price
   * @param dateTime      the date time
   * @param currentPrice  the current price
   */
  public Stock(String ticker, int shares, float purchasePrice, Date dateTime, float currentPrice) {
    this.ticker = ticker;
    this.shares = shares;
    this.purchasePrice = purchasePrice;
    this.dateTime = dateTime;
    this.currentPrice = currentPrice;
  }

  /**
   * Gets ticker.
   *
   * @return the ticker
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Gets shares.
   *
   * @return the shares
   */
  public int getShares() {
    return shares;
  }

  /**
   * Gets purchase price.
   *
   * @return the purchase price
   */
  public float getPurchasePrice() {
    return purchasePrice;
  }

  /**
   * Gets date time.
   *
   * @return the date time
   */
  public Date getDateTime() {
    return dateTime;
  }

  /**
   * Gets current price.
   *
   * @return the current price
   */
  public float getCurrentPrice() {
    return currentPrice;
  }
}
