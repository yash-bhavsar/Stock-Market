package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
   *
   * @param portfolioNumber
   */
  public void createPortfolio(int portfolioNumber) {
    if (this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("Portfolio number already exists");
    }
    Portfolio portfolio = new Portfolio();
    this.portfolios.put(portfolioNumber, portfolio);
  }

  /**
   *
   *
   * @param portfolioNumber
   * @param stock
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
   *
   * @param portfolioNumber
   * @return
   */
  public String viewComposition(int portfolioNumber) {
    if (!this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("Create portfolio first.");
    }
    Portfolio portfolio = this.portfolios.get(portfolioNumber);
    return portfolio.getStocks().stream().map(Object::toString).toString();
  }

  public int evaluatePortfolio(int portfolioNumber, String date) {
    Services services = Services.getInstance();
    int value = this.portfolios.get(portfolioNumber).getStocksBeforeDate(date)
            .stream()
            .mapToInt(stock -> stock.evaluate(services.getValueForCompany(date, stock.getTicker())))
            .sum();
    return value;
  }
}
