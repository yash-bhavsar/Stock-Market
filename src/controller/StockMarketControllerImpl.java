package controller;

import java.io.IOException;

import model.IStockMarketModel;
import view.IStockMarketView;

public class StockMarketControllerImpl implements IStockMarketController {

  private IStockMarketView iv;
  private IStockMarketModel im;


  public StockMarketControllerImpl(IStockMarketModel im, IStockMarketView iv) {
    this.im = im;
    this.iv = iv;
  }

  @Override
  public void startStockMarketSimulator() {
    try {
      while (true) {
        String input = this.iv.enterCommand();
        String[] inputs = input.trim().split("\\s+");
        String result = "";
        switch (inputs[0]) {
          case "1":
            this.im.createPortfolio(Integer.parseInt(inputs[1].trim()));
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
                      ,inputs[2]) + "\nTotal Evaluation: " + this.im.evaluatePortfolio(
                              Integer.parseInt(inputs[1]), inputs[2]);
            } catch (Exception e) {
              result = e.getMessage();
            }
            break;
          case "5":
            System.exit(0);
            break;
        }
        this.iv.result(result);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
