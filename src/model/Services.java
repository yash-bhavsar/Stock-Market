package model;

/**
 * The interface Services which has methods like getDataForCompany and getValueForCompany.
 */
public interface Services {

  /**
   * Gets data for company.
   *
   * @param stockSymbol the stock symbol
   * @param shares      the shares
   * @param date        the date
   * @param commission  the commission
   * @return the data for company
   */
  Stock getDataForCompany(String stockSymbol, double shares, String date, double commission);

  /**
   * Gets value for company.
   *
   * @param date   the date
   * @param ticker the ticker
   * @return the value for company
   */
  double getValueForCompany(String date, String ticker);
}
