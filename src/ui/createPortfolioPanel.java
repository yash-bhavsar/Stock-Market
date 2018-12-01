package ui;

import javax.swing.*;

/**
 * The type Create portfolio panel.
 */
public class createPortfolioPanel {

  private JTextField createPortfolioTextField;
  private JButton createPortfolioButton;
  private JLabel createPortfolioLabel;
  /**
   * The Create portfolio panel.
   */
  JPanel createPortfolioPanel;

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame("createPortfolioPanel");
    frame.setContentPane(new createPortfolioPanel().createPortfolioPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
