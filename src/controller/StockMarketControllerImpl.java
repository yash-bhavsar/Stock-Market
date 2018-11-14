package controller;

import java.net.Inet4Address;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Portfolio;
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
  public int evaluatePortfolio(int portfolioNumber, String date) {
    return user.evaluatePortfolio(portfolioNumber, date);
  }

  public static void main(String[] args) {
    IStockMarketController s = new StockMarketControllerImpl();
    s.buyStock("AAPL", 2, "2016-11-22", 1);
  }
}
