package view;

import java.io.IOException;
import java.util.List;

import controller.IStockMarketController;
import controller.StockMarketControllerImpl;

/**
 * The interface Stock market view which has methods like enterCommand which is used to read an
 * input from the user and result which is used to append the result.
 */
public interface IStockMarketView {

  /**
   * Enter command which is a string.
   *
   * @return returns the string.
   * @throws IOException if the input is invalid.
   */
  String enterCommand() throws IOException;

  /**
   * Returns the result which is a string.
   *
   * @param result is the result which is appended.
   * @throws IOException if the input is invalid.
   */
  void result(String result) throws IOException;

  /**
   * Method to continue taking weights.
   *
   * @param stockList the stock list
   * @return the weights for each stock as a string.
   * @throws IOException the io exception if input is invalid.
   */
  String continueTakingWeights(List<String> stockList) throws IOException;

  /**
   * Gets amount to be invested for equal weights.
   *
   * @return the equal weights amount.
   * @throws IOException the io exception
   */
  String getEqualWeightsAmount() throws IOException;

  /**
   * Sets features.
   */
  void setFeatures();
}
