package admin;

import db.OrdersDB;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import models.StatisticOrder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class AdminScreen extends javax.swing.JFrame {

    public AdminScreen() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScreenPanel = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        menuPedidos = new javax.swing.JMenu();
        menuProgressOrders = new javax.swing.JMenuItem();
        menuDelivered = new javax.swing.JMenuItem();
        statisticMenu = new javax.swing.JMenu();
        statisticOrders = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");
        setResizable(false);

        javax.swing.GroupLayout ScreenPanelLayout = new javax.swing.GroupLayout(ScreenPanel);
        ScreenPanel.setLayout(ScreenPanelLayout);
        ScreenPanelLayout.setHorizontalGroup(
            ScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );
        ScreenPanelLayout.setVerticalGroup(
            ScreenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        menuPedidos.setText("Pedidos");
        menuPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPedidosActionPerformed(evt);
            }
        });

        menuProgressOrders.setText("Em andamento");
        menuProgressOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProgressOrdersActionPerformed(evt);
            }
        });
        menuPedidos.add(menuProgressOrders);

        menuDelivered.setText("Entregues");
        menuDelivered.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeliveredActionPerformed(evt);
            }
        });
        menuPedidos.add(menuDelivered);

        menuBar.add(menuPedidos);

        statisticMenu.setText("Estatisticas");
        statisticMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticMenuActionPerformed(evt);
            }
        });

        statisticOrders.setText("Clientes c/ mais pedidos");
        statisticOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticOrdersActionPerformed(evt);
            }
        });
        statisticMenu.add(statisticOrders);

        menuBar.add(statisticMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScreenPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScreenPanel)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPedidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuPedidosActionPerformed

    private void menuProgressOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProgressOrdersActionPerformed
        // TODO add your handling code here:
        ScreenPanel.removeAll();
        ManageOrders managementScreen = new ManageOrders();
        managementScreen.setVisible(true);
        ScreenPanel.add(managementScreen);
    }//GEN-LAST:event_menuProgressOrdersActionPerformed

    private void menuDeliveredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeliveredActionPerformed
        // TODO add your handling code here:
        ScreenPanel.removeAll();
        DeliveredOrders deliveredOrders = new DeliveredOrders();
        deliveredOrders.setVisible(true);
        ScreenPanel.add(deliveredOrders);
    }//GEN-LAST:event_menuDeliveredActionPerformed

    private void statisticMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticMenuActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_statisticMenuActionPerformed

    private void statisticOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticOrdersActionPerformed
        ScreenPanel.removeAll();

        DefaultPieDataset pieDataset = new DefaultPieDataset();
        try {
            ArrayList<StatisticOrder> users = new OrdersDB().getTopUsers();
            for (StatisticOrder user : users) {
                pieDataset.setValue(user.user, Integer.valueOf(user.orderCount));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Pedidos por cliente", pieDataset, true, true, true);
        
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("   {1}   ");
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(labelGenerator);
        ChartPanel cp = new ChartPanel(chart);

        JInternalFrame internalFrame = new JInternalFrame();

        internalFrame.add(cp);
        internalFrame.pack();
        internalFrame.setVisible(true);

        JDesktopPane pane = new JDesktopPane();
        pane.add(internalFrame);
        internalFrame.setVisible(true);
        internalFrame.setSize(631, 310);
        ScreenPanel.add(internalFrame);
    }//GEN-LAST:event_statisticOrdersActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane ScreenPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuDelivered;
    private javax.swing.JMenu menuPedidos;
    private javax.swing.JMenuItem menuProgressOrders;
    private javax.swing.JMenu statisticMenu;
    private javax.swing.JMenuItem statisticOrders;
    // End of variables declaration//GEN-END:variables
}
