package model;

/**
 * The interface Strategy.
 */
public interface IStrategy {

  /**
   * Invest in a particular portfolio.
   *
   * @param ticker           the ticker
   * @param investmentAmount the investment amount
   * @param date             the date
   * @param portfolioNumber  the portfolio number
   * @param commission       the commission
   */
  void investmentStrategy(String ticker, double investmentAmount, String date, int portfolioNumber,
              double commission);
}
