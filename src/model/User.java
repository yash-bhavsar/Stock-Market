package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User.
 */
public class User {

  private List<Portfolio> portfolios;

  /**
   * Instantiates a new User.
   */
  public User() {
    this.portfolios = new ArrayList<>();
  }

  /**
   * Gets portfolios.
   *
   * @return the portfolios
   */
  public List<Portfolio> getPortfolios() {
    return this.portfolios;
  }

  /**
   * Create portfolio.
   */
  public void createPortfolio() {
    Portfolio portfolio = new Portfolio();
    this.portfolios.add(portfolio);
  }
}
