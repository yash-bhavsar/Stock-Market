package view;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.IStockMarketController;

/**
 * The class Create portfolio panel.
 *
 * @author ojaspatwardhan
 */
public class CreatePortfolioPanel extends javax.swing.JPanel {

  /**
   * Creates new form createPortfolioPanel.
   */

  private IStockMarketController stockMarketController;

  /**
   * Instantiates a new Create portfolio panel.
   *
   * @param stockMarketController the stock market controller
   */
  public CreatePortfolioPanel(IStockMarketController stockMarketController) {
    initComponents();
    this.stockMarketController = stockMarketController;
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    createPortfolioLabel = new javax.swing.JLabel();
    createPortfolioTextField = new javax.swing.JTextField();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton createPortfolioBtn = new javax.swing.JButton();

    createPortfolioLabel.setName("createPortfolioLabel");
    createPortfolioTextField.setName("createPortfolioTextField");
    createPortfolioBtn.setName("createPortfolioBtn");

    createPortfolioLabel.setText("Please enter the portfolio number.");

    createPortfolioTextField.setText("");
    createPortfolioTextField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        createPortfolioTextFieldActionPerformed(evt);
      }
    });

    createPortfolioBtn.setText("Create");

    createPortfolioBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        createPortfolioBtnActionPerformed(actionEvent);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(
                                    javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(createPortfolioLabel,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    388, Short.MAX_VALUE))
                                    .addComponent(createPortfolioTextField)
                                    .addComponent(createPortfolioBtn,
                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(createPortfolioLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(createPortfolioTextField,
                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(createPortfolioBtn)
                            .addContainerGap(211, Short.MAX_VALUE))
    );
  } // </editor-fold>//GEN-END:initComponents

  private void createPortfolioTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    //GEN-FIRST:event_createPortfolioTextFieldActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_createPortfolioTextFieldActionPerformed

  private void createPortfolioBtnActionPerformed(java.awt.event.ActionEvent evt) {
    //GEN-FIRST:event_createPortfolioBtnActionPerformed
    // TODO add your handling code here:
    createPortfolio(createPortfolioTextField.getText());
  } //GEN-LAST:event_createPortfolioBtnActionPerformed


  /**
   * Private helper method used to create a portfolio.
   *
   * @param n is the number of the portfolio to be created.
   */
  private void createPortfolio(String n) {
    try {
      int a = Integer.parseInt(n);
      if (a < 0) {
        createPortfolioLabel.setText("Portfolio number cannot be negative");
      }
      String result = this.stockMarketController.createPortfolio(n);
      if (result.equals("pass")) {
        createPortfolioLabel.setText("Portfolio created successfully");
      } else {
        createPortfolioLabel.setText(result);
      }
    } catch (NumberFormatException e) {
      createPortfolioLabel.setText("Please enter a number");
    }
  }


  private javax.swing.JLabel createPortfolioLabel;
  private javax.swing.JTextField createPortfolioTextField;
  // End of variables declaration//GEN-END:variables
}
