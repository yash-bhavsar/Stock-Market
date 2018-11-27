package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class model.Portfolio which has methods to get stocks, get stocks before a certain date, and
 * add stock. Intentionally kept package-private as this class is not outside of this package.
 */
class Portfolio {

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
            .filter(stock -> stock.getDateTime().compareTo(date) <= 0)
            .collect(Collectors.toList());
  }

  /**
   * Add stock to the portfolio. Intentionally kept package-private.
   *
   * @param stock the stock that is to be added to the portfolio.
   */
  void addStock(Stock stock) {
    boolean exists = false;
    for (Stock s : this.stocks) {
      if (s.getDateTime().equals(stock.getDateTime()) && s.getTicker().equals(stock.getTicker())) {
        exists = true;
        double totalShares = s.getShares() + stock.getShares();
        this.stocks.remove(s);
        this.stocks.add(new Stock(stock.getTicker(), totalShares, stock.getPurchasePrice(),
                stock.getDateTime(), stock.getCurrentPrice(),stock.getCommission()));
        break;
      }
    }
    if (!exists) {
      this.stocks.add(stock);
    }
  }
}
