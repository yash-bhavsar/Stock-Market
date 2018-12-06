package model;

import java.text.ParseException;

/**
 * The interface Strategy which has the method investmentStrategy to decide which stocks to invest
 * in.
 */
public interface IStrategy {

  /**
   * Invest in a particular portfolio.
   *
   * @param ticker           the ticker
   * @param investmentAmount the investment amount
   * @param startDate        the start date
   * @param endDate          the end date
   * @param portfolioNumber  the portfolio number
   * @param frequency        the frequency
   * @param model            the model
   * @param user             the user
   * @throws ParseException the parse exception
   */
  void investmentStrategy(String ticker, double investmentAmount, String startDate, String endDate,
                          int portfolioNumber, int frequency, IStockMarketModel model, User user);

}
