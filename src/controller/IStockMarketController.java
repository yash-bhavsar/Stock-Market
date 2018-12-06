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
   * Sets the view.
   *
   * @param sv is the view object.
   */
  void setView(IStockMarketView sv);

  /**
   * Creates portfolio.
   *
   * @param portfolioNumber is the portfoilio number.
   * @return the portfolio number.
   */
  String createPortfolio(String portfolioNumber);

  /**
   * Used to buy stock.
   *
   * @param ticker          the ticker
   * @param numberOfStocks  the number of stocks
   * @param date            the date
   * @param portfolioNumber the portfolio number
   * @param commission      the commission
   * @return string
   */
  String buyStock(String ticker, double numberOfStocks, String date, int portfolioNumber,
                  int commission);

  /**
   * View composition list.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return list
   */
  List<Stock> viewComposition(int portfolioNumber, String date);

  /**
   * Cost basis and evaluation string.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return string
   */
  String costBasisAndEvaluation(int portfolioNumber, String date);

  /**
   * Buy stock by amount.
   *
   * @param ticker          the ticker
   * @param amount          the amount
   * @param date            the date
   * @param portfolioNumber the portfolio number
   * @param commission      the commission
   * @return string
   */
  String buyStockByAmount(String ticker, double amount, String date, int portfolioNumber, double commission);

  /**
   * Execute strategy string.
   *
   * @param ticker           the ticker
   * @param investmentAmount the investment amount
   * @param startDate        the start date
   * @param endDate          the end date
   * @param portfolioNumber  the portfolio number
   * @param frequency        the frequency
   * @return string
   * @throws ParseException the parse exception
   */
  String executeStrategy(String ticker, double investmentAmount, String startDate,
                         String endDate, int portfolioNumber,
                         int frequency) throws ParseException;

  /**
   * Save portfolio string.
   *
   * @param portfolioNumber the portfolio number
   * @return string
   */
  String savePortfolio(int portfolioNumber);

  /**
   * Save strategy string.
   *
   * @param strategyNumber the strategy number
   * @param amount         the amount
   * @param sdate          the sdate
   * @param edate          the edate
   * @param frequency      the frequency
   * @return string
   */
  String saveStrategy(String strategyNumber, double amount, String sdate, String edate, int frequency);
}
