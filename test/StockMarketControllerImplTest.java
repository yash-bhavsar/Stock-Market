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
    rd = new StringReader("1 1 2 AAPL 2 2016-10-21 1 3 1 4 1 2018-11-15");
    ap = new StringBuffer();
    sv = new StockMarketViewImpl(rd, ap);
    sm = new StockMarketModelImpl();
    s = new StockMarketControllerImpl(sm, sv);
    s.startStockMarketSimulator();
    assertEquals("\n" +
            "Enter Choice:\n" +
            " 1. Create new portfolio.\n" +
            " 2. Buy Stock.\n" +
            " 3. View Composition of a portfolio.\n" +
            " 4. View total cost basis and evaluation of a portfolio on a particular date.\n" +
            " 5. Quit.\n" +
            "\n" +
            "Enter portfolio number: \n" +
            "Enter Choice:\n" +
            " 1. Create new portfolio.\n" +
            " 2. Buy Stock.\n" +
            " 3. View Composition of a portfolio.\n" +
            " 4. View total cost basis and evaluation of a portfolio on a particular date.\n" +
            " 5. Quit.\n" +
            "\n" +
            "Enter Stock details: \n" +
            "Stock symbol(Ticker): \n" +
            "The number of shares you want to buy: \n" +
            "Date(yyyy-mm-dd) at which you want to buy stock: \n" +
            "Enter portfolio number: \n" +
            "Enter Choice:\n" +
            " 1. Create new portfolio.\n" +
            " 2. Buy Stock.\n" +
            " 3. View Composition of a portfolio.\n" +
            " 4. View total cost basis and evaluation of a portfolio on a particular date.\n" +
            " 5. Quit.\n" +
            "\n" +
            "Enter portfolio number: Company name: AAPL\n" +
            "Date of purchase: 2016-10-21\n" +
            "Purchase price: 116.28\n" +
            "Number of shares: 2\n" +
            "\n" +
            "Enter Choice:\n" +
            " 1. Create new portfolio.\n" +
            " 2. Buy Stock.\n" +
            " 3. View Composition of a portfolio.\n" +
            " 4. View total cost basis and evaluation of a portfolio on a particular date.\n" +
            " 5. Quit.\n" +
            "\n" +
            "Enter portfolio number: \n" +
            "Date(yyyy-mm-dd) at which you want to buy stock: Total basis: 232.55999755859375\n" +
            "Total Evaluation: 383.94\n" +
            "Enter Choice:\n" +
            " 1. Create new portfolio.\n" +
            " 2. Buy Stock.\n" +
            " 3. View Composition of a portfolio.\n" +
            " 4. View total cost basis and evaluation of a portfolio on a particular date.\n" +
            " 5. Quit.\n", ap.toString());
  }
}