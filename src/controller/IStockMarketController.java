package controller;

import java.text.ParseException;
import java.util.List;

import model.Stock;
import view.IStockMarketView;

/**
 * The interface Stock market controller which has the method to start the stock market simulator.
 */
public interface IStockMarketController {

  /**
   * Method to start stock market simulator.
   */
  void startStockMarketSimulator();

  /**
   *
   * @param sv
   */
  void setView(IStockMarketView sv);

  /**
   *
   * @param portfolioNumber
   * @return
   */
  String createPortfolio(String portfolioNumber);

  /**
   * M
   *
   * @param ticker
   * @param numberOfStocks
   * @param date
   * @param portfolioNumber
   * @param commission
   * @return
   */
  String buyStock(String ticker, double numberOfStocks, String date, int portfolioNumber,
                  int commission);

  /**
   *
   * @param portfolioNumber
   * @param date
   * @return
   */
  List<Stock> viewComposition(int portfolioNumber, String date);

  /**
   *
   * @param portfolioNumber
   * @param date
   * @return
   */
  String costBasisAndEvaluation(int portfolioNumber, String date);

  /**
   *
   * @param ticker
   * @param amount
   * @param date
   * @param portfolioNumber
   * @param commission
   * @return
   */
  String buyStockByAmount(String ticker, double amount, String date, int portfolioNumber, double commission);

  /**
   *
   * @param ticker
   * @param investmentAmount
   * @param startDate
   * @param endDate
   * @param portfolioNumber
   * @param frequency
   * @return
   */
  String executeStrategy(String ticker, double investmentAmount, String startDate,
                         String endDate, int portfolioNumber,
                         int frequency) throws ParseException;
}
