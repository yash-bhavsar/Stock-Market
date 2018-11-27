package controller;

import java.io.IOException;
import java.net.Inet4Address;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.IStockMarketModel;
import model.Stock;
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
        if (input.equals(String.valueOf(Integer.MAX_VALUE))) {
          break;
        }
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
                      Integer.parseInt(inputs[4]), Double.parseDouble(inputs[5]));
              result += "\nStock bought successfully\n";
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
            }
            break;
          case "3":
            if (inputs[3].equals("2")) {
              try {
                String date = inputs[2];
                int portfolioNumber = Integer.parseInt(inputs[1]);
                List<Stock> stockList = this.im.viewComposition(portfolioNumber, date);
                //Write helper method.
                if (stockList.size() == 0) {
                  result = "\n\nBuy stock first\n";
                  break;
                }
                String weights = this.iv.continueTakingWeights(stockList);
                int[] numbers = Arrays.stream(weights.trim().split("\\s+"))
                        .mapToInt(Integer::parseInt).toArray();
                int amount = numbers[numbers.length - 1];
                int [] weightsNumbers = Arrays.copyOf(numbers, numbers.length - 1);
                if (Arrays.stream(weightsNumbers).sum() != 100) {
                  result = "\nSum of weights should be 100\n";
                  break;
                }
                double[] numbers1 = Arrays.stream(weightsNumbers)
                        .mapToDouble(number -> number * 0.01 * amount).toArray();
                for (int i = 0; i < stockList.size(); i++) {
                  this.im.invest(stockList.get(i).getTicker(), numbers1[i], date, portfolioNumber, 0);
                }
              } catch (IllegalArgumentException e) {
                result = e.getMessage();
              }
            } else if (inputs[3].equals("1")) {
              try {
                String date = inputs[2];
                int portfolioNumber = Integer.parseInt(inputs[1]);
                List<Stock> stockList = this.im.viewComposition(portfolioNumber, date);
                //Write helper method.
                if (stockList.size() == 0) {
                  result = "\n\nBuy stock first\n";
                  break;
                }
                String amount = this.iv.getEqualWeightsAmount();
                double investmentAmount = Double.parseDouble(amount) / stockList.size();
                for (int i = 0; i < stockList.size(); i++) {
                  this.im.invest(stockList.get(i).getTicker(), investmentAmount, date, portfolioNumber, 0);
                }
              } catch (IllegalArgumentException e) {
                result = e.getMessage();
              }
            }
            break;
          case "4":
            try {
              String sdate = inputs[2];
              String edate = inputs[3];
              int portfolioNumber = Integer.parseInt(inputs[1]);
              int frequency = Integer.parseInt(inputs[4]);
              List<Stock> stockList = this.im.viewComposition(portfolioNumber, sdate);
              //Write helper method.
              if (stockList.size() == 0) {
                result = "\n\nBuy stock first\n";
                break;
              }
              String weights = this.iv.continueTakingWeights(stockList);
              int[] numbers = Arrays.stream(weights.trim().split("\\s+"))
                      .mapToInt(Integer::parseInt).toArray();
              int amount = numbers[numbers.length - 1];
              int [] weightsNumbers = Arrays.copyOf(numbers, numbers.length - 1);
              if (Arrays.stream(weightsNumbers).sum() != 100) {
                result = "\nSum of weights should be 100\n";
                break;
              }
              double[] numbers1 = Arrays.stream(weightsNumbers)
                      .mapToDouble(number -> number * 0.01 * amount).toArray();
              for (int i = 0; i < stockList.size(); i++) {
                this.im.DCassStrategy(stockList.get(i).getTicker(), numbers1[i], sdate, edate,
                        portfolioNumber, frequency);
              }
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
            }
            break;
          case "5":
            try {
              result = this.im.viewComposition(Integer.parseInt(inputs[1]), inputs[2]).toString();
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
            }
            break;
          case "6":
            try {
              result += "Total basis: " + this.im.calculateCostBasis(Integer.parseInt(inputs[1])
                      , inputs[2]) + "\nTotal Evaluation: " + this.im.evaluatePortfolio(
                      Integer.parseInt(inputs[1]), inputs[2]);
            } catch (Exception e) {
              result = e.getMessage();
            }
            break;
          case "7":
            System.exit(0);
            break;
          default:
            break;
        }
        this.iv.result(result);
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }
}
