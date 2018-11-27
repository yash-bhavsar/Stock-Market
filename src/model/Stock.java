package model;

/**
 * The class model.Stock which has getter and setter methods for ticker, shares, purchase price,
 * date and current price. Intentionally kept package-private object of this class are not used
 * outside of this package.
 */
public class Stock {

  private final String ticker;
  private double shares;
  private final float purchasePrice;
  private final String date;
  private final float currentPrice;
  private final double commission;

  /**
   * Instantiates a new model.Stock.
   *
   * @param ticker        the ticker
   * @param shares        the shares
   * @param purchasePrice the purchase price
   * @param date          the date time
   * @param currentPrice  the current price
   * @param commission    the commission
   */
  public Stock(String ticker, double shares, float purchasePrice, String date, float currentPrice, double commission) {
    this.ticker = ticker;
    this.shares = shares;
    this.purchasePrice = purchasePrice;
    this.date = date;
    this.currentPrice = currentPrice;
    this.commission = commission;
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
  public double getShares() {
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
    return this.purchasePrice * this.shares + this.commission;
  }

  /**
   * Gets commission.
   *
   * @return the commission
   */
  public double getCommission() {
    return commission;
  }

  /**
   * Overridden toString method which outputs the information in the format, company name, date of
   * purchase, purchase price, number of shares, each separated by a newline.
   *
   * @return the formatted string.
   */
  @Override
  public String toString() {
    return "Company name: " + this.ticker + "\nDate of purchase: " + this.getDateTime() +
            "\nPurchase price: $" + this.purchasePrice + "\nNumber of shares: " + this.shares +
            "\n";
  }
}
