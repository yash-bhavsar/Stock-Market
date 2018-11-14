package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

import model.Services;
import model.Stock;
import model.User;

public class StockMarketControllerImpl implements IStockMarketController {

  private User user;

  public StockMarketControllerImpl() {
    this.user = new User();
  }

  @Override
  public void buyStock(String ticker, int numberOfStocks, String date, int portfolioNumber) {
    Services s = Services.getInstance();
    Stock stock = s.getDataForCompany(ticker, numberOfStocks, date);
    user.buyStock(portfolioNumber, stock);
  }

  @Override
  public String viewComposition(int portfolioNumber) {
    return user.viewComposition(portfolioNumber);
  }

  @Override
  public void createPortfolio(int portfolioNumber) {
    user.createPortfolio(portfolioNumber);
  }

  @Override
  public double evaluatePortfolio(int portfolioNumber, String date) {
    return user.evaluatePortfolio(portfolioNumber, date);
  }

  @Override
  public double calculateCostBasis(int portfolioNumber, String date) {
    return user.calculateCostBasis(portfolioNumber, date);
  }

  public static void main(String[] args) {
    IStockMarketController s = new StockMarketControllerImpl();
    s.createPortfolio(1);
    s.buyStock("AAPL", 2, "2016-11-22", 1);
    s.buyStock("AAPL", 2, "2016-10-12", 1);
    System.out.println(s.viewComposition(1));

//    System.out.println(s.evaluatePortfolio(1, "2016-11-22"));
//    System.out.println(s.viewComposition(1));
  }
}
