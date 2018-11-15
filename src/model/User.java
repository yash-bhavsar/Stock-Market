package model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The class User which has methods to buy stock, create portfolio, evaluate portfolio, calculate
 * cost basis and view composition of the portfolio.
 */
public class User {

  private Map<Integer, Portfolio> portfolios;

  /**
   * Instantiates a new User and the portfolios hash map.
   */
  public User() {
    this.portfolios = new HashMap<>();
  }

  /**
   * Method to get portfolios.
   *
   * @return the portfolios
   */
  public Map<Integer, Portfolio> getPortfolios() {
    return this.portfolios;
  }

  /**
   * Method to create portfolio.
   *
   * @param portfolioNumber the portfolio number
   */
  public void createPortfolio(int portfolioNumber) {
    if (portfolioNumber < 0) {
      throw new IllegalArgumentException("\nInvalid number entered.\n");
    } else if (this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("\nPortfolio number already exists\n");
    }
    Portfolio portfolio = new Portfolio();
    this.portfolios.put(portfolioNumber, portfolio);
  }

  /**
   * Method to buy stock.
   *
   * @param portfolioNumber the portfolio number
   * @param stock           the stock
   */
  public void buyStock(int portfolioNumber, Stock stock) {
    if (!this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("\nCreate portfolio first.\n");
    } else {
      Portfolio userPortfolio = this.portfolios.get(portfolioNumber);
      userPortfolio.addStock(stock);
    }
  }

  /**
   * Method to view the composition.
   *
   * @param portfolioNumber the portfolio number
   * @return the composition of the portfolio
   */
  public String viewComposition(int portfolioNumber) {
    checkPortfolioNumber(portfolioNumber);
    Portfolio portfolio = this.portfolios.get(portfolioNumber);
    return portfolio.getStocks().stream().map(Object::toString).collect(Collectors.joining("\n"));
  }

  /**
   * Method to calculate cost basis.
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
   * Method to evaluate portfolio.
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
            .mapToDouble(
                    stock -> stock.evaluate(services.getValueForCompany(date, stock.getTicker()))
            ).sum();
  }

  /**
   * Helper method to check portfolio number.
   *
   * @param portfolioNumber is the portfolio number.
   */
  private void checkPortfolioNumber(int portfolioNumber) {
    if (portfolioNumber < 0) {
      throw new IllegalArgumentException("\nInvalid number entered.\n");
    } else if (!this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("\nCreate portfolio first.\n");
    }
  }
}
