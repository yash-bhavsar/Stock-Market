package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type User.
 */
public class User {

  private List<Portfolio> portfolios;

  public User() {
    portfolios = new ArrayList<>();
  }

  /**
   * Gets portfolios.
   *
   * @return the portfolios
   */
  public List<Portfolio> getPortfolios() {
    return this.portfolios;
  }
}
