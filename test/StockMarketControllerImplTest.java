import org.junit.Test;

import controller.IStockMarketController;
import controller.StockMarketControllerImpl;

import static org.junit.Assert.assertEquals;

public class StockMarketControllerImplTest {

  IStockMarketController s;

  @Test
  public void test1() {
    s = new StockMarketControllerImpl();

    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2017-10-22", 2);
  }

  /**
   * Test to check if buy stock method works as expected.
   */
  @Test
  public void testBuyStock() {
    s = new StockMarketControllerImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 1);
    assertEquals("Company name: GOOG\n" +
            "Date of purchase: 2014-10-22\n" +
            "Purchase price: 528.8\n" +
            "Number of shares: 2\n", s.viewComposition(1));
  }

  /**
   * Test to check if evaluate method works as expected.
   */
  @Test
  public void testEvaluate() {
    s = new StockMarketControllerImpl();
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
    s = new StockMarketControllerImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 1);
    assertEquals(1057.5999755859375, s.calculateCostBasis(1, "2014-10-22"), 0.1);
  }

  /**
   * Test to see if buying a stock in a non existent portfolio throws IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void checkForNonExistentPortfolio() {
    s = new StockMarketControllerImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 2);
  }

}