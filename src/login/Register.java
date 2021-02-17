package login;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import db.Connect;
import db.UserDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import login.Authentication;
import models.User;

public class Register extends javax.swing.JFrame {

    Connection connection = null;
    PreparedStatement templateQuery = null;

    public Register() {
        initComponents();
        connection = Connect.Database();
    }

    public boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void register() {

        if (textUser.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Nome de usuário em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (String.valueOf(textPassword.getPassword()).length() == 0) {
            JOptionPane.showMessageDialog(null, "Senha em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (textStreet.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rua em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (!isInt(textNumber.getText())) {
            JOptionPane.showMessageDialog(null, "Número de endereço em branco ou inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (textDistrict.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Bairro em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (textCity.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Cidade em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                User user = new User(textUser.getText(), String.valueOf(textPassword.getPassword()), "CLIENT", textStreet.getText(), Integer.valueOf(textNumber.getText()), textDistrict.getText(), textCity.getText());
                UserDB userdb = new UserDB();
                userdb.addUser(user);
                JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!", "Cadastrado", JOptionPane.INFORMATION_MESSAGE);
            } catch (MySQLIntegrityConstraintViolationException se) {
                JOptionPane.showMessageDialog(null, "Usuario existente.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Erro no cadastro.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelUser = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        titleAddress = new javax.swing.JLabel();
        textUser = new javax.swing.JTextField();
        titleUserRegister = new javax.swing.JLabel();
        labelStreet = new javax.swing.JLabel();
        labelNumber = new javax.swing.JLabel();
        labelDistrict = new javax.swing.JLabel();
        labelCity = new javax.swing.JLabel();
        textStreet = new javax.swing.JTextField();
        textDistrict = new javax.swing.JTextField();
        textCity = new javax.swing.JTextField();
        textNumber = new javax.swing.JTextField();
        buttonRegister = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        textPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro");
        setResizable(false);

        labelUser.setText("Usuario");

        labelPassword.setText("Senha");

        titleAddress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titleAddress.setText("INFORMAÇÕES DE ENDEREÇO");

        titleUserRegister.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titleUserRegister.setText("REGISTRO DE USUARIO");

        labelStreet.setText("Rua");

        labelNumber.setText("Número");

        labelDistrict.setText("Bairro");

        labelCity.setText("Cidade");

        textStreet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textStreetActionPerformed(evt);
            }
        });

        textDistrict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDistrictActionPerformed(evt);
            }
        });

        textCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCityActionPerformed(evt);
            }
        });

        textNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNumberActionPerformed(evt);
            }
        });

        buttonRegister.setText("Registrar");
        buttonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegisterActionPerformed(evt);
            }
        });

        buttonClose.setText("Fechar");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleAddress)
                    .addComponent(labelDistrict)
                    .addComponent(titleUserRegister)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUser)
                            .addComponent(labelPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textUser)
                            .addComponent(textPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(buttonClose)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(buttonRegister))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelStreet)
                                .addComponent(labelNumber)
                                .addComponent(labelCity))
                            .addGap(28, 28, 28)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textCity, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(titleUserRegister)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUser)
                    .addComponent(textUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPassword)
                    .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(titleAddress)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStreet)
                    .addComponent(textStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumber)
                    .addComponent(textNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDistrict)
                    .addComponent(textDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCity)
                    .addComponent(textCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRegister)
                    .addComponent(buttonClose))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textStreetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textStreetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textStreetActionPerformed

    private void textDistrictActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDistrictActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textDistrictActionPerformed

    private void textCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCityActionPerformed

    private void textNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNumberActionPerformed

    private void buttonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegisterActionPerformed
        // TODO add your handling code here:
        register();
    }//GEN-LAST:event_buttonRegisterActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        // TODO add your handling code here:
        Authentication screen = new Authentication();
        screen.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonRegister;
    private javax.swing.JLabel labelCity;
    private javax.swing.JLabel labelDistrict;
    private javax.swing.JLabel labelNumber;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelStreet;
    private javax.swing.JLabel labelUser;
    private javax.swing.JTextField textCity;
    private javax.swing.JTextField textDistrict;
    private javax.swing.JTextField textNumber;
    private javax.swing.JPasswordField textPassword;
    private javax.swing.JTextField textStreet;
    private javax.swing.JTextField textUser;
    private javax.swing.JLabel titleAddress;
    private javax.swing.JLabel titleUserRegister;
    // End of variables declaration//GEN-END:variables
}
