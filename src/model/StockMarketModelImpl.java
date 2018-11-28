package model;

import java.text.ParseException;
import java.util.List;

/**
 * The class StockMarketModelImpl which implements the interface IStockMarketModel. It has
 * overridden methods like buyStock, viewComposition, createPortfolio, evaluatePortfolio and
 * calculateCostBasis.
 */
public class StockMarketModelImpl implements IStockMarketModel<Stock> {

  private User user;

  /**
   * Constructor which initializes a new user.
   */
  public StockMarketModelImpl() {
    this.user = new User();
  }

  /**
   * Method to buy stock.
   *
   * @param ticker          the ticker
   * @param numberOfStocks  the number of stocks
   * @param date            the date
   * @param portfolioNumber the portfolio number
   */
  @Override
  public void buyStock(String ticker, double numberOfStocks, String date, int portfolioNumber, double commission) {
    if (numberOfStocks < 0) {
      throw new IllegalArgumentException("\nNumber of stocks cannot be negative.\n");
    }
    Services s = Services.getInstance();
    Stock stock;
    try {
      stock = s.getDataForCompany(ticker, numberOfStocks, date, commission);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    user.buyStock(portfolioNumber, stock);
  }

  /**
   * View composition of a portfolio.
   *
   * @param portfolioNumber the portfolio name
   * @return the composition of the portfolio.
   */
  @Override
  public List<Stock> viewComposition(int portfolioNumber, String date) {
    return user.viewComposition(portfolioNumber, date);
  }

  /**
   * Create portfolio.
   *
   * @param portfolioNumber the portfolio number
   */
  @Override
  public void createPortfolio(int portfolioNumber) {
    user.createPortfolio(portfolioNumber);
  }

  /**
   * Evaluate the portfolio.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return returns the evaluated value of the portfolio.
   */
  @Override
  public double evaluatePortfolio(int portfolioNumber, String date) {
    return user.evaluatePortfolio(portfolioNumber, date);
  }

  /**
   * Calculate cost basis.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return returns the cost basis.
   */
  @Override
  public double calculateCostBasis(int portfolioNumber, String date) {
    return user.calculateCostBasis(portfolioNumber, date);
  }

  /**
   * Invest in a particular portfolio.
   *
   * @param ticker           the ticker
   * @param investmentAmount the investment amount
   * @param date             the date
   * @param portfolioNumber  the portfolio number
   * @param commission       the commission
   */
  @Override
  public void invest(String ticker, double investmentAmount, String date, int portfolioNumber, double commission) {
    Services services = Services.getInstance();
    double value = services.getValueForCompany(date, ticker);
    double numberOfShares = investmentAmount / value;
    buyStock(ticker, numberOfShares, date, portfolioNumber, 0);
  }

  @Override
  public void DCassStrategy(String ticker, double investmentAmount, String startDate, String endDate, int portfolioNumber, int frequency, IStockMarketModel model) throws ParseException {
    IStrategy strategy = new DCAS();
    strategy.investmentStrategy(ticker, investmentAmount, startDate, endDate, portfolioNumber, frequency, model);
  }
}
