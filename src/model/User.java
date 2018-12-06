package model;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The class User which has methods to buy stock, create portfolio, evaluate portfolio, calculate
 * cost basis and view composition of the portfolio.
 */
public class User {

  private Map<Integer, Portfolio> portfolios;
  private Set<String> strategyList;

  /**
   * Instantiates a new User and the portfolios hash map.
   */
  public User() {
    this.portfolios = new HashMap<>();
  }

  /**
   * Method to get portfolios.
   *
   * @return the portfolios
   */
  public Map<Integer, Portfolio> getPortfolios() {
    return this.portfolios;
  }

  /**
   * Method to create portfolio.
   *
   * @param portfolioNumber the portfolio number
   */
  public void createPortfolio(int portfolioNumber) {
    if (portfolioNumber < 0) {
      throw new IllegalArgumentException("\nInvalid number entered.\n");
    } else if (this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("\nPortfolio number already exists\n");
    }
    Portfolio portfolio = new Portfolio();
    this.portfolios.put(portfolioNumber, portfolio);
  }

  /**
   * Method to buy stock.
   *
   * @param portfolioNumber the portfolio number
   * @param stock           the stock
   */
  public void buyStock(int portfolioNumber, Stock stock) {
    if (!this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("\nCreate portfolio first.\n");
    } else {
      Portfolio userPortfolio = this.portfolios.get(portfolioNumber);
      userPortfolio.addStock(stock);
    }
  }

  /**
   *
   * @param portfolioNumber
   */
  public void save(int portfolioNumber) {
    if (!this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("\nCreate portfolio first.\n");
    } else {
      try {
        Portfolio userPortfolio = this.portfolios.get(portfolioNumber);
        userPortfolio.save(portfolioNumber);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
  }

  /**
   * Method to view the composition.
   *
   * @param portfolioNumber the portfolio number
   * @return the composition of the portfolio
   */
  public List<Stock> viewComposition(int portfolioNumber, String date) {
    checkPortfolioNumber(portfolioNumber);
    Portfolio portfolio = this.portfolios.get(portfolioNumber);
    return portfolio.getStocksBeforeDate(date);
  }

  /**
   * Method to calculate cost basis.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return the cost basis.
   */
  public double calculateCostBasis(int portfolioNumber, String date) {
    checkPortfolioNumber(portfolioNumber);
    return this.portfolios.get(portfolioNumber).getStocksBeforeDate(date)
            .stream()
            .mapToDouble(Stock::calculateCostBasis)
            .sum();
  }

  /**
   * Method to evaluate portfolio.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return the evaluated portfolio.
   */
  public double evaluatePortfolio(int portfolioNumber, String date) {
    checkPortfolioNumber(portfolioNumber);
    Services vantageService = VantageService.getInstance();
    return this.portfolios.get(portfolioNumber).getStocksBeforeDate(date)
            .stream()
            .mapToDouble(
                    stock -> stock.evaluate(vantageService.getValueForCompany(date,
                            stock.getTicker()))
            ).sum();
  }

  /**
   * Helper method to check portfolio number.
   *
   * @param portfolioNumber is the portfolio number.
   */
  private void checkPortfolioNumber(int portfolioNumber) {
    if (portfolioNumber < 0) {
      throw new IllegalArgumentException("\nInvalid number entered.\n");
    } else if (!this.portfolios.containsKey(portfolioNumber)) {
      throw new IllegalArgumentException("\nCreate portfolio first.\n");
    }
  }

  /**
   * Method to save strategy to a csv file. Saves the strategy in the format investment amount,
   * start date, end date, frequency.
   * @param strategyNumber is the name of the strategy the user wishes to save.
   * @param investmentAmount is the investment amount.
   * @param startDate is the strategy start date.
   * @param endDate is the strategy end date.
   * @param frequency is the frequency for the strategy.
   */
  public void saveStrategy(String strategyNumber, double investmentAmount, String startDate,
                           String endDate, int frequency) {
    if (strategyList.contains(strategyNumber)) {
      throw new IllegalArgumentException("Strategy already exists.");
    }
    strategyList.add(strategyNumber);
    String[] strategyArray = new String[4];
    strategyArray[0] = Double.toString(investmentAmount);
    strategyArray[1] = startDate;
    strategyArray[2] = endDate;
    strategyArray[3] = Integer.toString(frequency);
    try {
      File file = new File("./src/strategies/strategy_" + strategyNumber + ".csv");
      FileWriter output = new FileWriter(file);
      CSVWriter writer = new CSVWriter(output);
      writer.writeNext(strategyArray);
      writer.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
