package ui;

import javax.swing.*;

public class buyStockPanel {
  private JTextField tickerTextField;
  private JLabel tickerLabel;
  private JLabel numberOfSharesLabel;
  private JTextField numberOfSharesTextField;
  private JTextField dateTextField;
  private JLabel dateLabel;
  private JTextField portfolioNumberTextField;
  private JLabel portfolioNumberLabel;
  private JTextField commissionTextField;
  private JLabel commissionLabel;
  private JButton buyButton;
  JPanel buyStockPanel;

  public static void main(String[] args) {
    JFrame frame = new JFrame("buyStockPanel");
    frame.setContentPane(new buyStockPanel().buyStockPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
