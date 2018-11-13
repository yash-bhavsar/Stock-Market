package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type model.Portfolio.
 */
public class Portfolio {

  private Map<Integer, List<Stock>> portfolio;

  /**
   * Instantiates a new Portfolio.
   */
  public Portfolio() {
    this.portfolio = new HashMap<>();
  }

  /**
   * Gets portfolio.
   *
   * @return the portfolio
   */
  public Map<Integer, List<Stock>> getPortfolio() {
    return this.portfolio;
  }

  /**
   * Add stock.
   *
   * @param portfolioNumber the portfolio number
   * @param stock           the stock
   */
  public void addStock(int portfolioNumber, Stock stock) {
    if (this.portfolio.containsKey(portfolioNumber)) {
      if (!this.portfolio.get(portfolioNumber).isEmpty()) {
        List<Stock> stockList = this.portfolio.get(portfolioNumber);
        stockList.add(stock);
        this.portfolio.put(portfolioNumber, stockList);
      }
    }
     else {
      List<Stock> stockList = new ArrayList<>();
      stockList.add(stock);
      this.portfolio.put(portfolioNumber, stockList);
    }
  }
}
