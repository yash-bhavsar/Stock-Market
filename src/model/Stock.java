package model;

/**
 * The type model.Stock.
 */
public class Stock {

  private final String ticker;
  private int shares;
  private final float purchasePrice;
  private final String date;
  //remove this field.
  private final float currentPrice;

  /**
   * Instantiates a new model.Stock.
   *
   * @param ticker        the ticker
   * @param shares        the shares
   * @param purchasePrice the purchase price
   * @param date          the date time
   * @param currentPrice  the current price
   */
  public Stock(String ticker, int shares, float purchasePrice, String date, float currentPrice) {
    this.ticker = ticker;
    this.shares = shares;
    this.purchasePrice = purchasePrice;
    this.date = date;
    this.currentPrice = currentPrice;//remove this field.
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
   * Sets shares.
   *
   * @param shares the shares
   */
  public void setShares(int shares) {
    this.shares = shares;
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
   * Gets date time. May need to rename to getDate.
   *
   * @return the date time
   */
  public String getDateTime() {
    return date;
  }

  /**
   * Gets current price.
   *
   * @return the current price
   */
  public float getCurrentPrice() {
    return currentPrice;
  }

  /**
   * Method to evaluate the price.
   *
   * @param value is the value of the share.
   * @return returns the evaluated value which is a double.
   */
  public double evaluate(double value) {
    return this.shares * value;
  }

  /**
   * Calculate cost basis double.
   *
   * @return the double
   */
  public double calculateCostBasis() {
    return this.purchasePrice * this.shares;
  }

  @Override
  public String toString() {
    return "Company name: " + this.ticker + "\nDate of purchase: " + this.getDateTime() +
            "\nPurchase price: " + this.purchasePrice + "\nNumber of shares: " + this.shares +
            "\n";
  }
}
