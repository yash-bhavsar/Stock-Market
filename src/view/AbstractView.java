package view;

import java.io.IOException;

/**
 * The type Abstract view.
 */
public abstract class AbstractView {

  /**
   * Private helper method to ask for end date.
   *
   * @param flag      the flag
   * @param startdate the startdate
   * @return the end date.
   * @throws IOException if input is invalid.
   */
  abstract String askEndDate(boolean flag, String startdate) throws IOException;

  /**
   * Private helper method which asks the user for number depending on the question.
   *
   * @param string is the number which the user wishes to enter.
   * @return the number, if it passes validations.
   * @throws IOException if input is invalid.
   */
  abstract String askNumber(String string) throws IOException;

  /**
   * Private helper method which asks the user for weights of each stock in the portfolio.
   *
   * @param string question as a String.
   * @return return the valid choice (either 1 or 2) as a String.
   * @throws IOException if append is not working.
   */
  abstract String askChoice(String string) throws IOException;

  /**
   * Private helper method which asks the user for weights of each stock in the portfolio.
   *
   * @param ticker the ticker
   * @return the weight entered by the user.
   * @throws IOException the io exception
   */
  abstract String askWeights(String ticker) throws IOException;

  /**
   * Helper method to ask the user for the date.
   *
   * @return returns the date which is a string.
   * @throws IOException if the input is invalid.
   */
  abstract String askDate() throws IOException;
}
