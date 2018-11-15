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

}