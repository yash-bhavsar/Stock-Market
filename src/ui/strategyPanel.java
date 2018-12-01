package ui;

import javax.swing.*;

public class strategyPanel {
  private JTextField portfolioNumberTextField;
  private JLabel portfolioNumberLabel;
  JPanel strategyPanel;
  private JTextField startDateTextField;
  private JLabel startDateLabel;
  private JLabel endDateLabel;
  private JTextField endDateTextField;
  private JTextField frequencyTextField;
  private JLabel frequencyLabel;
  private JComboBox endDateComboBox;
  private JLabel endDateComboBoxLabel;
  private JLabel investmentAmountLabel;
  private JTextField investmentAmountTextField;
  private JButton createStrategyButton;

  public static void main(String[] args) {
    JFrame frame = new JFrame("strategyPanel");
    frame.setContentPane(new strategyPanel().strategyPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
