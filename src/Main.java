import java.io.InputStreamReader;

import controller.IStockMarketController;
import controller.StockMarketControllerImpl;
import model.StockMarketModelImpl;
import view.IStockMarketView;
import view.StockMarketViewImpl;
import view.WelcomeFrame;

/**
 * The main class which has a main method to test the controller.
 */
public class Main {

  /**
   * This is the main method which tests if the controller works.
   * @param args are the command line arguments.
   */
  public static void main(String[] args) {
    /*IStockMarketController sc = new StockMarketControllerImpl(new StockMarketModelImpl(),
            new StockMarketViewImpl(new InputStreamReader(System.in), System.out));

    sc.startStockMarketSimulator();*/

    StockMarketControllerImpl controller = new StockMarketControllerImpl(new StockMarketModelImpl());
    IStockMarketView view = new WelcomeFrame();
    controller.setView(view);
  }
}
