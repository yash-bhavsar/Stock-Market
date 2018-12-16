package view;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout.Alignment;

import controller.IStockMarketController;
import model.Stock;

/**
 * The type View composition panel.
 *
 * @author ojaspatwardhan
 */
public class ViewCompositionPanel extends javax.swing.JPanel {

  private IStockMarketController stockMarketController;

  /**
   * Creates new form ViewCompositionPanel.
   *
   * @param stockMarketController the stock market controller
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

    javax.swing.JLabel viewCompositionPortfolNumberLbl = new javax.swing.JLabel();
    viewCompositionPortfolioNumberField = new javax.swing.JTextField();
    javax.swing.JLabel viewCompositionDateLabel = new javax.swing.JLabel();
    viewCompositionDateField = new javax.swing.JTextField();
    javax.swing.JButton viewCompositionBtn = new javax.swing.JButton();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
    viewCompositionTextArea = new javax.swing.JTextArea();
    javax.swing.JLabel resultLabel = new javax.swing.JLabel();

    viewCompositionPortfolNumberLbl.setText("Please enter the portfolio number.");

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
            layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING,
                            layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.
                                                            DEFAULT_SIZE,
                                                    658, Short.MAX_VALUE)
                                            .addComponent(viewCompositionBtn,
                                                    Alignment.LEADING,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE)
                                            .addComponent(viewCompositionPortfolioNumberField)
                                            .addComponent(viewCompositionDateField,
                                                    Alignment.LEADING)
                                            .addGroup(Alignment.LEADING,
                                                    layout.createParallelGroup(
                                                            Alignment.LEADING)
                                                            .addComponent(
                                                                    viewCompositionPortfolNumberLbl)
                                                            .addComponent(viewCompositionDateLabel)
                                                            .addComponent(resultLabel)
                                                            .addGroup(layout.createSequentialGroup()
                                                            ).addGap(0, 0, Short.MAX_VALUE)
                                            )
                                    )
                                    .addContainerGap())
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(viewCompositionPortfolNumberLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewCompositionPortfolioNumberField,
                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewCompositionDateLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewCompositionDateField,
                                    javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(resultLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewCompositionBtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    161, Short.MAX_VALUE)
                            .addContainerGap())
    );
  } // </editor-fold>//GEN-END:initComponents


  private void viewCompositionBtnActionPerformed(java.awt.event.ActionEvent evt) {
    //GEN-FIRST:event_viewCompositionBtnActionPerformed
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
  } //GEN-LAST:event_viewCompositionBtnActionPerformed


  private javax.swing.JTextField viewCompositionDateField;
  private javax.swing.JTextField viewCompositionPortfolioNumberField;
  private javax.swing.JTextArea viewCompositionTextArea;
  // End of variables declaration//GEN-END:variables
}
