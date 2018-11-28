package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The class DCAS which implements methods of IStrategy interface. This represents one strategy
 * which is Dollar Cost Averaging Strategy.
 */
public class DCAS implements IStrategy {

  /**
   * Constructor which can be used for any instantiations required in the future.
   */
  public DCAS() {
  }

  @Override
  public void investmentStrategy(String ticker, double investmentAmount, String startDate,
                                 String endDate, int portfolioNumber, int frequency,
                                 IStockMarketModel model, User user)
          throws ParseException {
    Services service = Services.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date sdate = dateFormat.parse(startDate);
    Date edate = dateFormat.parse(endDate);
    while (sdate.compareTo(edate) <= 0) {
      double value = service.getValueForCompany(dateFormat.format(sdate), ticker);
      double numberOfShares = investmentAmount / value;
      Stock stock = null;
      try {
        stock = service.getDataForCompany(ticker, numberOfShares,
                dateFormat.format(sdate), 0);
      } catch (IllegalArgumentException e) {
        String t = getNearestDate(dateFormat.format(sdate));
        sdate = dateFormat.parse(t);
      }
      if (stock != null) {
        user.buyStock(portfolioNumber, stock);
      }
      Calendar c = Calendar.getInstance();
      c.setTime(sdate);
      c.add(Calendar.DATE, frequency);
      sdate = dateFormat.parse(dateFormat.format(c.getTime()));
    }
  }

  /**
   * Helper method to get nearest date if the date passed is a holiday.
   *
   * @return the nearest date.
   */
  private String getNearestDate(String date) {
    int daysToIncrement = 1;
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    try {
      c.setTime(sd.parse(date));
      c.add(Calendar.DATE, daysToIncrement);
      date = sd.format(c.getTime());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }
}
