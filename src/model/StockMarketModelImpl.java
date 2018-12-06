package model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

/**
 * The class StockMarketModelImpl which implements the interface IStockMarketModel. It has
 * overridden methods like buyStock, viewComposition, createPortfolio, evaluatePortfolio and
 * calculateCostBasis.
 */
public class StockMarketModelImpl implements IStockMarketModel<Stock> {

  private User user;

  /**
   * Constructor which initializes a new user.
   */
  public StockMarketModelImpl() {
    this.user = new User();
    loadStrategies();
    loadPortfolios();
  }

  private void loadStrategies() {
    String path = "./src/strategies/";
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
                try {
                  user.addStrategy(r.get(0));
                } catch (IllegalArgumentException ie) {
                  throw new IllegalArgumentException(ie.getMessage());
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
  }

  /**
   * Helper method to load all the portfolios.
   */
  private void loadPortfolios() {
    String path = "./src/portfolios/";
    File f = new File(path);
    File[] listOfFiles = f.listFiles();
    if (listOfFiles.length != 0) {
      File[] files = new File(path).listFiles(new FileFilter() {
        @Override
        public boolean accept(File path) {
          if (path.isFile()) {
            try (FileReader f = new FileReader(path)) {
              Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(f);
              int portfolioNumber = getPortfolioNumber(path.getPath());
              createPortfolio(portfolioNumber);
              getRecordDetails(records, portfolioNumber);
            } catch (Exception e) {
              e.printStackTrace();
            }
            return true;
          }
          return false;
        }
      });
    }
  }

  private void getRecordDetails(Iterable<CSVRecord> records, int portfolioNumber) {
    Iterator i = records.iterator();
    for (CSVRecord r : records) {
      String ticker = r.get(0);
      double numberOfStocks = Double.parseDouble(r.get(2));
      String date = r.get(3);
      double commission = Double.parseDouble(r.get(4));
      buyStock(ticker, numberOfStocks, date, portfolioNumber, commission);
    }
  }

  /**
   * Private helper method to get the portfolio number from the file name.
   *
   * @param path is the path of the file.
   * @return the number.
   */
  private int getPortfolioNumber(String path) {
    String portfolioNumber = path.replaceAll("[^0-9]", "");
    return Integer.parseInt(portfolioNumber);
  }

  /**
   * Method to buy stock.
   *
   * @param ticker          the ticker
   * @param numberOfStocks  the number of stocks
   * @param date            the date
   * @param portfolioNumber the portfolio number
   */
  @Override
  public void buyStock(String ticker, double numberOfStocks, String date,
                       int portfolioNumber, double commission) {
    if (numberOfStocks < 0) {
      throw new IllegalArgumentException("\nNumber of stocks cannot be negative.\n");
    }
    Services s = VantageService.getInstance();
    Stock stock;
    try {
      stock = s.getDataForCompany(ticker, numberOfStocks, date, commission);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    user.buyStock(portfolioNumber, stock);
  }

  /**
   * View composition of a portfolio.
   *
   * @param portfolioNumber the portfolio name
   * @return the composition of the portfolio.
   */
  @Override
  public List<Stock> viewComposition(int portfolioNumber, String date) {
    return user.viewComposition(portfolioNumber, date);
  }

  /**
   * Create portfolio.
   *
   * @param portfolioNumber the portfolio number
   */
  @Override
  public void createPortfolio(int portfolioNumber) {
    user.createPortfolio(portfolioNumber);
  }

  /**
   * Evaluate the portfolio.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return returns the evaluated value of the portfolio.
   */
  @Override
  public double evaluatePortfolio(int portfolioNumber, String date) {
    return user.evaluatePortfolio(portfolioNumber, date);
  }

  /**
   * Calculate cost basis.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return returns the cost basis.
   */
  @Override
  public double calculateCostBasis(int portfolioNumber, String date) {
    return user.calculateCostBasis(portfolioNumber, date);
  }

  /**
   * Invest in a particular portfolio.
   *
   * @param ticker           the ticker
   * @param investmentAmount the investment amount
   * @param date             the date
   * @param portfolioNumber  the portfolio number
   * @param commission       the commission
   */
  @Override
  public void invest(String ticker, double investmentAmount, String date,
                     int portfolioNumber, double commission) {
    Services vantageService = VantageService.getInstance();
    double value = vantageService.getValueForCompany(date, ticker);
    double numberOfShares = investmentAmount / value;
    buyStock(ticker, numberOfShares, date, portfolioNumber, 0);
  }

  /**
   * Used to save a portfolio to a csv file.
   */
  @Override
  public void save(int portfolioNumber) {
    user.save(portfolioNumber);
  }

  @Override
  public void dCassStrategy(String ticker, double investmentAmount, String startDate,
                            String endDate, int portfolioNumber,
                            int frequency, IStockMarketModel model) {
    IStrategy strategy = new DCAS();
    try {
      strategy.investmentStrategy(ticker, investmentAmount, startDate, endDate,
              portfolioNumber, frequency, model, this.user);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public void saveDcassStrategy(String strategyNumber, double investmentAmount, String startDate,
                                String endDate, int frequency) {
    try {
      user.saveStrategy(strategyNumber, investmentAmount, startDate, endDate, frequency);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public String[] strategyDetails(String strategyNumber) {
    try {
      return user.strategyDetails(strategyNumber);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}
