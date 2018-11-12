import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Portfolio.
 */
public class Portfolio {

  private HashMap<Integer, List<Stock>> stocks;

  private List<Stock> stockList;

  /**
   * Gets stocks.
   *
   * @return the stocks
   */
  public HashMap<Integer, List<Stock>> getStocks() {
    return stocks;
  }

  public Portfolio() {
    stockList = new ArrayList<Stock>();
    stocks = new HashMap<>();
  }

  /**
   * Helper method to add a stock to the portfolio.
   */
  private void addStock(Stock stock) {
    stockList.add(stock);
    stocks.put(0, stockList);
    System.out.println(stocks.get(0).get(0).getTicker());
  }

  /**
   * Helper method to get data for a company and add the stock to the portfolio. Make private after
   * testing.
   */
  public void getDataForCompany(String stockSymbol, int shares) {

    String[] stockArray;
    //the API key needed to use this web service.
    //Please get your own free API key here: https://www.alphavantage.co/
    //Please look at documentation here: https://www.alphavantage.co/documentation/
    String apiKey = "W0M1JOKC82EZEQA8";
    URL url = null;

    try {
      /*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       */
      url = new URL("https://www.alphavantage" + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full" + "&symbol" + "=" + stockSymbol
              + "&apikey="+apiKey+"&datatype=csv");
    }
    catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       */
      in = url.openStream();
      int b;

      while ((b=in.read())!=-1) {
        output.append((char)b);
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    String s = output.toString().substring(37);
    stockArray = s.split("\n");

    String[] temp = stockArray[1].split(",");

    Date currentDate = new Date();
    float open = Float.parseFloat(temp[1]);
    String high = temp[2];
    String low = temp[3];
    String close = temp[4];
    String volume = temp[5];
    Stock stock = new Stock(stockSymbol, shares, open, currentDate, open);
    addStock(stock);
    System.out.println(output);
  }
}
