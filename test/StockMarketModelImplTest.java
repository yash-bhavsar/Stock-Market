import org.junit.Test;

import model.IStockMarketModel;
import model.StockMarketModelImpl;

import static org.junit.Assert.assertEquals;

public class StockMarketModelImplTest {

  IStockMarketModel s;

  @Test
  public void test1() {
    s = new StockMarketModelImpl();

    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2016-10-20",1);
    s.buyStock("GOOG", 2, "2016-10-20",1);
    s.buyStock("GOOG", 2, "2016-10-21", 1);
    System.out.println(s.evaluatePortfolio(1, "2016-10-22"));
  }

  /**
   * Test to check if create portfolio works as expected.
   */
  @Test
  public void testCreatePortfolio() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2016-10-22", 1);
    assertEquals(1630.36, s.evaluatePortfolio(1, "2016-10-24"), 0.1);
  }

  /**
   * Test if create portfolio fails on passing a negative number.
   */
  @Test
  public void testCreatePortfolioFails() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("AAPL", 2, "2016-10-22", 1);
  }

  /**
   * Test to check if buy stock method works as expected.
   */
  @Test
  public void testBuyStock() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 1);
    assertEquals("Company name: GOOG\n" +
            "Date of purchase: 2014-10-22\n" +
            "Purchase price: 528.8\n" +
            "Number of shares: 2\n", s.viewComposition(1));
  }

  /**
   * Test to check if passing an invalid argument to buyStock throws an exception.
   */
  @Test
  public void buyStockFail() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
  }

  /**
   * Test to check if evaluate method works as expected.
   */
  @Test
  public void testEvaluate() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 1);
    s.buyStock("AAPL", 2, "2014-10-22", 1);
    assertEquals(1287.82, s.evaluatePortfolio(1, "2014-10-22"), 0.1);
  }

  /**
   * Test to check if calculate cost basis works as expected.
   */
  @Test
  public void testCostBasis() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 1);
    assertEquals(1057.5999755859375, s.calculateCostBasis(1, "2014-10-22"), 0.1);
  }

  /**
   * Test to see if buying a stock in a non existent portfolio throws IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void checkForNonExistentPortfolio() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 2);
  }

  /**
   * Test to check if creating multiple portfolios works as expected.
   */
  @Test
  public void createPortfolios() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.createPortfolio(2);
    s.buyStock("AAPL", 2, "2014-10-22", 2);
    s.buyStock("GOOG", 2, "2014-10-22", 1);
    assertEquals(1079.6, s.evaluatePortfolio(1, "2014-10-22"), 0.1);
    assertEquals(210.98, s.evaluatePortfolio(2, "2014-10-24"), 0.1);
  }

}