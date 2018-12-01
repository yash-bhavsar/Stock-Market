package ui;

import javax.swing.*;

public class investPanel {
  JPanel investPanel;
  private JTextField portfolioNumberTextField;
  private JLabel portfolioNumberLabel;
  private JLabel dateLabel;
  private JTextField startDateTextField;
  private JLabel weightsLabel;
  private JComboBox weightsComboBox;
  private JLabel investmentAmountLabel;
  private JTextField investmentAmountTextField;
  private JButton investButton;

  public static void main(String[] args) {
    JFrame frame = new JFrame("investPanel");
    frame.setContentPane(new investPanel().investPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
