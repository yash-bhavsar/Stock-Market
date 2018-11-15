import java.io.InputStreamReader;

import controller.IStockMarketController;
import controller.StockMarketControllerImpl;
import model.StockMarketModelImpl;
import view.StockMarketViewImpl;

public class Main {

  public static void main(String[] args) {
    IStockMarketController sc = new StockMarketControllerImpl(new StockMarketModelImpl(),
            new StockMarketViewImpl(new InputStreamReader(System.in), System.out));

    sc.startStockMarketSimulator();
  }
}
