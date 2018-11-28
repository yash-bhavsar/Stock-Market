package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The class DCAS which implements methods of IStrategy interface.
 */
public class DCAS implements IStrategy {

  private IStockMarketModel model;

  /**
   * Instantiates a new model.
   */
  public DCAS() {}

  @Override
  public void investmentStrategy(String ticker, double investmentAmount, String startDate,
                                       String endDate, int portfolioNumber, int frequency, IStockMarketModel model)
          throws ParseException {
    Services service = Services.getInstance();
    Stock s;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date sdate = dateFormat.parse(startDate);
    Date edate = dateFormat.parse(endDate);

    while (sdate.compareTo(edate) < 0) {
      model.invest(ticker, investmentAmount, dateFormat.format(sdate), portfolioNumber,
              0);
      Calendar c = Calendar.getInstance();
      c.setTime(sdate);
      c.add(Calendar.DATE, frequency);
      sdate = dateFormat.parse(dateFormat.format(c.getTime()));
      String tempDate = dateFormat.format(sdate);
      try {
        s = service.getDataForCompany(ticker, 0, tempDate, 0);
      } catch (IllegalArgumentException e) {
       String t = getNearestDate(tempDate);
       sdate = dateFormat.parse(t);
      }
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
