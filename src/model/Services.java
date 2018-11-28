package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * The class Services which has methods to get data for a particular ticker, check the nearest date
 * in case of holidays and get a value for a company.
 */
public class Services {

  private static Services instance = null;
  private Map<String, String> cachedData;

  private Services() {
    cachedData = new HashMap<>();
  }

  /**
   * Gets the service instance.
   *
   * @return the instance
   */
  public static Services getInstance() {
    if (instance == null) {
      instance = new Services();
      return instance;
    } else {
      return instance;
    }
  }

  /**
   * Helper method to get data for a company and add the stock to the portfolio. Make private after
   * testing.
   *
   * @param stockSymbol the stock symbol
   * @param shares      the shares
   * @param date        the date
   * @return the data for company
   */
  public Stock getDataForCompany(String stockSymbol, double shares, String date, double commission) {

    String[] stockArray;
    float close = 0;
    String currentDate;
    String finalDate = "";

    if (cachedData.containsKey(stockSymbol)) {
      String[] temp = cachedData.get(stockSymbol).split("\n");

      for (int i = 0; i < temp.length; i++) {
        String[] tempArray = temp[i].split(",");
        currentDate = tempArray[0];
        if (currentDate.equals(date)) {
          finalDate = currentDate;
          close = Float.parseFloat(tempArray[4]);
          return new Stock(stockSymbol, shares, close, finalDate, close, commission);
        }
      }
    }

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
              + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
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

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }

    if (output.toString().contains("Invalid")) {
      throw new IllegalArgumentException("\nStock symbol not valid.\n");
    }

    this.cachedData.put(stockSymbol, output.toString());
    stockArray = output.toString().split("\n");
    for (int i = 1; i < stockArray.length; i++) {
      String[] temp = stockArray[i].split(",");
      currentDate = temp[0];
      if (currentDate.equals(date)) {
        finalDate = currentDate;
        close = Float.parseFloat(temp[4]);
        break;
      }
    }

    if (close == 0.0) {
      throw new IllegalArgumentException("\nMarket is closed.\n");
    }
    return new Stock(stockSymbol, shares, close, finalDate, close, commission);
  }

  /**
   * Helper method to get nearest date if the date passed is a holiday.
   *
   * @return the nearest date.
   */
  private String getNearestDate(String date) {
    int daysToDecrement = -1;
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    try {
      c.setTime(sd.parse(date));
      c.add(Calendar.DATE, daysToDecrement);
      date = sd.format(c.getTime());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  /**
   * Gets value for company.
   *
   * @param date   the date
   * @param ticker the ticker
   * @return the value for company
   */
  public double getValueForCompany(String date, String ticker) {
    double value = 0;
    if (this.cachedData.containsKey(ticker)) {
      String data = this.cachedData.get(ticker);
      String[] stockArray = data.split("\n");
      for (int i = 1; i < stockArray.length; i++) {
        String[] temp = stockArray[i].split(",");
        if (temp[0].equals(date)) {
          value = Double.parseDouble(temp[4].trim());
        }
      }
      if (value == 0) {
        date = getNearestDate(date);
        value = getValueForCompany(date, ticker);
      }
    }
    return value;
  }
}
