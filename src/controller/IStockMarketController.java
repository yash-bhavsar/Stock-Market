package controller;

/**
 * The interface Stock market controller.
 */
public interface IStockMarketController {

  /**
   * Method to buy stock.
   *
   * @param ticker          the ticker
   * @param numberOfStocks  the number of stocks
   * @param date            the date
   * @param portfolioNumber the portfolio number
   */
  void buyStock(String ticker, int numberOfStocks, String date, int portfolioNumber);

  /**
   * View composition of a portfolio.
   *
   * @param portfolioNumber the portfolio name
   */
  String viewComposition(int portfolioNumber);

  /**
   * Create portfolio.
   *
   * @param portfolioNumber the portfolio name
   */
  void createPortfolio(int portfolioNumber);

  /**
   * Evaluate the po
   *
   * @param portfolioNumber
   * @return
   */
  int evaluatePortfolio(int portfolioNumber, String date);
}
