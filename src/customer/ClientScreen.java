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

    public ClientScreen(User user) {
        this.user = user;
        order = new Order(new ArrayList<>(), new ArrayList<>(), user, "ANDAMENTO");
        initComponents();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente");

        boxSizes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Grande", "Média", "Broto" }));

        buttonFlavours.setText("Registrar");
        buttonFlavours.setToolTipText("");
        buttonFlavours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFlavoursActionPerformed(evt);
            }
        });

        buttonDrinks.setText("Registrar");
        buttonDrinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDrinksActionPerformed(evt);
            }
        });

        buttonCheckOut.setText("Finalizar");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(boxSizes, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(panelFlavours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 23, Short.MAX_VALUE)))
                        .addGap(51, 51, 51))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonFlavours)
                            .addComponent(panelDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonDrinks))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tablePanelFinishPizza)
                        .addComponent(tablePanelFinishDrink))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(buttonCheckOut)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(boxSizes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelFlavours, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tablePanelFinishPizza, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonFlavours)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelDrinks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(tablePanelFinishDrink, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDrinks)
                    .addComponent(buttonCheckOut))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
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

            List<PizzaFlavour> selectedPizzaFlavours = new ArrayList<>();
            for (int index : listFlavours.getSelectedIndices()) {
                selectedPizzaFlavours.add(flavours.get(index));
            }
            Pizza pizza = new Pizza(sizes.get(selectedComboBox), selectedPizzaFlavours);
            order.pizzas.add(pizza);
            addPizzaToTable();

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
        order.drinks.add(drinks.get(listDrinks.getSelectedIndex()));
        addDrinkToTable();
    }

    private void addPizzaToTable() {
        String[] columnName = {"Tamanho", "Sabores", "Preço"};
        DefaultTableModel pizzasTable = new DefaultTableModel(columnName, 0);
        for (Pizza pizza : order.pizzas) {
            List<String> flavours = pizza.flavours.stream().map(x -> x.name).collect(Collectors.toList());
            pizzasTable.addRow(new String[]{pizza.size.description, String.join(",", flavours), String.valueOf(pizza.size.price)});
        }
        tableFinishPizza.setModel(pizzasTable);
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
    }
    
    private void buttonCheckOut(){
        OrderDB orderdb = new OrderDB();
        try {
            orderdb.addOrder(order);
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
