import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class User {

  private Map<Integer, List<Stock>> portfolio;

  public User(Map<Integer, List<Stock>> portfolio) {
    this.portfolio = portfolio;
  }

  public Map<Integer, List<Stock>> getPortfolio() {
    return portfolio;
  }

  public void setPortfolio(Map<Integer, List<Stock>> portfolio) {
    this.portfolio = portfolio;
  }
}
