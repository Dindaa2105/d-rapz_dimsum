/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;
import config.config;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Kharisma.S
 */
public class Tambahuser extends JFrame {
    JTextField txtUsername = new JTextField();
    JPasswordField txtPassword = new JPasswordField();
    JComboBox<String> cbRole = new JComboBox<>(new String[] {"admin", "kasir"});
    JButton btnSimpan = new JButton("Simpan");

    public Tambahuser() {
        setTitle("Tambah User");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#FFF5E1"));
        
        Color warnaTombol = Color.decode("#D9885B");

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        JLabel lblRole = new JLabel("Role:");

        lblUsername.setBounds(30, 30, 100, 25);
        txtUsername.setBounds(140, 30, 160, 25);

        lblPassword.setBounds(30, 70, 100, 25);
        txtPassword.setBounds(140, 70, 160, 25);

        lblRole.setBounds(30, 110, 100, 25);
        cbRole.setBounds(140, 110, 160, 25);

        btnSimpan.setBounds(110, 160, 100, 35);
        btnSimpan.setBackground(warnaTombol);
        btnSimpan.setForeground(Color.WHITE);

        add(lblUsername); 
        add(txtUsername);
        add(lblPassword); 
        add(txtPassword);
        add(lblRole); 
        add(cbRole);
        add(btnSimpan);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        btnSimpan.addActionListener(e -> simpanUser());
    }

    private void simpanUser() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String role = cbRole.getSelectedItem().toString();
        
        if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom wajib diisi!");
        return;
    }

        try {
            Connection conn = config.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO user_login2 (username, password, role) VALUES (?, ?, ?)"
            );
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            int sukses = ps.executeUpdate();
            if (sukses > 0) {
                JOptionPane.showMessageDialog(this, "User berhasil ditambahkan.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan user.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new Tambahuser();
    }
}
