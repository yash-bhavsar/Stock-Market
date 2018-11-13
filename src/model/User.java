package model;

import java.util.ArrayList;
import java.util.List;

public class User {

  private List<Portfolio> portfolios;

  public User() {
    this.portfolios = new ArrayList<>();
  }

  public List<Portfolio> getPortfolios() {
    return portfolios;
  }

  public void createPortfolio() {
    Portfolio portfolio = new Portfolio();
    this.portfolios.add(portfolio);
  }
}
