package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * The class StockMarketView which has methods to get the input from the user. These include helper
 * methods like askDate, askNumberOfShares etc.
 */
public class StockMarketViewImpl implements IStockMarketView {

  private Appendable out;
  private Scanner scanner;
  private StringBuilder input;


  /**
   * Constructor which initializes the readable and appendable objects.
   *
   * @param rd  is the readable object.
   * @param out is the appendable object.
   */
  public StockMarketViewImpl(Readable rd, Appendable out) {
    if (rd == null || out == null) {
      throw new IllegalArgumentException("Readable or appendable cannot be null");
    }
    Readable rd1 = rd;
    this.out = out;
    scanner = new Scanner(rd1);
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
    input = new StringBuilder();

    this.out.append(getQuestion());
    try {
      choice = scanner.next();
    } catch (NoSuchElementException e) {
      return String.valueOf(Integer.MAX_VALUE);
    }
    switch (choice) {
      case "1":
        input.append("1 ");
        input.append(" ").append(askNumber("\nEnter portfolio number: "));
        break;
      case "2":
        input.append("2 ");
        this.out.append("\nEnter Stock details: \nStock symbol(Ticker): ");
        input.append(" ").append(scanner.next());
        input.append(" ").append(askNumber("\nThe number of shares you want to buy: "));
        input.append(" ").append(askDate());
        input.append(" ").append(askNumber("\nEnter portfolio number: "));
        input.append(" ").append(askNumber("\nEnter commission amount (%): "));
        break;
      case "3":
        input.append("3 ");
        this.out.append("\nEnter investment details. \n");
        input.append(" ").append(askNumber("\nEnter portfolio number: "));
        input.append(" ").append(askDate());
        input.append(" ").append(askChoice("\nSelect weights: \n1.Equal weights " +
                "\n2.Custom weights\n"));
        break;
      case "4":
        input.append("4 ");
        this.out.append("\nEnter strategy details: \n");
        input.append(" ").append(askNumber("\nEnter portfolio number: "));
        this.out.append("\nEnter start date: \n");
        String startdate = askDate();
        input.append(" ").append(startdate);
        this.out.append("\nEnter end Date\n1. Yes\n2. No\n");
        String choice1 = askChoice("");
        if (choice1.equals("1")) {
          input.append(" ").append(askEndDate(true, startdate));
        } else {
          input.append(" ").append(askEndDate(false, startdate));
        }
        input.append(" ").append(askNumber("\nEnter Amount to be invested: $"));
        input.append(" ").append(askNumber("Please enter the frequency (in days) " +
                "for the strategy\n"));
        this.out.append("Would you like to save the strategy?\n1. Yes\n2. No\n");
        String choice2 = askChoice("");
        if (choice2.equals("1")) {
          input.append(" ").append(askNumber("\nEnter strategy number: "));
        } else {
          input.append(" ").append("no");
        }
        input.append(" ").append(askChoice("\nSelect weights: \n1.Equal weights " +
                "\n2.Custom weights\n"));
        break;
      case "5":
        input.append("5 ");
        input.append(" ").append(askNumber("\nEnter portfolio number: "));
        input.append(" ").append(askDate());
        break;
      case "6":
        input.append("6 ");
        input.append(" ").append(askNumber("\nEnter portfolio number: "));
        input.append(" ").append(askDate());
        break;
      case "7":
        input.append("7 ");
        input.append(" ").append(askNumber("\nEnter portfolio number: "));
        break;
      case "8":
        input.append("8 ");
        input.append(" ").append(askNumber("\nEnter strategy number"));
        input.append(" ").append(askNumber("\nEnter portfolio number"));
        input.append(" ").append(askChoice("\nSelect weights: \n1.Equal weights " +
                "\n2.Custom weights\n"));
        break;
      case "9":
        input.append("9 ");
        this.out.append("Quitting.....");
        try {
          TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        break;

      default:
        this.out.append("\n\t\t\t\t\t\tEnter valid choice.\n");
    }
    return input.toString();
  }

  @Override
  public String continueTakingWeights(List<String> stockList) throws IOException {
    input = new StringBuilder();
    for (String stock : stockList) {
      input.append(" ").append(askWeights(stock));
    }
    return input.toString();
  }

  @Override
  public String getEqualWeightsAmount() throws IOException {
    input = new StringBuilder();
    this.out.append("\nEnter the amount you want to invest: $");
    input.append(" ").append(scanner.next());
    return input.toString();
  }

  @Override
  public void setFeatures() {

  }

  /**
   * Private helper method to ask for end date.
   *
   * @param flag      the flag
   * @param startdate the startdate
   * @return the end date.
   * @throws IOException if input is invalid.
   */
  protected String askEndDate(boolean flag, String startdate) throws IOException {
    String endDate;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    if (flag) {
      this.out.append("\nPlease enter end date(yyyy-mm-dd): ");
      String st = scanner.next();
      df.setLenient(false);
      try {
        Date date = df.parse(st);
        Date sdate = df.parse(startdate);
        Date currentDate = new Date();
        if (date.after(currentDate)) {
          return df.format(currentDate);
        }
        if (sdate.after(date)) {
          this.out.append("\nEnd Date cannot be before start Date.\n ");
          return askEndDate(true, startdate);
        }
        return df.format(date);
      } catch (ParseException e) {
        this.out.append("\nEnter valid date in format (yyyy-mm-dd).\n ");
        return askEndDate(true, startdate);
      }
    }
    endDate = df.format(new Date());
    return endDate;
  }

  /**
   * Private helper method which asks the user for number depending on the question.
   *
   * @param string is the number which the user wishes to enter.
   * @return the number, if it passes validations.
   * @throws IOException if input is invalid.
   */
  protected String askNumber(String string) throws IOException {
    this.out.append(string);
    String s = scanner.next();
    try {
      int t = Integer.parseInt(s);
      if (t < 0) {
        this.out.append("\nEnter valid number.\n ");
        return askNumber(string);
      }
      return s.trim();
    } catch (NumberFormatException e) {
      this.out.append("\nEnter valid number: ");
      return askNumber(string);
    }
  }

  /**
   * Private helper method which asks the user for weights of each stock in the portfolio.
   *
   * @param string question as a String.
   * @return return the valid choice (either 1 or 2) as a String.
   * @throws IOException if append is not working.
   */
  protected String askChoice(String string) throws IOException {
    this.out.append(string);
    String s = scanner.next();
    try {
      int t = Integer.parseInt(s);
      if (t < 1 || t > 2) {
        this.out.append("\nEnter valid choice.\n ");
        return askChoice(string);
      }
      return s.trim();
    } catch (NumberFormatException e) {
      this.out.append("\nEnter valid choice: ");
      return askChoice(string);
    }
  }

  /**
   * Private helper method which asks the user for weights of each stock in the portfolio.
   *
   * @param ticker the ticker
   * @return the weight entered by the user.
   * @throws IOException the io exception
   */
  protected String askWeights(String ticker) throws IOException {
    this.out.append("\nEnter weight(%) for: " + ticker + "\n");
    String s = scanner.next();
    try {
      int t = Integer.parseInt(s);
      if (t < 0) {
        this.out.append("\nNegative weight not valid.\n ");
        return askWeights(ticker);
      }
      return s.trim();
    } catch (NumberFormatException e) {
      this.out.append("\nEnter valid number: ");
      return askWeights(ticker);
    }
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
   *
   * @return the string of questions separated by a newline.
   */
  private String getQuestion() {
    return "\nEnter Choice:" +
            "\n 1. Create new portfolio." +
            "\n 2. Buy Stock." +
            "\n 3. Invest" +
            "\n 4. Create Strategy" +
            "\n 5. View Composition of a portfolio." +
            "\n 6. View total cost basis and evaluation of a portfolio on a particular date." +
            "\n 7. Save Portfolio." +
            "\n 8. Apply Existing Strategy." +
            "\n 9. Quit.\n";
  }

  /**
   * Helper method to ask the user for the date.
   *
   * @return returns the date which is a string.
   * @throws IOException if the input is invalid.
   */
  protected String askDate() throws IOException {
    this.out.append("\nPlease enter date(yyyy-mm-dd): ");
    String s = scanner.next();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    df.setLenient(false);
    try {
      Date date = df.parse(s);
      Date currentDate = new Date();
      if (date.after(currentDate)) {
        this.out.append("\nFuture dates are not valid.\n");
        return askDate();
      }
      return df.format(date);
    } catch (ParseException e) {
      this.out.append("\nEnter valid date in format (yyyy-mm-dd).\n ");
      return askDate();
    }
  }

  /**
   * Gets out.
   *
   * @return the out
   */
  public Appendable getOut() {
    return this.out;
  }
}
