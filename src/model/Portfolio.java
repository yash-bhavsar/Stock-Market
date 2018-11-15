package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class model.Portfolio which has methods to get stocks, get stocks before a certain date, and
 * add stock.
 */
public class Portfolio {

  private List<Stock> stocks;

  /**
   * Instantiates a new Portfolio that has scope package private.
   */
  Portfolio() {
    this.stocks = new ArrayList<>();
  }

  /**
   * Gets the stocks in a particular portfolio and this method's scope has been intentionally kept
   * package-private.
   *
   * @return the portfolio
   */
  List<Stock> getStocks() {
    return this.stocks;
  }

  /**
   * Gets stocks before date passed as argument. Scope has been intentionally kept package private.
   *
   * @param date the date upto which stock records are needed.
   * @return the stocks before the given date.
   */
  List<Stock> getStocksBeforeDate(String date) {
    return this.stocks
            .stream()
            .filter(stock -> stock.getDateTime().compareTo(date) < 1)
            .collect(Collectors.toList());
  }

  /**
   * Add stock to the portfolio.
   *
   * @param stock the stock that is to be added to the portfolio.
   */
  void addStock(Stock stock) {
    boolean exists = false;
    for (Stock s : stocks) {
      if (s.getDateTime().equals(stock.getDateTime()) && s.getTicker().equals(stock.getTicker())) {
        exists = true;
        int totalShares = s.getShares() + stock.getShares();
        stocks.remove(s);
        stocks.add(new Stock(stock.getTicker(), totalShares, stock.getPurchasePrice(),
                stock.getDateTime(), stock.getCurrentPrice()));
      }
    }
    if (!exists) {
      this.stocks.add(stock);
    }
  }
}
