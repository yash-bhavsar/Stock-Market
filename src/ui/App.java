package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The type App.
 */
public class App {
  private JPanel rootPanel;
  private JButton createPortfolio;
  private JButton buyStock;
  private JButton invest;
  private JButton createStrategy;
  private JButton viewComposition;
  private JButton costBasisEvaluation;
  private JButton quit;
  private JPanel optionsPanel;
  private JPanel componentPanel;
  private JSplitPane splitPane;
  private JPanel createPortfolioPanel;

  /**
   * Instantiates a new App.
   */
  public App() {
    createPortfolio.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        createPortfolioPanel portfolioPanel = new createPortfolioPanel();
        splitPane.setRightComponent(portfolioPanel.createPortfolioPanel);
      }
    });
    buyStock.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        buyStockPanel buyStockPanel = new buyStockPanel();
        splitPane.setRightComponent(buyStockPanel.buyStockPanel);
      }
    });
    invest.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        investPanel investPanel = new investPanel();
        splitPane.setRightComponent(investPanel.investPanel);
      }
    });
    createStrategy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        strategyPanel strategyPanel = new strategyPanel();
        splitPane.setRightComponent(strategyPanel.strategyPanel);
      }
    });
    viewComposition.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        viewCompositionPanel viewCompositionPanel = new viewCompositionPanel();
        splitPane.setRightComponent(viewCompositionPanel.viewCompositionPanel);
      }
    });
    costBasisEvaluation.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ui.costBasisEvaluation costBasisEvaluation = new costBasisEvaluation();
        splitPane.setRightComponent(costBasisEvaluation.costBasisEvaluationPanel);
      }
    });
    quit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame("App");
    frame.setContentPane(new App().rootPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }
}
