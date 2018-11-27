package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DCAS implements IStrategy {

  @Override
  public void investmentStrategy(String ticker, double investmentAmount, String startDate,
                                 String endDate, int portfolioNumber, int frequency) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date sdate = dateFormat.parse(startDate);

  }
}
