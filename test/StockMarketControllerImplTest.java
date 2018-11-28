import org.junit.Test;

import java.io.StringReader;

import controller.IStockMarketController;
import controller.StockMarketControllerImpl;
import model.IStockMarketModel;
import model.StockMarketModelImpl;
import view.IStockMarketView;
import view.StockMarketViewImpl;

import static org.junit.Assert.assertEquals;

public class StockMarketControllerImplTest {

  IStockMarketController s;
  IStockMarketView sv;
  IStockMarketModel sm;
  Readable rd;
  Appendable ap;

  /**
   * Test to see if passing null in model or view throws IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testModelViewNull() {
    rd = new StringReader("1 1 AAPL 2 2016-10-21 1");
    ap = new StringBuilder();
    sv = new StockMarketViewImpl(rd, ap);
    sm = new StockMarketModelImpl();
    s = new StockMarketControllerImpl(null, sv);
    s = new StockMarketControllerImpl(sm, null);
  }

  /**
   * Test to check controller works as expected.
   */
  @Test
  public void testController() {
    rd = new StringReader("1 1 2 AAPL 2 2016-10-21 1 5 5 1 2018-11-15");
    ap = new StringBuffer();
    sv = new StockMarketViewImpl(rd, ap);
    sm = new StockMarketModelImpl();
    s = new StockMarketControllerImpl(sm, sv);
    s.startStockMarketSimulator();
    assertEquals("\n" +
            "Enter Choice:\n" +
            " 1. Create new portfolio.\n" +
            " 2. Buy Stock.\n" +
            " 3. Invest\n" +
            " 4. Create Strategy\n" +
            " 5. View Composition of a portfolio.\n" +
            " 6. View total cost basis and evaluation of a portfolio on a particular date.\n" +
            " 7. Quit.\n" +
            "\n" +
            "Enter portfolio number: \n" +
            "Enter Choice:\n" +
            " 1. Create new portfolio.\n" +
            " 2. Buy Stock.\n" +
            " 3. Invest\n" +
            " 4. Create Strategy\n" +
            " 5. View Composition of a portfolio.\n" +
            " 6. View total cost basis and evaluation of a portfolio on a particular date.\n" +
            " 7. Quit.\n" +
            "\n" +
            "Enter Stock details: \n" +
            "Stock symbol(Ticker): \n" +
            "The number of shares you want to buy: \n" +
            "Please enter date(yyyy-mm-dd): \n" +
            "Enter portfolio number: \n" +
            "Enter commission amount: \n" +
            "Stock bought successfully\n" +
            "\n" +
            "Enter Choice:\n" +
            " 1. Create new portfolio.\n" +
            " 2. Buy Stock.\n" +
            " 3. Invest\n" +
            " 4. Create Strategy\n" +
            " 5. View Composition of a portfolio.\n" +
            " 6. View total cost basis and evaluation of a portfolio on a particular date.\n" +
            " 7. Quit.\n" +
            "\n" +
            "Enter portfolio number: \n" +
            "Please enter date(yyyy-mm-dd): \n" +
            "Ticker name: AAPL\n" +
            "Date of purchase: 2016-10-21\n" +
            "Purchase price: $116.6\n" +
            "Number of shares: 2.0\n" +
            "\n" +
            "Enter Choice:\n" +
            " 1. Create new portfolio.\n" +
            " 2. Buy Stock.\n" +
            " 3. Invest\n" +
            " 4. Create Strategy\n" +
            " 5. View Composition of a portfolio.\n" +
            " 6. View total cost basis and evaluation of a portfolio on a particular date.\n" +
            " 7. Quit.\n", ap.toString());
  }
}