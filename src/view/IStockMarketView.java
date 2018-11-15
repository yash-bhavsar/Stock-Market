package view;

import java.io.IOException;

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
}
