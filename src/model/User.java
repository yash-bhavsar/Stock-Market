package model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type User.
 */
public class User {

  private Map<Integer, Portfolio> portfolios;

  /**
   * Instantiates a new User.
   */
  public User() {
    this.portfolios = new HashMap<Integer, Portfolio>();
  }

  /**
   * Gets portfolios.
   *
   * @return the portfolios
   */
  public Map<Integer, Portfolio> getPortfolios() {
    return this.portfolios;
  }

  /**
   * Create portfolio.
   *
   * @param portfolioNumber the portfolio number
   */
  public void createPortfolio(int portfolioNumber) {
    if (this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("Portfolio number already exists");
    }
    Portfolio portfolio = new Portfolio();
    this.portfolios.put(portfolioNumber, portfolio);
  }

  /**
   * Buy stock.
   *
   * @param portfolioNumber the portfolio number
   * @param stock           the stock
   */
  public void buyStock(int portfolioNumber, Stock stock) {
    if (!this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("Create portfolio first.");
    } else {
      Portfolio userPortfolio = this.portfolios.get(portfolioNumber);
      userPortfolio.addStock(stock);
    }
  }

  /**
   * View composition string.
   *
   * @param portfolioNumber the portfolio number
   * @return string string
   */
  public String viewComposition(int portfolioNumber) {
    checkPortfolioNumber(portfolioNumber);
    Portfolio portfolio = this.portfolios.get(portfolioNumber);
    return portfolio.getStocks().stream().map(Object::toString).collect(Collectors.joining("\n"));
  }

  /**
   * Calculate cost basis.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return the cost basis.
   */
  public double calculateCostBasis(int portfolioNumber, String date) {
    checkPortfolioNumber(portfolioNumber);
    return this.portfolios.get(portfolioNumber).getStocksBeforeDate(date)
            .stream()
            .mapToDouble(Stock::calculateCostBasis)
            .sum();
  }

  /**
   * Evaluate portfolio.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return the evaluated portfolio.
   */
  public double evaluatePortfolio(int portfolioNumber, String date) {
    checkPortfolioNumber(portfolioNumber);
    Services services = Services.getInstance();
    return this.portfolios.get(portfolioNumber).getStocksBeforeDate(date)
            .stream()
            .mapToDouble(stock -> stock.evaluate(services.getValueForCompany(date, stock.getTicker())))
            .sum();
  }

  /**
   * Helper method to check portfolio number.
   * @param portfolioNumber is the portfolio number.
   */
  private void checkPortfolioNumber(int portfolioNumber) {
    if (!this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("Create portfolio first.");
    }
  }
}
