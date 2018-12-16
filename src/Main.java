import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

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
   *
   * @param args are the command line arguments.
   */
  public static void main(String[] args) {
    if (args.length == 2) {
      if (args[0].equals("-view")) {
        if (args[1].equals("console")) {
          IStockMarketController sc = new StockMarketControllerImpl(new StockMarketModelImpl());
          IStockMarketView sv = new StockMarketViewImpl(new InputStreamReader(System.in),
                  System.out);
          sc.setView(sv);
          sc.startStockMarketSimulator();
        } else if (args[1].equals("gui")) {
          IStockMarketController controller = new StockMarketControllerImpl(
                  new StockMarketModelImpl());
          IStockMarketView view = new WelcomeFrame(controller);
          controller.setView(view);
        } else {
          System.out.println("Invalid command line option.");
          System.out.println("Exiting...");
          try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("\nGoodbye.");
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
          System.exit(0);
        }
      } else {
        System.out.println("First argument should be -view");
        System.out.println("Exiting...");
        try {
          TimeUnit.SECONDS.sleep(3);
          System.out.println("\nGoodbye.");
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
        System.exit(0);
      }
    } else {
      System.out.println("2 Arguments required(-view console / -view gui)");
      System.out.println("Exiting...");
      try {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("\nGoodbye.");
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      System.exit(0);
    }
  }
}
