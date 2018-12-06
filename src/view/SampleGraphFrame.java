package view;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.IStockMarketController;
import controller.StockMarketControllerImpl;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ojaspatwardhan
 */
public class SampleGraphFrame extends javax.swing.JFrame {

  /**
   * Creates new form SampleGraphFrame
   */
  public SampleGraphFrame(String portfolioNumber, String applicationTitle, String chartTitle,
                          String startDate,
                          IStockMarketController ic) {
    initComponents();
    JFreeChart lineChart = ChartFactory.createLineChart(
            chartTitle,
            "Date", "Cost & Value",
            createDataset(portfolioNumber, startDate, ic),
            PlotOrientation.VERTICAL,
            true, true, false);

    ChartPanel chartPanel = new ChartPanel(lineChart);
    chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
    setContentPane(chartPanel);
  }

  /**
   * Test private method.
   * @return the data-set.
   */
  private DefaultCategoryDataset createDataset( ) {
    String series1 = "Cost";
    String series2 = "Value";

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    dataset.addValue(200, series1, "2016-12-19");
    dataset.addValue(150, series1, "2016-12-20");
    dataset.addValue(100, series1, "2016-12-21");
    dataset.addValue(210, series1, "2016-12-22");
    dataset.addValue(240, series1, "2016-12-23");
    dataset.addValue(195, series1, "2016-12-24");
    dataset.addValue(245, series1, "2016-12-25");

    dataset.addValue(150, series2, "2016-12-19");
    dataset.addValue(130, series2, "2016-12-20");
    dataset.addValue(95, series2, "2016-12-21");
    dataset.addValue(195, series2, "2016-12-22");
    dataset.addValue(200, series2, "2016-12-23");
    dataset.addValue(180, series2, "2016-12-24");
    dataset.addValue(230, series2, "2016-12-25");

    return dataset;
  }

  /**
   * Helper method to create data-set to plot graph.
   *
   * @param portfolioNumber is the portfolio number.
   * @param startDate       is the start date.
   * @param ic              is the controller object.
   * @return the dataset.
   */
  private DefaultCategoryDataset createDataset(String portfolioNumber, String startDate,
                                               IStockMarketController ic) {
    String xAxis = "Cost";
    String yAxis = "Value";

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    Date stDate = new Date();

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    try {
      stDate = df.parse(startDate);
    } catch (ParseException p) {

    }
    Date enDate = new Date();

    long date1 = stDate.getTime();
    long date2 = enDate.getTime();

    long diff = (date2 - date1) / (1000 * 60 * 60 * 24);
    int daysToIncrement = Math.toIntExact(diff / 5);

    Date temp = stDate;
    while (temp.compareTo(enDate) <= 0) {
      String todayDate = df.format(temp);
      try {
        String costValue = ic.costBasisAndEvaluation(Integer.parseInt(portfolioNumber), todayDate);
        int cost = (int) getCostBasis(costValue);
        int value = (int) getEvaluation(costValue);
        dataset.addValue(cost, xAxis, temp);
        dataset.addValue(value, yAxis, temp);
      } catch (IllegalArgumentException e) {

      }
      Calendar c = Calendar.getInstance();
      c.setTime(temp);
      c.add(Calendar.DATE, daysToIncrement);
      try {
        temp = df.parse(df.format(c.getTime()));
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }

    /*dataset.addValue( 15 , "schools" , "1970" );
    dataset.addValue( 30 , "schools" , "1980" );
    dataset.addValue( 60 , "schools" ,  "1990" );
    dataset.addValue( 120 , "schools" , "2000" );
    dataset.addValue( 240 , "schools" , "2010" );
    dataset.addValue( 300 , "schools" , "2014" );*/
    return dataset;
  }

  /**
   * Private helper method to get the cost basis from the string.
   *
   * @param costValue is the input string.
   * @return the cost basis.
   */
  private double getCostBasis(String costValue) {
    String[] temp = costValue.split("\n");
    String[] temp2 = temp[0].split(":");
    return Double.parseDouble(temp2[1]);
  }

  private double getEvaluation(String costValue) {
    String[] temp = costValue.split("\n");
    String[] temp2 = temp[1].split(":");
    return Double.parseDouble(temp2[1]);
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 400, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 300, Short.MAX_VALUE)
    );

    pack();
    setVisible(true);
  }// </editor-fold>//GEN-END:initComponents

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(SampleGraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(SampleGraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(SampleGraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(SampleGraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
//                new SampleGraphFrame("1", "abc", "xyz", "2018-11-23", "2018-11-28", "5").setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
}
