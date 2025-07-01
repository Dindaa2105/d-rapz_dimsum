/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import LoginForm.java.LoginForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame {
    JButton btnTransaksi = new JButton("Transaksi");
    JButton btnKeluar = new JButton("Keluar");
    JButton btnTambahuser = new JButton("Tambah User");

    public Dashboard(String namaUser) {
        setTitle("Dashboard - D'RAPZ_DIMSUM");
        setSize(500, 300); 
        setLayout(null);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Font fontTitle = new Font("Yu Gothic UI", Font.BOLD, 20);
        Font fontButton = new Font("Yu Gothic UI", Font.PLAIN, 14);

        JLabel lblSelamat = new JLabel("Selamat Datang, " + namaUser);
        lblSelamat.setFont(fontTitle);
        lblSelamat.setBounds(110, 20, 250, 30);

        btnTransaksi.setFont(fontButton);
        btnTransaksi.setBounds(70, 80, 130, 40); 

        btnKeluar.setFont(fontButton);
        btnKeluar.setBounds(150, 150, 130, 40);
        
        btnTambahuser.setFont(fontButton);
        btnTambahuser.setBounds(240, 80, 130, 40);
        
        add(btnTambahuser);
        add(lblSelamat);
        add(btnTransaksi);
        add(btnKeluar);

        // Tombol Transaksi
        btnTransaksi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new transaksiform(namaUser).setVisible(true);
                dispose();
            }
        });

        btnTambahuser.addActionListener(e -> {
        new Tambahuser().setVisible(true);
        });
        // Tombol Keluar / Logout
        btnKeluar.addActionListener(e -> {
            new LoginForm().setVisible(true);
            dispose();
        });

        getContentPane().setBackground(Color.decode("#FFF5E1"));


         Color warnaTombol = Color.decode("#D9885B");
         Color warnaTeks = Color.WHITE;

        btnTransaksi.setBackground(warnaTombol);
        btnTambahuser.setBackground(warnaTombol);
        btnKeluar.setBackground(warnaTombol);

        btnTransaksi.setForeground(warnaTeks);
        btnTambahuser.setForeground(warnaTeks);
        btnKeluar.setForeground(warnaTeks);
        
        setVisible(true);
    }
}

   
