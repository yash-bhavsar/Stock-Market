package model;

/**
 * The type Stock market controller.
 */
public class StockMarketModelImpl implements IStockMarketModel {

  private User user;

  /**
   * Instantiates a new Stock market controller.
   */
  public StockMarketModelImpl() {
    this.user = new User();
  }

  @Override
  public void buyStock(String ticker, int numberOfStocks, String date, int portfolioNumber) {
    if (numberOfStocks < 0) {
      throw new IllegalArgumentException("\nNumber of stocks cannot be negative.\n");
    }
    Services s = Services.getInstance();
    Stock stock;
    try {
      stock = s.getDataForCompany(ticker, numberOfStocks, date);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
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

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
}
