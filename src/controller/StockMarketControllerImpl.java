package controller;

import java.io.IOException;

import model.IStockMarketModel;
import view.IStockMarketView;

/**
 * The class StockMarketController which has the overridden method startStockMarketSimulator to
 * start the simulation.
 */
public class StockMarketControllerImpl implements IStockMarketController {

  private IStockMarketView iv;
  private IStockMarketModel im;


  /**
   * Constructor which initializes stock market model and view objects.
   *
   * @param im IStockMarketModel object.
   * @param iv the IStockMarketView object.
   */
  public StockMarketControllerImpl(IStockMarketModel im, IStockMarketView iv) {
    if (im == null || iv == null) {
      throw new IllegalArgumentException("Model or View is null");
    }
    this.im = im;
    this.iv = iv;
  }

  /**
   * Method to start stock market simulator.
   */
  @Override
  public void startStockMarketSimulator() {
    try {
      while (true) {
        String input = this.iv.enterCommand();
        String[] inputs = input.trim().split("\\s+");
        String result = "";
        switch (inputs[0]) {
          case "1":
            try {
              this.im.createPortfolio(Integer.parseInt(inputs[1].trim()));
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
            }
            break;
          case "2":
            try {
              this.im.buyStock(inputs[1], Integer.parseInt(inputs[2]), inputs[3],
                      Integer.parseInt(inputs[4]));
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
            }
            break;
          case "3":
            try {
              result = this.im.viewComposition(Integer.parseInt(inputs[1]));
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
            }
            break;
          case "4":
            try {
              result += "Total basis: " + this.im.calculateCostBasis(Integer.parseInt(inputs[1])
                      , inputs[2]) + "\nTotal Evaluation: " + this.im.evaluatePortfolio(
                      Integer.parseInt(inputs[1]), inputs[2]);
            } catch (Exception e) {
              result = e.getMessage();
            }
            break;
          case "5":
            System.exit(0);
            break;
          default:
            break;
        }
        this.iv.result(result);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
