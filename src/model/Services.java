package model;

public interface Services {

  Stock getDataForCompany(String stockSymbol, double shares, String date, double commission);

  double getValueForCompany(String date, String ticker);
}
