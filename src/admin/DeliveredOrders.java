package admin;

import db.DrinksDB;
import db.OrdersDB;
import db.PizzaDB;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import models.Drink;
import models.User;

public final class DeliveredOrders extends javax.swing.JInternalFrame {

    public DeliveredOrders() {
        initComponents();
        clear();
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

    public void clear() {
        street.setText("");
        district.setText("");
        number.setText("");
        name.setText("");
        labelPizzaSize.setText("");
        pizzaSelector.setModel(new DefaultComboBoxModel());
        String[] columnNameDrink = {"Bebidas", "Valor"};
        tableDrinks.setModel(new DefaultTableModel(columnNameDrink, 0));
        String[] columnNamePizzas = {"Sabores"};
        pizzasTable.setModel(new DefaultTableModel(columnNamePizzas, 0));
    }

    public void setDrinkListFromId(String id) {
        try {
            List<Drink> drinks = new DrinksDB().getDrinksFromOrder(id);
            String[] columnName = {"Bebidas", "Valor"};
            DefaultTableModel model = new DefaultTableModel(columnName, 0);
            drinks.stream().forEach((drink) -> {
                model.addRow(new Object[]{drink.name, drink.price});
            });
            tableDrinks.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setOrdersList() {
        try {
            ArrayList<Integer> ids = new OrdersDB().getDeliveredOrders();
            DefaultListModel<String> orderList = new DefaultListModel<>();
            ids.stream().forEach((id) -> {
                orderList.addElement("Pedido " + id);
            });
            listOrders.setModel(orderList);
        } catch (Exception e) {
            System.out.println(e);
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

    public void setSelectPizzaList(String id) {
        try {
            List<String> pizzasFromDB = new PizzaDB().getAllPizzasIdFromOrder(id);
            List<String> pizzas = new ArrayList<>();
            pizzasFromDB.stream().forEach((pizza) -> {
                pizzas.add("Pizza " + pizza);
            });
            pizzaSelector.setModel(new DefaultComboBoxModel(pizzas.toArray()));
            if (pizzasFromDB.size() > 0) {
                getSelectedPizza(pizzasFromDB.get(0));
                setLabelPizzaSize(pizzasFromDB.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void getSelectedOrder() {
        String res = (String) listOrders.getSelectedValue();
        if (res != null) {
            String idArray[] = res.split(" ");
            String id = idArray[1];
            setDrinkListFromId(id);
            setSelectPizzaList(id);
            try {
                User user = new OrdersDB().getUserFromID(id);
                name.setText(user.user);
                street.setText(user.address_street);
                number.setText(String.valueOf(user.address_number));
                district.setText(user.address_district);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pane = new javax.swing.JScrollPane();
        listOrders = new javax.swing.JList();
        buttonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDrinks = new javax.swing.JTable();
        pizzaSelector = new javax.swing.JComboBox();
        labelInfoPizza = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pizzasTable = new javax.swing.JTable();
        labelTamanho = new javax.swing.JLabel();
        labelPizzaSize = new javax.swing.JLabel();
        labelTitle = new javax.swing.JLabel();
        labelClient = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        labelAddress = new javax.swing.JLabel();
        street = new javax.swing.JLabel();
        district = new javax.swing.JLabel();
        number = new javax.swing.JLabel();

        listOrders.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listOrders.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pane.setViewportView(listOrders);

        buttonSearch.setText("Consultar");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        tableDrinks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bebidas", "Valor"
            }
        ));
        tableDrinks.setEnabled(false);
        jScrollPane1.setViewportView(tableDrinks);

        pizzaSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelInfoPizza.setText("Informações pizza:");

        pizzasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sabores"
            }
        ));
        pizzasTable.setEnabled(false);
        jScrollPane3.setViewportView(pizzasTable);

        labelTamanho.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelTamanho.setText("Tamanho:");

        labelPizzaSize.setText("jLabel2");

        labelTitle.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        labelTitle.setText("Informações da compra");

        labelClient.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelClient.setText("Cliente:");

        name.setText("jLabel5");

        labelAddress.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelAddress.setText("Endereço:");

        street.setText("jLabel1");

        district.setText("jLabel1");

        number.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(pane))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTitle)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(district)
                            .addComponent(number)
                            .addComponent(street))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelInfoPizza)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pizzaSelector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTamanho)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelPizzaSize))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name))
                    .addComponent(labelAddress))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pane, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelClient)
                                    .addComponent(name))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelAddress)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(street)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(number)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(district)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pizzaSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelInfoPizza))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTamanho)
                            .addComponent(labelPizzaSize))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        // TODO add your handling code here:
        getSelectedOrder();
    }//GEN-LAST:event_buttonSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSearch;
    private javax.swing.JLabel district;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelAddress;
    private javax.swing.JLabel labelClient;
    private javax.swing.JLabel labelInfoPizza;
    private javax.swing.JLabel labelPizzaSize;
    private javax.swing.JLabel labelTamanho;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JList listOrders;
    private javax.swing.JLabel name;
    private javax.swing.JLabel number;
    private javax.swing.JScrollPane pane;
    private javax.swing.JComboBox pizzaSelector;
    private javax.swing.JTable pizzasTable;
    private javax.swing.JLabel street;
    private javax.swing.JTable tableDrinks;
    // End of variables declaration//GEN-END:variables
}
