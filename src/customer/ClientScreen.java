package customer;

import db.DrinksDB;
import db.OrderDB;
import db.PizzaFlavoursDB;
import db.SizesDB;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Drink;
import models.Order;
import models.Pizza;
import models.PizzaFlavour;
import models.PizzaSize;
import models.User;

public class ClientScreen extends javax.swing.JFrame {

    Order order;
    User user;
    List<Drink> drinks;
    List<PizzaFlavour> flavours;
    List<PizzaSize> sizes;
    PreparedStatement templateQuery = null;
    float somaDrink = 0;
    float somaPizza = 0;

    public ClientScreen(User user) {
        this.user = user;
        order = new Order(new ArrayList<>(), new ArrayList<>(), user, "ANDAMENTO");
        initComponents();
        labelNameDisplay.setText(user.user + ", " + user.address_street + ", " + user.address_district + ", " + user.address_number);
        loadPizzaSizes();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boxSizes = new javax.swing.JComboBox();
        buttonFlavours = new javax.swing.JButton();
        buttonDrinks = new javax.swing.JButton();
        buttonCheckOut = new javax.swing.JButton();
        panelDrinks = new javax.swing.JScrollPane();
        listDrinks = new javax.swing.JList();
        panelFlavours = new javax.swing.JScrollPane();
        listFlavours = new javax.swing.JList();
        tablePanelFinishDrink = new javax.swing.JScrollPane();
        tableFinishDrink = new javax.swing.JTable();
        tablePanelFinishPizza = new javax.swing.JScrollPane();
        tableFinishPizza = new javax.swing.JTable();
        labelNameDisplay = new javax.swing.JLabel();
        labelClientLogged = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        labelTotalPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente");
        setResizable(false);

        boxSizes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grande", "Média", "Broto" }));

        buttonFlavours.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonFlavours.setText("Registrar");
        buttonFlavours.setToolTipText("");
        buttonFlavours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFlavoursActionPerformed(evt);
            }
        });

        buttonDrinks.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonDrinks.setText("Registrar");
        buttonDrinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDrinksActionPerformed(evt);
            }
        });

        buttonCheckOut.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonCheckOut.setText("Finalizar Pedido");
        buttonCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCheckOutActionPerformed(evt);
            }
        });

        listDrinks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        panelDrinks.setViewportView(listDrinks);

        listFlavours.setToolTipText("");
        panelFlavours.setViewportView(listFlavours);

        tableFinishDrink.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bebidas", "Preço"
            }
        ));
        tableFinishDrink.setEnabled(false);
        tablePanelFinishDrink.setViewportView(tableFinishDrink);

        tableFinishPizza.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tamanho", "Sabores", "Preço"
            }
        ));
        tableFinishPizza.setEnabled(false);
        tablePanelFinishPizza.setViewportView(tableFinishPizza);
        if (tableFinishPizza.getColumnModel().getColumnCount() > 0) {
            tableFinishPizza.getColumnModel().getColumn(0).setMaxWidth(800);
            tableFinishPizza.getColumnModel().getColumn(2).setMaxWidth(500);
        }

        labelNameDisplay.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelNameDisplay.setToolTipText("");

        labelClientLogged.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelClientLogged.setText("Olá, ");

        labelTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelTotal.setText("TOTAL:");

        labelTotalPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelClientLogged)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelFlavours, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(boxSizes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(buttonFlavours, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(buttonDrinks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tablePanelFinishPizza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tablePanelFinishDrink, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelClientLogged, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(labelNameDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(boxSizes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelFlavours, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tablePanelFinishPizza, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(buttonFlavours, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(tablePanelFinishDrink, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loadPizzaSizes() {
        loadPizzaFlavours();
        loadDrinks();
        try {
            sizes = new SizesDB().getSizes();
            DefaultComboBoxModel<String> sizeListModel = new DefaultComboBoxModel<>();
            sizes.stream().forEach((size) -> {
                sizeListModel.addElement(size.description);
            });
            boxSizes.setModel(sizeListModel);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void loadPizzaFlavours() {
        try {
            flavours = new PizzaFlavoursDB().getFlavours();
            DefaultListModel<String> flavourListModel = new DefaultListModel<>();
            flavours.stream().forEach((flavour) -> {
                flavourListModel.addElement(flavour.name);
            });
            listFlavours.setModel(flavourListModel);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void registerFlavours() {
        try {
            int selectedComboBox = boxSizes.getSelectedIndex();
            PizzaSize pizzasize = sizes.get(selectedComboBox);
            int[] verificationFlavour = listFlavours.getSelectedIndices();

            List<PizzaFlavour> selectedPizzaFlavours = new ArrayList<>();
            for (int index : listFlavours.getSelectedIndices()) {
                selectedPizzaFlavours.add(flavours.get(index));
            }

            if (verificationFlavour.length > pizzasize.max_flavours || verificationFlavour.length == 0) {
                JOptionPane.showMessageDialog(null, "Escolha entre 1 sabor a " + pizzasize.max_flavours + "!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                Pizza pizza = new Pizza(sizes.get(selectedComboBox), selectedPizzaFlavours);
                order.pizzas.add(pizza);
                addPizzaToTable();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void loadDrinks() {
        try {
            drinks = new DrinksDB().getDrink();
            DefaultListModel<String> drinkListModel = new DefaultListModel<>();
            drinks.stream().forEach((drink) -> {
                drinkListModel.addElement(drink.name);
            });
            listDrinks.setModel(drinkListModel);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void registerDrinks() {
        int verificationDrink = listDrinks.getSelectedIndex();
        if (verificationDrink < 0) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar a bebida!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            order.drinks.add(drinks.get(listDrinks.getSelectedIndex()));
            addDrinkToTable();
        }
    }

    private void addPizzaToTable() {
        String[] columnName = {"Tamanho", "Sabores", "Preço"};
        DefaultTableModel pizzasTable = new DefaultTableModel(columnName, 0);
        for (Pizza pizza : order.pizzas) {
            List<String> flavours = pizza.flavours.stream().map(x -> x.name).collect(Collectors.toList());
            pizzasTable.addRow(new String[]{pizza.size.description, String.join(",", flavours), String.valueOf(pizza.size.price)});
        }
        tableFinishPizza.setModel(pizzasTable);
        for (Pizza pizzapay : order.pizzas) {
            somaPizza += pizzapay.size.price;
        }
        float somaTotal = somaDrink + somaPizza;
        labelTotalPrice.setText(String.valueOf(somaTotal));
        tableFinishPizza.getColumnModel().getColumn(0).setMaxWidth(800);
        tableFinishPizza.getColumnModel().getColumn(2).setMaxWidth(500);
    }

    private void addDrinkToTable() {
        String[] columnName = {"Bebidas", "Preço"};
        DefaultTableModel drinksTable = new DefaultTableModel(columnName, 0);
        for (Drink drink : order.drinks) {
            drinksTable.addRow(new String[]{drink.name, String.valueOf(drink.price)});
        }
        tableFinishDrink.setModel(drinksTable);
        for (Drink drinkpay : order.drinks) {
            somaDrink += drinkpay.price;
        } 
        float somaTotal = somaDrink + somaPizza;
        labelTotalPrice.setText(String.valueOf(somaTotal));
    }

    private void buttonCheckOut() {
        OrderDB orderdb = new OrderDB();
        try {
            orderdb.addOrder(order);
            order = new Order(new ArrayList<>(), new ArrayList<>(), user, "ANDAMENTO");
            ((DefaultTableModel) tableFinishPizza.getModel()).setRowCount(0);
            ((DefaultTableModel) tableFinishDrink.getModel()).setRowCount(0);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Erro ao finalizar pedido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buttonDrinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDrinksActionPerformed
        registerDrinks();
    }//GEN-LAST:event_buttonDrinksActionPerformed

    private void buttonFlavoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFlavoursActionPerformed
        registerFlavours();
    }//GEN-LAST:event_buttonFlavoursActionPerformed

    private void buttonCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCheckOutActionPerformed
        buttonCheckOut();
    }//GEN-LAST:event_buttonCheckOutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox boxSizes;
    private javax.swing.JButton buttonCheckOut;
    private javax.swing.JButton buttonDrinks;
    private javax.swing.JButton buttonFlavours;
    private javax.swing.JLabel labelClientLogged;
    private javax.swing.JLabel labelNameDisplay;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelTotalPrice;
    private javax.swing.JList listDrinks;
    private javax.swing.JList listFlavours;
    private javax.swing.JScrollPane panelDrinks;
    private javax.swing.JScrollPane panelFlavours;
    private javax.swing.JTable tableFinishDrink;
    private javax.swing.JTable tableFinishPizza;
    private javax.swing.JScrollPane tablePanelFinishDrink;
    private javax.swing.JScrollPane tablePanelFinishPizza;
    // End of variables declaration//GEN-END:variables
}
