/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;
import config.config;
import java.awt.Color;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
/**
 *
 * @author Kharisma.S
 */
public class transaksiform extends JFrame {
    int totalHarga = 0;
    StringBuilder struk = new StringBuilder();
    
    JComboBox<String> cbMenu = new JComboBox<>();
    JTextField txtJumlah = new JTextField();
    JTextArea txtOutput = new JTextArea();
    
    JButton btnTambah = new JButton("Tambah");
    JButton btnSimpan = new JButton("Simpan");
    JButton btnCetak = new JButton("Cetak Struk");
    JButton btnHitung = new JButton("Hitung");

    DefaultListModel<String> listModel = new DefaultListModel<>();

    Vector<Integer> menuIdList = new Vector<>();
    Vector<Integer> jumlahList = new Vector<>();
    Vector<Integer> subtotalList = new Vector<>();
    

    public transaksiform(String namaUser) {
        setTitle("Transaksi - D'RAPZ_DIMSUM");
        setSize(400, 430);
        setLayout(null);
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(Color.decode("#FFF5E1"));
        
        JLabel lblMenu = new JLabel("Menu:");
        JLabel lblJumlah = new JLabel("Jumlah:");
        
        JLabel lblTotal = new JLabel("Total:");
        JTextField txtTotal = new JTextField();
        JLabel lblBayar = new JLabel("Bayar:");
        JTextField txtBayar = new JTextField();
        JLabel lblKembalian = new JLabel("Kembalian:");
        JTextField txtKembalian = new JTextField();
        JButton btnHitung = new JButton("Hitung");
        btnTambah = new JButton("Tambah");
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtOutput);

        lblMenu.setBounds(30, 20, 100, 25);
        cbMenu.setBounds(120, 20, 200, 25);

        lblJumlah.setBounds(30, 60, 100, 25);
        txtJumlah.setBounds(120, 60, 50, 25);
        btnTambah.setBounds(180, 60, 100, 25);

        txtOutput.setBounds(30, 100, 320, 130);
        btnSimpan.setBounds(30, 340, 150, 30);
        btnCetak.setBounds(200, 340, 150, 30);
        
        lblTotal.setBounds(30, 240, 100, 25);
        txtTotal.setBounds(120, 240, 150, 25);
        txtTotal.setEditable(false); 

        lblBayar.setBounds(30, 270, 100, 25);
        txtBayar.setBounds(120, 270, 150, 25);

        lblKembalian.setBounds(30, 300, 100, 25);
        txtKembalian.setBounds(120, 300, 150, 25);
        txtKembalian.setEditable(false);

        btnHitung.setBounds(280, 270, 80, 25);
        
        
        add(lblMenu); add(cbMenu);
        add(lblJumlah); add(txtJumlah); add(btnTambah);
        add(scroll);
        add(lblTotal); add(txtTotal);
        add(lblBayar); add(txtBayar);
        add(lblKembalian); add(txtKembalian);
        add(btnHitung); add(btnSimpan); add(btnCetak);
        add(txtOutput); add(btnSimpan);

        add(lblTotal);
        add(txtTotal);
        add(lblBayar);
        add(txtBayar);
        add(lblKembalian);
        add(txtKembalian);
        add(btnHitung);
        
        
        btnTambah.addActionListener(e -> {
            
    try {
        String menu = cbMenu.getSelectedItem().toString();
        int jumlah = Integer.parseInt(txtJumlah.getText());

        int harga = 0;
        switch (menu) {
            case "Dimsum Kukus":
                harga = 10000;
                break;
            case "Dimsum Bakar":
                harga = 15000;
                break;
            case "Dimsum Mentai":
                harga = 25000;
                break;
            default:
                harga = 0;
        }

        int subtotal = harga * jumlah;
        totalHarga += subtotal;

        String detail = menu + " x" + jumlah + " = " + subtotal + "\n";
        txtOutput.append(detail);
        struk.append(detail); // simpan struk

        txtTotal.setText(String.valueOf(totalHarga));
        txtJumlah.setText("");
        txtJumlah.requestFocus();

    } catch (NumberFormatException ex) {
         
    }
});

        
        btnHitung.addActionListener(e -> {
    try {
        int bayar = Integer.parseInt(txtBayar.getText());
        int kembali = bayar - totalHarga;

        if (kembali < 0) {
            JOptionPane.showMessageDialog(this, "Uang tidak cukup!");
            txtKembalian.setText("0");
            
        } else {
            
            txtKembalian.setText(String.valueOf(kembali));
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Masukkan jumlah uang yang valid!");
    }
});
        
        btnCetak.addActionListener(e -> {
    String total = txtTotal.getText();
    String bayar = txtBayar.getText();
    String kembali = txtKembalian.getText();

    String hasilStruk = "====== STRUK PEMBELIAN ======\n" + struk.toString() +
        "-----------------------------\n" +
        "Total    : Rp" + total + "\n" +
        "Bayar    : Rp" + bayar + "\n" +
        "Kembali  : Rp" + kembali + "\n" +
        "=============================";

    JOptionPane.showMessageDialog(this, hasilStruk);
});
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        

        tampilMenu();

        btnTambah.addActionListener(e -> tambahItem());
        btnSimpan.addActionListener(e -> simpanTransaksi(namaUser));
        btnCetak.addActionListener(e -> cetakStruk(namaUser));
        btnSimpan.setBackground(Color.decode("#D9885B"));
        btnSimpan.setForeground(Color.WHITE);
        btnCetak.setBackground(Color.decode("#D9885B"));
        btnCetak.setForeground(Color.WHITE);

    }

