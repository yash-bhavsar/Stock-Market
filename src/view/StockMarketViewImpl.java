package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * The class StockMarketView which has methods to get the input from the user. These include
 * helper methods like askDate, askNumberOfShares etc.
 */
public class StockMarketViewImpl implements IStockMarketView {
  private Readable rd;
  private Appendable out;

  /**
   * Instantiates a new Stock market view.
   *
   * @param rd  is the readable object.
   * @param out is the appendable object.
   */
  public StockMarketViewImpl(Readable rd, Appendable out) {
    this.rd = rd;
    this.out = out;
  }

  /**
   * Enter command which is a string.
   *
   * @return returns the string.
   * @throws IOException if the input is invalid.
   */
  @Override
  public String enterCommand() throws IOException {
    String choice;
    StringBuilder input = new StringBuilder();
    Scanner scanner = new Scanner(rd);
    this.out.append(getQuestion());
    choice = scanner.next();
    switch (choice) {
      case "1":
        input.append("1 ");
        input.append(" ").append(askPortfolioNumber());
        break;
      case "2":
        input.append("2 ");
        this.out.append("\nEnter Stock details: \nStock symbol(Ticker): ");
        input.append(" ").append(scanner.next());
        input.append(" ").append(askNumberOfShares());
        input.append(" ").append(askDate());
        input.append(" ").append(askPortfolioNumber());
        break;
      case "3":
        input.append("3 ");
        input.append(" ").append(askPortfolioNumber());
        break;
      case "4":
        input.append("4 ");
        input.append(" ").append(askPortfolioNumber());
        input.append(" ").append(askDate());
        break;
      case "5":
        input.append("5 ");
        this.out.append("Quitting.....");
        try {
          TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        break;
    }
    return input.toString();
  }

  /**
   * Returns the result which is a string.
   *
   * @param result is the result which is appended.
   * @throws IOException if the input is invalid.
   */
  @Override
  public void result(String result) throws IOException {
    this.out.append(result);
  }

  /**
   * Private helper method to get get the questions to be asked to the user.
   * @return the string of questions separated by a newline.
   */
  private String getQuestion() {
    return "\nEnter Choice:" +
            "\n 1. Create new portfolio." +
            "\n 2. Buy Stock." +
            "\n 3. View Composition of a portfolio." +
            "\n 4. View total cost basis and evaluation of a portfolio on a particular date." +
            "\n 5. Quit.\n";
  }

  /**
   * Helper method to get the number of shares from the user.
   * @return returns the string.
   * @throws IOException if the input is invalid.
   */
  private String askNumberOfShares() throws IOException {
    this.out.append("\nThe number of shares you want to buy: ");
    Scanner sc = new Scanner(this.rd);
    String s = sc.next();
    try {
      int a = Integer.parseInt(s.trim());
      return s.trim();
    } catch (NumberFormatException e) {
      this.out.append("\nEnter valid number: ");
      return askNumberOfShares();
    }
  }

  /**
   * Helper method to take the portfolio number as an input from the user.
   * @return returns the portfolio number.
   * @throws IOException if the input is invalid.
   */
  private String askPortfolioNumber() throws IOException {
    this.out.append("\nEnter portfolio number: ");
    Scanner sc = new Scanner(this.rd);
    String s = sc.next();
    try {
      int a = Integer.parseInt(s.trim());
      return s.trim();
    } catch (NumberFormatException e) {
      this.out.append("\nEnter valid number: ");
      return askNumberOfShares();
    }
  }

  /**
   * Helper method to ask the user for the date.
   * @return returns the date which is a string.
   * @throws IOException if the input is invalid.
   */
  private String askDate() throws IOException {
    this.out.append("\nThe date at which you want to buy stock: ");
    Scanner sc = new Scanner(this.rd);
    String s = sc.next();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
    df.setLenient(false);
    try {
      Date date = df.parse(s.trim());
      return s.trim();
    } catch (ParseException e) {
      this.out.append("Enter valid date in format (yyyy-mm-dd): ");
      return askDate();
    }
  }
}
