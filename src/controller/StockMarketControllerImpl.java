package controller;

import java.util.List;
import java.util.Scanner;

import model.Portfolio;
import model.Services;
import model.Stock;
import model.User;

public class StockMarketControllerImpl implements IStockMarketController {

  @Override
  public void buyStock(String ticker, int numberOfStocks, String date, int portfolioNumber) {
    Services s = Services.getInstance();
    Stock stock = s.getDataForCompany(ticker, numberOfStocks, date);
    User u = new User();
    List<Portfolio> portfolios = u.getPortfolios();
    if (portfolios.isEmpty()) {
      portfolios.add(portfolioNumber - 1, new Portfolio());
      Portfolio userPortfolio = portfolios.get(portfolioNumber - 1);
      userPortfolio.addStock(portfolioNumber - 1, stock);
    } else {
      Portfolio userPortfolio = portfolios.get(portfolioNumber - 1);
      userPortfolio.addStock(portfolioNumber - 1, stock);
    }
  }

  @Override
  public void viewComposition(String portfolioName) {

  }

  @Override
  public void createPortfolio(String portfolioName) {

  }

  public static void main(String[] args) {
    IStockMarketController s = new StockMarketControllerImpl();
    s.buyStock("AAPL", 2, "2016-11-22", 1);
  }
}
