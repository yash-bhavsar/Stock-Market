package model;

/**
 * The interface Stock market controller.
 */
public interface IStockMarketModel {

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
   * @return the string
   */
  String viewComposition(int portfolioNumber);

  /**
   * Create portfolio.
   *
   * @param portfolioNumber the portfolio name
   */
  void createPortfolio(int portfolioNumber);

  /**
   * Evaluate the portfolio.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return int double
   */
  double evaluatePortfolio(int portfolioNumber, String date);

  /**
   * Calculate cost basis double.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return double double
   */
  double calculateCostBasis(int portfolioNumber, String date);
}
