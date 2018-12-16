package model;

import com.opencsv.CSVWriter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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
    this.strategyList = new HashSet<>();
  }

  /**
   * Method to get portfolio s.
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
   * Method which is used to save a portfolio.
   *
   * @param portfolioNumber is the portfolio number.
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
   *
   * @param strategyNumber   is the name of the strategy the user wishes to save.
   * @param investmentAmount is the investment amount.
   * @param startDate        is the strategy start date.
   * @param endDate          is the strategy end date.
   * @param frequency        is the frequency for the strategy.
   */
  public void saveStrategy(String strategyNumber, double investmentAmount, String startDate,
                           String endDate, int frequency) {
    if (strategyList.contains(strategyNumber)) {
      throw new IllegalArgumentException("Strategy number already exists.");
    }
    strategyList.add(strategyNumber);
    String[] strategyArray = new String[5];
    strategyArray[0] = strategyNumber;
    strategyArray[1] = Double.toString(investmentAmount);
    strategyArray[2] = startDate;
    strategyArray[3] = endDate;
    strategyArray[4] = Integer.toString(frequency);
    try {
      File file = new File("../src/strategies/strategy_" + strategyNumber + ".csv");
      FileWriter output = new FileWriter(file);
      CSVWriter writer = new CSVWriter(output);
      writer.writeNext(strategyArray);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Add Strategy in the map so it initiates the map on start. Intentionally public because called
   * outside this class also.
   *
   * @param strategyNumber number of strategy to uniquely identify strategy.
   */
  public void addStrategy(String strategyNumber) {
    if (strategyList.contains(strategyNumber)) {
      throw new IllegalArgumentException("Strategy number already exists.");
    }
    strategyList.add(strategyNumber);
  }

  /**
   * Method to get the strategy details.
   *
   * @param strategyNumber is the strategy number.
   * @return the array of strategy details.
   */
  public String[] strategyDetails(String strategyNumber) {
    if (!strategyList.contains(strategyNumber)) {
      throw new IllegalArgumentException("Create strategy first.");
    }
    String path = "./src/strategies/";
    String[] details = new String[4];
    File f = new File(path);
    File[] listOfFiles = f.listFiles();
    if (listOfFiles.length != 0) {
      File[] files = new File(path).listFiles(new FileFilter() {
        @Override
        public boolean accept(File path) {
          if (path.isFile()) {
            try (FileReader f = new FileReader(path)) {
              Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(f);
              for (CSVRecord r : records) {
                if (r.get(0).equals(strategyNumber)) {
                  details[0] = r.get(1);
                  details[1] = r.get(2);
                  details[2] = r.get(3);
                  details[3] = r.get(4);
                }
              }
            } catch (Exception e) {
              e.printStackTrace();
            }
            return true;
          }
          return false;
        }
      });
    }
    return details;
  }
}