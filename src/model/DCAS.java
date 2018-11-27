package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DCAS implements IStrategy {

  private IStockMarketModel model;

  public DCAS() {
  }

  @Override
  public void investmentStrategy(String ticker, double investmentAmount, String startDate,
                                       String endDate, int portfolioNumber, int frequency) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date sdate = dateFormat.parse(startDate);
    Date edate = dateFormat.parse(endDate);
    while (sdate.compareTo(edate) < 0) {
      model.invest(ticker, investmentAmount, dateFormat.format(sdate), portfolioNumber, 0);
      Calendar c = Calendar.getInstance();
      c.setTime(sdate);
      c.add(Calendar.DATE, frequency);
      sdate = dateFormat.parse(dateFormat.format(c.getTime()));
      System.out.println(sdate);
    }
  }

//  public static void main(String[] args) {
//    try {
//      investmentStrateg("AAPL", 1000, "2018-11-15", "2018-11-26", 1, 1);
//    } catch (ParseException e) {
//      e.printStackTrace();
//    }
//  }
}
