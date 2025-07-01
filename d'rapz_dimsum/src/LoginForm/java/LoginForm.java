/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LoginForm.java;

import config.config;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import view.Dashboard;

public class LoginForm extends JFrame {
    JTextField txtUser = new JTextField(15);
    JPasswordField txtPass = new JPasswordField(15);
    JButton btnLogin = new JButton("Login");

    public LoginForm() {
    setTitle("D'RAPZ_DIMSUM");
    setSize(450, 300);
    setLayout(null);
    getContentPane().setBackground(Color.decode("#FFF5E1"));
    setLocationRelativeTo(null);

    JLabel lblUser = new JLabel("Username:");
    JLabel lblPass = new JLabel("Password:");
    txtUser = new JTextField(20);
    txtPass = new JPasswordField(20);
    btnLogin = new JButton("Login");
    JCheckBox showPassword = new JCheckBox("👁");

    Font fontLabel = new Font("Yu Gothic UI", Font.BOLD, 16);
    Font fontInput = new Font("Yu Gothic UI", Font.BOLD, 14);

    lblUser.setFont(fontLabel);
    lblPass.setFont(fontLabel);
    txtUser.setFont(fontInput);
    txtPass.setFont(fontInput);
    btnLogin.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
    
    Color warnaTombol = Color.decode("#D9885B");
    btnLogin.setBackground(warnaTombol);
    btnLogin.setForeground(Color.WHITE);
    showPassword.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
    showPassword.setBackground(Color.decode("#FFF5E1"));
    showPassword.setFocusable(false);

    
    lblUser.setBounds(50, 50, 100, 30);
    txtUser.setBounds(160, 50, 200, 30);

    lblPass.setBounds(50, 100, 100, 30);
    txtPass.setBounds(160, 100, 160, 30);
    showPassword.setBounds(330, 100, 50, 30); 

    btnLogin.setBounds(160, 160, 100, 35);

    
    add(lblUser);
    add(txtUser);
    add(lblPass);
    add(txtPass);
    add(showPassword);
    add(btnLogin);

    
    txtPass.setEchoChar('*');

    
    showPassword.addActionListener(e -> {
        if (showPassword.isSelected()) {
            txtPass.setEchoChar((char) 0); 
        } else {
            txtPass.setEchoChar('*'); 
        }
    });

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // Fungsi login
    btnLogin.addActionListener((ActionEvent e) -> {
        login();
    });
}

    private void login() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());
        String inputPass = new String(txtPass.getPassword());
       


        try {
            Connection conn = config.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM user_login2 WHERE username=? AND password=?"
            );
            ps.setString(1, user);
            ps.setString(2, pass);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Berhasil!");
                new Dashboard(user).setVisible(true); // Lanjut ke menu utama
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username/Password salah!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
    new LoginForm();
}
}
