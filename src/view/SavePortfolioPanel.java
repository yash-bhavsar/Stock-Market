package view;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controller.IStockMarketController;

/**
 *
 * @author ojaspatwardhan
 */
public class SavePortfolioPanel extends javax.swing.JPanel {

    private IStockMarketController stockMarketController;

    /**
     * Creates new form SavePortfolioPanel
     */
    public SavePortfolioPanel(IStockMarketController stockMarketController) {
        initComponents();
        this.stockMarketController = stockMarketController;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        portfolioNumberLbl = new javax.swing.JLabel();
        portfolioNumberTextField = new javax.swing.JTextField();
        savePortfolioBtn = new javax.swing.JButton();

        portfolioNumberLbl.setText("Enter portfolio number.");

        portfolioNumberTextField.setText("Enter number.");

        savePortfolioBtn.setText("Save Portfolio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(savePortfolioBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(portfolioNumberTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(portfolioNumberLbl)
                        .addGap(0, 1055, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(portfolioNumberLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(portfolioNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savePortfolioBtn)
                .addContainerGap(715, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel portfolioNumberLbl;
    private javax.swing.JTextField portfolioNumberTextField;
    private javax.swing.JButton savePortfolioBtn;
    // End of variables declaration//GEN-END:variables
}
