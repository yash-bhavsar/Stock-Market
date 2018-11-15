package view;

import java.io.IOException;

public interface IStockMarketView {

  /**
   *
   * @return
   * @throws IOException
   */
  String enterCommand() throws IOException;

  /**
   *
   * @param result
   * @throws IOException
   */
  void result(String result) throws IOException;
}
