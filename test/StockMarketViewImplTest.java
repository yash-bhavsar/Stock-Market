import org.junit.Test;

import java.io.StringReader;

import controller.IStockMarketController;
import view.IStockMarketView;
import view.StockMarketViewImpl;

import static org.junit.Assert.*;

public class StockMarketViewImplTest {

  IStockMarketView sv;
  IStockMarketController sc;
  IStockMarketView sm;
  Readable rd;
  Appendable ap;

  /**
   * Test to check if passing null in readable and appendable throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testReadableAppendableNull() {
    rd = new StringReader("1 1 2 AAPL 2 2018-11-15 1 3 1 4 1 2018-11-15");
    ap = new StringBuilder();
    sv = new StockMarketViewImpl(null, ap);
    IStockMarketView sv1 = new StockMarketViewImpl(rd, null);
    IStockMarketView sv2 = new StockMarketViewImpl(null, null);
  }

  /**
   * Test to check if entering invalid input throws IllegalArgumentException.
   */
  @Test
  public void testInvalidInput() {
    try {
      rd = new StringReader("6 -1 1 2 AAPL 1 2018-05-15 1 3 1 5");
      ap = new StringBuffer();
      sv = new StockMarketViewImpl(rd, ap);
      sv.enterCommand();
      assertEquals("\n" +
              "Enter Choice:\n" +
              " 1. Create new portfolio.\n" +
              " 2. Buy Stock.\n" +
              " 3. View Composition of a portfolio.\n" +
              " 4. View total cost basis and evaluation of a portfolio on a particular date.\n" +
              " 5. Quit.\n" +
              "\n" +
              "\t\t\t\t\tEnter valid choice. JAI HIND!!!\n", ap.toString());
    } catch (Exception e) {
    }
  }

  /**
   * Test to check if passing an invalid input portfolio number gives an error.
   */
  @Test
  public void testPortfolioNumber() {
    try {
      rd = new StringReader("1 -1 1 2 AAPL 1 2018-05-15 1 3 1 5");
      ap = new StringBuffer();
      sv = new StockMarketViewImpl(rd, ap);
      sv.enterCommand();
      assertEquals("\n" +
              "Enter Choice:\n" +
              " 1. Create new portfolio.\n" +
              " 2. Buy Stock.\n" +
              " 3. View Composition of a portfolio.\n" +
              " 4. View total cost basis and evaluation of a portfolio on a particular date.\n" +
              " 5. Quit.\n" +
              "\n" +
              "Enter portfolio number: \n" +
              "Enter valid number.\n" +
              " \n" +
              "Enter portfolio number: ", ap.toString());
    } catch (Exception e) {}
  }

  /**
   * Test to see if negative shares gives an error.
   */
  @Test
  public void testNegativeShares() {
    try {
      rd = new StringReader("2 AAPL -1 1 2018-05-15 1");
      ap = new StringBuffer();
      sv = new StockMarketViewImpl(rd, ap);
      sv.enterCommand();
      assertEquals("\n" +
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
              "Enter valid number.\n" +
              " \n" +
              "The number of shares you want to buy: \n" +
              "The date at which you want to buy stock: \n" +
              "Enter portfolio number: ", ap.toString());
    } catch (Exception e) {}
  }

  /**
   * Test to see if entering future date works as expected.
   */
  @Test
  public void testFutureDate() {
    try {
      rd = new StringReader("2 AAPL 2 2018-11-26 2018-11-14 1 5");
      ap = new StringBuffer();
      sv = new StockMarketViewImpl(rd, ap);
      sv.enterCommand();
      assertEquals("\n" +
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
              "The date at which you want to buy stock: \n" +
              "Future dates are not valid.\n" +
              "\n" +
              "The date at which you want to buy stock: \n" +
              "Enter portfolio number: ", ap.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}