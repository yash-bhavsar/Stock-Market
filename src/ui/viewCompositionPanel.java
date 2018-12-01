package ui;

import javax.swing.*;

public class viewCompositionPanel {
  JPanel viewCompositionPanel;
  private JLabel stockDisplayDateLabel;
  private JTextField stockDisplayDateTextField;
  private JTable stocksTable;
  private JButton dateButton;

  public viewCompositionPanel() {
    String [] columns = {"Ticker Name", "Date of purchase", "No. of shares", "Purchase price"};
    Object[][] dummyData = {{"AAPL", "2018-11-23", "2", "172.3"},
            {"AAPL", "2018-11-22", "2", "172.3"}};
    stocksTable = new JTable(dummyData, columns);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("viewCompositionPanel");
    frame.setContentPane(new viewCompositionPanel().viewCompositionPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
