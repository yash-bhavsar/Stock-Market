package ui;

import javax.swing.*;

public class costBasisEvaluation {
  JPanel costBasisEvaluationPanel;
  private JTextField dateTextField;
  private JLabel dateLabel;
  private JTextPane costBasisEvaluationTextPane;
  private JButton calculateCostBasisEvaluationButton;

  public static void main(String[] args) {
    JFrame frame = new JFrame("costBasisEvaluation");
    frame.setContentPane(new costBasisEvaluation().costBasisEvaluationPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
