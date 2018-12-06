package view;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import controller.IStockMarketController;
import model.Stock;

/**
 * @author ojaspatwardhan
 */
public class ViewCompositionPanel extends javax.swing.JPanel {

  private IStockMarketController stockMarketController;

  /**
   * Creates new form ViewCompositionPanel
   */
  public ViewCompositionPanel(IStockMarketController stockMarketController) {
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

    viewCompositionPortfolioNumberLabel = new javax.swing.JLabel();
    viewCompositionPortfolioNumberField = new javax.swing.JTextField();
    viewCompositionDateLabel = new javax.swing.JLabel();
    viewCompositionDateField = new javax.swing.JTextField();
    viewCompositionBtn = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    viewCompositionTextArea = new javax.swing.JTextArea();
    resultLabel = new javax.swing.JLabel();

    viewCompositionPortfolioNumberLabel.setText("Please enter the portfolio number.");

    viewCompositionPortfolioNumberField.setText("");

    viewCompositionDateLabel.setText("Please enter the date (yyyy-mm-dd).");

    viewCompositionDateField.setText("");

    viewCompositionBtn.setText("View");
    viewCompositionBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        viewCompositionBtnActionPerformed(evt);
      }
    });

    viewCompositionTextArea.setEditable(false);
    jScrollPane1.setViewportView(viewCompositionTextArea);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                                    .addComponent(viewCompositionBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(viewCompositionPortfolioNumberField)
                                    .addComponent(viewCompositionDateField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(viewCompositionPortfolioNumberLabel)
                                                    .addComponent(viewCompositionDateLabel)
                                                    .addComponent(resultLabel))
                                            .addGap(0, 0, Short.MAX_VALUE)))
                            .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(viewCompositionPortfolioNumberLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewCompositionPortfolioNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewCompositionDateLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewCompositionDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(resultLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewCompositionBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

  private void viewCompositionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCompositionBtnActionPerformed
    // TODO add your handling code here:
    String pNumber = viewCompositionPortfolioNumberField.getText();
    String date = viewCompositionDateField.getText();
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      int profileNumber = Integer.parseInt(pNumber);
      Date date1 = dateFormat.parse(date);
      if (date1.after(new Date())) {
        this.viewCompositionTextArea.setText("Future dates are not valid.");
        return;
      } else if (profileNumber < 0) {
        this.viewCompositionTextArea.setText("Profile number should be positive.");
      } else {
        List<Stock> stocks = this.stockMarketController.viewComposition(profileNumber, date);
        String response = "";
        for (Stock stock : stocks) {
          response += "\nTicker name: " + stock.getTicker() + "\nDate of purchase: "
                  + stock.getDateTime() + "\nPurchase price: $" + stock.getPurchasePrice()
                  + "\nNumber of shares: " + stock.getShares() + "\n";
        }
        this.viewCompositionTextArea.setText(response);
      }
    } catch (NumberFormatException ne) {
      this.viewCompositionTextArea.setText("Enter a valid number.");
    } catch (ParseException e) {
      this.viewCompositionTextArea.setText("Enter valid date of format (yyyy-MM-dd).");
    } catch (IllegalArgumentException e) {
      this.viewCompositionTextArea.setText(e.getMessage());
    }
  }//GEN-LAST:event_viewCompositionBtnActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JButton viewCompositionBtn;
  private javax.swing.JTextField viewCompositionDateField;
  private javax.swing.JLabel viewCompositionDateLabel;
  private javax.swing.JTextField viewCompositionPortfolioNumberField;
  private javax.swing.JLabel viewCompositionPortfolioNumberLabel;
  private javax.swing.JLabel resultLabel;
  private javax.swing.JTextArea viewCompositionTextArea;
  // End of variables declaration//GEN-END:variables
}
