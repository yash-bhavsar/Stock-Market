package model;

/**
 * The class model.Stock which has getter and setter methods for ticker, shares, purchase price,
 * date and current price.
 */
public class Stock {

  private final String ticker;
  private int shares;
  private final float purchasePrice;
  private final String date;
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
   * @return the date and time
   */
  public String getDateTime() {
    return date;
  }

  /**
   * Gets current price of a stock.
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

  /**
   * Overridden toString method which outputs the information in the format, company name, date
   * of purchase, purchase price, number of shares, each separated by a newline.
   * @return the formatted string.
   */
  @Override
  public String toString() {
    return "Company name: " + this.ticker + "\nDate of purchase: " + this.getDateTime() +
            "\nPurchase price: " + this.purchasePrice + "\nNumber of shares: " + this.shares +
            "\n";
  }
}
