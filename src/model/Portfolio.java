package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type model.Portfolio.
 */
public class Portfolio {

  private List<Stock> stocks;

  /**
   * Instantiates a new Portfolio.
   */
  public Portfolio() {
    this.stocks = new ArrayList<>();
  }

  /**
   * Gets portfolio.
   *
   * @return the portfolio
   */
  public List<Stock> getStocks() {
    return this.stocks;
  }

  public List<Stock> getStocksBeforeDate(String date) {
    return this.stocks
            .stream()
            .filter(stock -> stock.getDateTime().compareTo(date) < 1)
            .collect(Collectors.toList());
  }

  /**
   * Add stock.
   *
   * @param stock the stock
   */
  public void addStock(Stock stock) {
    boolean exists = false;
    for (Stock s : stocks) {
      if (s.getDateTime().equals(stock.getDateTime()) && s.getTicker().equals(stock.getTicker())) {
        exists = true;
        int totalShares = s.getShares() + stock.getShares();
        s.setShares(totalShares);
      }
    }

    if (!exists) {
      this.stocks.add(stock);
    }
  }
}
