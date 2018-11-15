package model;

/**
 * The class model.Stock which has getter and setter methods for ticker, shares, purchase price,
 * date.
 */
public class Stock {

  private final String ticker;
  private int shares;
  private final float purchasePrice;
  private final String date;

  /**
   * Instantiates a new model.Stock.
   *
   * @param ticker        the ticker
   * @param shares        the shares
   * @param purchasePrice the purchase price
   * @param date          the date time
   */
  public Stock(String ticker, int shares, float purchasePrice, String date) {
    this.ticker = ticker;
    this.shares = shares;
    this.purchasePrice = purchasePrice;
    this.date = date;
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
   * @return the date
   */
  public String getDate() {
    return date;
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
   * @return the cost basis
   */
  public double calculateCostBasis() {
    return this.purchasePrice * this.shares;
  }

  /**
   * Overridden toString method which formats the output in the format purchase price, date of
   * purchase, purchase price, shares separated by a newline.
   * @return the string formatted in the way described above.
   */
  @Override
  public String toString() {
    return "Company name: " + this.ticker + "\nDate of purchase: " + this.getDate() +
            "\nPurchase price: " + this.purchasePrice + "\nNumber of shares: " + this.shares +
            "\n";
  }
}
