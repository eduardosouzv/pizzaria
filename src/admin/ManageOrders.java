package admin;

import db.DrinksDB;
import db.OrdersDB;
import db.PizzaDB;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Drink;

public final class ManageOrders extends javax.swing.JInternalFrame {

    public ManageOrders() {
        initComponents();
        setOrdersList();
        pizzaSelector.setModel(new DefaultComboBoxModel());

        pizzaSelector.addActionListener((ActionEvent e) -> {
            String res = (String) pizzaSelector.getSelectedItem();
            String[] idArray = res.split(" ");
            String id = idArray[1];
            System.out.println(id);
            getSelectedPizza(id);
            setLabelPizzaSize(id);

        });
    }

    public void setSelectPizzaList(String id) {
        try {
            List<String> pizzasFromDB = new PizzaDB().getAllPizzasIdFromOrder(id);
            List<String> pizzas = new ArrayList<>();
            for (String pizza : pizzasFromDB) {
                pizzas.add("Pizza " + pizza);
            }
            pizzaSelector.setModel(new DefaultComboBoxModel(pizzas.toArray()));
            if (pizzasFromDB.size() > 0) {
                getSelectedPizza(pizzasFromDB.get(0));
                setLabelPizzaSize(pizzasFromDB.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLabelPizzaSize(String id) {
        try {
            List<String> size = new PizzaDB().getPizzaSizeFromId(id);
            labelPizzaSize.setText(size.toString().replace("[", "").replace("]", ""));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getSelectedPizza(String id) {
        try {
            List<String> flavours = new PizzaDB().getSelectedPizzaFromId(id);
            String[] columnName = {"Sabores"};
            DefaultTableModel model = new DefaultTableModel(columnName, 0);
            for (String flavour : flavours) {
                model.addRow(new String[]{flavour});
            }
            pizzasTable.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setDrinkListFromId(String id) {
        try {
            List<Drink> drinks = new DrinksDB().getDrinksFromOrder(id);
            String[] columnName = {"Bebidas", "Valor"};
            DefaultTableModel model = new DefaultTableModel(columnName, 0);
            for (Drink drink : drinks) {
                model.addRow(new Object[]{drink.name, drink.price});
            }
            drinkTable.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setOrdersList() {
        try {
            ArrayList<Integer> ids = new OrdersDB().getOrders();
            DefaultListModel<String> orderList = new DefaultListModel<>();
            ids.stream().forEach((id) -> {
                orderList.addElement("Pedido " + id);
            });
            listBox.setModel(orderList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setClientNameLabel(String id) {
        try {
            OrdersDB orders = new OrdersDB();
            clientName.setText(orders.getNameFromOrder(id));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getSelectedOrder() {
        String idArray[] = new String[3];
        String res = (String) listBox.getSelectedValue();
        if (res != null) {
            idArray = res.split(" ");
            String id = idArray[1];
            setClientNameLabel(id);
            setDrinkListFromId(id);
            setSelectPizzaList(id);
        }
    }

    public void clearAfterUpdateStatus() {
        clientName.setText("");
        labelPizzaSize.setText("");
        pizzaSelector.setModel(new DefaultComboBoxModel());
        String[] columnNameDrink = {"Bebidas", "Valor"};
        drinkTable.setModel(new DefaultTableModel(columnNameDrink, 0));
        String[] columnNamePizzas = {"Sabores"};
        pizzasTable.setModel(new DefaultTableModel(columnNamePizzas, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        orderListPanel = new javax.swing.JScrollPane();
        listBox = new javax.swing.JList();
        titlePedidos = new javax.swing.JLabel();
        labelInfosOrder = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pizzasTable = new javax.swing.JTable();
        infosPanel = new javax.swing.JScrollPane();
        drinkTable = new javax.swing.JTable();
        consultButton = new javax.swing.JButton();
        deliverButton = new javax.swing.JButton();
        labelPizzaSelector = new javax.swing.JLabel();
        pizzaSelector = new javax.swing.JComboBox();
        labelSize = new javax.swing.JLabel();
        labelPizzaSize = new javax.swing.JLabel();
        labelClient = new javax.swing.JLabel();
        clientName = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        listBox.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listBox.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        orderListPanel.setViewportView(listBox);

        titlePedidos.setText("Pedidos");

        labelInfosOrder.setText("Informações do pedido");

        pizzasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sabores"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pizzasTable.setEnabled(false);
        jScrollPane1.setViewportView(pizzasTable);

        drinkTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bebidas", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        drinkTable.setEnabled(false);
        infosPanel.setViewportView(drinkTable);

        consultButton.setText("Consultar");
        consultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultButtonActionPerformed(evt);
            }
        });

        deliverButton.setText("Entregar Pedido Selecionado");
        deliverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliverButtonActionPerformed(evt);
            }
        });

        labelPizzaSelector.setText("Informações pizza:");

        pizzaSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pizzaSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pizzaSelectorActionPerformed(evt);
            }
        });

        labelSize.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelSize.setText("Tamanho:");

        labelClient.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelClient.setText("Cliente");

        clientName.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(titlePedidos))
                    .addComponent(orderListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(consultButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInfosOrder)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(infosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelClient)
                                .addGap(18, 18, 18)
                                .addComponent(clientName)))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deliverButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelPizzaSize))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelPizzaSelector)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pizzaSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInfosOrder, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titlePedidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(orderListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deliverButton, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(consultButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelPizzaSelector)
                                    .addComponent(pizzaSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelSize)
                                    .addComponent(labelPizzaSize))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelClient)
                                    .addComponent(clientName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(infosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultButtonActionPerformed
        getSelectedOrder();
    }//GEN-LAST:event_consultButtonActionPerformed

    private void deliverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliverButtonActionPerformed
        OrdersDB orders = new OrdersDB();
        try {
            String res = (String) listBox.getSelectedValue();
            String[] idArray = res.split(" ");
            String id = idArray[1];
            orders.setOrderStatus(id);
            clearAfterUpdateStatus();
            setOrdersList();
            JOptionPane.showMessageDialog(null, "Pedido entregue com sucesso!", "Pedido", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum pedido selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deliverButtonActionPerformed

    private void pizzaSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pizzaSelectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pizzaSelectorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clientName;
    private javax.swing.JButton consultButton;
    private javax.swing.JButton deliverButton;
    private javax.swing.JTable drinkTable;
    private javax.swing.JScrollPane infosPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelClient;
    private javax.swing.JLabel labelInfosOrder;
    private javax.swing.JLabel labelPizzaSelector;
    private javax.swing.JLabel labelPizzaSize;
    private javax.swing.JLabel labelSize;
    private javax.swing.JList listBox;
    private javax.swing.JScrollPane orderListPanel;
    private javax.swing.JComboBox pizzaSelector;
    private javax.swing.JTable pizzasTable;
    private javax.swing.JLabel titlePedidos;
    // End of variables declaration//GEN-END:variables
}
