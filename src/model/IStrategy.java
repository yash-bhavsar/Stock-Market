package model;

import java.text.ParseException;

/**
 * The interface Strategy.
 */
public interface IStrategy {

  /**
   * Invest in a particular portfolio.
   *
   * @param ticker           the ticker
   * @param investmentAmount the investment amount
   * @param startDate        the date
   * @param endDate          the date
   * @param portfolioNumber  the portfolio number
   * @param frequency        the frequency
   */
  void investmentStrategy(String ticker, double investmentAmount, String startDate, String endDate,
                          int portfolioNumber, int frequency) throws ParseException;
}
