package model;

import java.util.Date;

/**
 * The type model.Stock.
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
   * @param date      the date time
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
   *
   * @param shares
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
   * Gets date time.
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

  public int evaluate(int value) {
    return this.shares * value;
  }
}