    private void tampilMenu() {
        try {
            Connection conn = config.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM menu");

            while (rs.next()) {
                cbMenu.addItem(rs.getString("nama_menu"));
                menuIdList.add(rs.getInt("kode_menu"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tambahItem() {
        try {
            int index = cbMenu.getSelectedIndex();
            int id_menu = menuIdList.get(index);
            String nama = (String) cbMenu.getSelectedItem();
            int jumlah = Integer.parseInt(txtJumlah.getText());

            Connection conn = config.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT harga FROM menu WHERE kode_menu=?");
            ps.setInt(1, id_menu);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int harga = rs.getInt("harga");
                int subtotal = harga * jumlah;

                jumlahList.add(jumlah);
                subtotalList.add(subtotal);

                txtOutput.append(nama + " x" + jumlah + " = " + subtotal + "\n");
                totalHarga += subtotal;
            }

            txtJumlah.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid");
        }
    }

    private void simpanTransaksi(String namaUser) {
        try {
            Connection conn = config.getConnection();

            // Simpan transaksi utama
            PreparedStatement ps1 = conn.prepareStatement(
                "INSERT INTO transaksi(nama_user, total) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, namaUser);
            ps1.setInt(2, totalHarga);
            ps1.executeUpdate();

            ResultSet rs = ps1.getGeneratedKeys();
            int id_transaksi = 0;
            if (rs.next()) {
                id_transaksi = rs.getInt(1);
            }

            // Simpan detail transaksi
            for (int i = 0; i < menuIdList.size(); i++) {
                if (i < jumlahList.size()) {
                    PreparedStatement ps2 = conn.prepareStatement(
                        "INSERT INTO detail_transaksi(id_transaksi, id_menu, jumlah, subtotal) VALUES(?, ?, ?, ?)");
                    ps2.setInt(1, id_transaksi);
                    ps2.setInt(2, menuIdList.get(i));
                    ps2.setInt(3, jumlahList.get(i));
                    ps2.setInt(4, subtotalList.get(i));
                    ps2.executeUpdate();
                }
            }

            JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan!");
dispose();
new Dashboard(namaUser).setVisible(true);

} catch (Exception e) {
    e.printStackTrace();
}
}

private void cetakStruk(String namaUser) {
    StringBuilder struk = new StringBuilder();
    struk.append("=========== D'RAPZ DIMSUM ===========\n");
    struk.append("Kasir      : ").append(namaUser).append("\n");
    struk.append("-------------------------------------\n");

    for (int i = 0; i < jumlahList.size(); i++) {
        String namaMenu = cbMenu.getItemAt(i);
        int jumlah = jumlahList.get(i);
        int subtotal = subtotalList.get(i);
        struk.append(String.format("%s x%d = %d\n", namaMenu, jumlah, subtotal));
    }

    struk.append("-------------------------------------\n");
    struk.append("Total Bayar: ").append(totalHarga).append("\n");
    struk.append("=====================================\n");
    struk.append("       Terima Kasih atas pesanannya!\n");

    JOptionPane.showMessageDialog(this, struk.toString(), "Struk Pembelian", JOptionPane.INFORMATION_MESSAGE);
}

    private void formatted(String toString, String total, String bayar, String kembali) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}



