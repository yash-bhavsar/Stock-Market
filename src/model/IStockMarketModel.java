package model;

/**
 * The interface IStockMarketModel which has methods to buy stocks, calculate cost basis, view
 * composition, create and evaluate portfolio.
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
   * @return the composition of the portfolio.
   */
  String viewComposition(int portfolioNumber, String date);

  /**
   * Create portfolio.
   *
   * @param portfolioNumber the portfolio number
   */
  void createPortfolio(int portfolioNumber);

  /**
   * Evaluate the portfolio.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return returns the evaluated value of the portfolio.
   */
  double evaluatePortfolio(int portfolioNumber, String date);

  /**
   * Calculate cost basis.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return returns the cost basis.
   */
  double calculateCostBasis(int portfolioNumber, String date);
}
