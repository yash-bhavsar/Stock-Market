import org.junit.Test;

import java.io.StringReader;

import view.IStockMarketView;
import view.StockMarketViewImpl;

import static org.junit.Assert.*;

public class StockMarketViewImplTest {

  IStockMarketView sv;
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

}