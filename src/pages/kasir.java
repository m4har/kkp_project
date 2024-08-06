/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.conn;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import utils.koneksi;

/**
 *
 * @author mahar
 */
public class kasir extends javax.swing.JFrame {
    Connection con;
    Statement st;
    ResultSet rs;
    int totalBeli;
    DefaultTableModel tabMode;
    String idkasir;

    /**
     * Creates new form kasir
     */
    public kasir() {
        initComponents();
        generateIdOrder();
        initTab();
    }
    
     void initTab(){
        Object[] baris = {"ID Barang","Nama Barang","Jumlah","Harga","Total"};
        tabMode = new DefaultTableModel(null, baris);
        tborder.setModel(tabMode);
        hitungTotal();
    }
     
    void setupDB(){
         // db
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        st = DB.stm;
    }
     
    void cetak(String idOrder){
        try {
            setupDB();
            String path = "src/report/kasir.jrxml";
            HashMap parameter =  new HashMap();
            parameter.put("ID_ORDER",idOrder);
            JasperReport jasperReport = JasperCompileManager.compileReport(path);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter,con);
            JasperViewer.viewReport(print,false);
        } catch (Exception e) {
            System.out.println("cetak "+e);
        }
    
    }
    public void setKasir(String id){
        idkasir = id;
        String query = "select nama from karyawan where id = '"+id+"'";
        setupDB();
         try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                nmkasir.setText(rs.getString(1));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("get nama kasir "+e);
        }
    }
    
    public String convertRupiah(int intPrice) {
        Locale localId = new Locale("in", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(localId);
        String strFormat = formatter.format(intPrice);
        return strFormat;
    }
   
    
    void generateIdOrder(){

        String query = "select COUNT(*) from transaksi";
        Number count;
        try {
            setupDB();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                count = Integer.parseInt(rs.getString(1))+1;
                txtidorder.setText("o"+count);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("generate id"+e);
        }
    }
    
    void txtClear(){
        txtidbarang.setText("");
        txtnamabarang.setText("");
        txtqty.setText("");
        txtharga.setText("");
        txtbayar.setText("");
        txtidbarang.setEditable(true);
    }

    void hitungTotal(){
        int total = 0;
        for(int i = 0; i<tborder.getRowCount();i++){
            int totalPerRow = Integer.valueOf(tborder.getValueAt(i, 4).toString());
            total += totalPerRow;
        }
        totalBeli = total;
        lbtotal.setText(convertRupiah(total));
    }

    void newOrder(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // Current timestamp
        String insertQuery = "INSERT INTO transaksi (id, tanggal, total, idMember, idKaryawan) VALUES (?, ?, ?, ?, ?)";
        
          try {
            setupDB();
            // Prepare the statement
            PreparedStatement statement = con.prepareStatement(insertQuery);
            // Set the parameters
            statement.setString(1, txtidorder.getText());
            statement.setTimestamp(2, timestamp);
            statement.setInt(3, totalBeli);
            statement.setString(4, txtidmember.getText());
            statement.setString(5, idkasir);
            
            // Execute the query
            int rowsInserted = statement.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
     
            generateIdOrder();
            conn.closeConnection();
        } catch (Exception e) {
            System.out.println("create pelanggan"+e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtidbarang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnamabarang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtqty = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tborder = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lbtotal = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtbayar = new javax.swing.JTextField();
        btnbayar = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnkeluar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtidmember = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtnamamember = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtidorder = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nmkasir = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kasir");

        jLabel1.setText("ID Barang");

        txtidbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidbarangActionPerformed(evt);
            }
        });
        txtidbarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidbarangKeyPressed(evt);
            }
        });

        jLabel2.setText("Nama Barang");

        txtnamabarang.setEditable(false);
        txtnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamabarangActionPerformed(evt);
            }
        });

        jLabel3.setText("Harga Barang");

        txtharga.setEditable(false);
        txtharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargaActionPerformed(evt);
            }
        });

        jLabel4.setText("QTY Barang");

        txtqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtqtyActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("List Pembelian"));

        tborder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama Barang", "Jumlah", "Harga", "Total"
            }
        ));
        jScrollPane1.setViewportView(tborder);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Bayar");

        lbtotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbtotal.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("TOTAL");

        txtbayar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbayarActionPerformed(evt);
            }
        });

        btnbayar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnbayar.setText("Bayar");
        btnbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbayarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtbayar)
                            .addComponent(btnbayar, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbtotal)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );

        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnhapus.setText("Hapus");

        btnkeluar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnkeluar.setText("Keluar");
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("PEMBAYARAN");

        jLabel9.setText("ID Member");

        txtidmember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidmemberActionPerformed(evt);
            }
        });
        txtidmember.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidmemberKeyPressed(evt);
            }
        });

        jLabel10.setText("Nama Member");

        txtnamamember.setEditable(false);
        txtnamamember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamamemberActionPerformed(evt);
            }
        });

        jLabel11.setText("ID Order");

        txtidorder.setEditable(false);
        txtidorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidorderActionPerformed(evt);
            }
        });

        jLabel6.setText("Nama Kasir");

        nmkasir.setText("nama_kasir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnamabarang, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(txtidbarang)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnamamember)
                                    .addComponent(txtidmember, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(btntambah)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnhapus))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtidorder, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nmkasir)
                                            .addComponent(jLabel6)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(jLabel8)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtidmember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtidbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnkeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtidorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nmkasir))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtnamamember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtidbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidbarangActionPerformed

    private void txtnamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamabarangActionPerformed

    private void txthargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargaActionPerformed

    private void txtqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtqtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtqtyActionPerformed

    private void txtbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbayarActionPerformed

    private void txtidmemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidmemberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidmemberActionPerformed

    private void txtnamamemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamamemberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamamemberActionPerformed

    private void txtidorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidorderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidorderActionPerformed

    private void txtidbarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidbarangKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String searchItem = txtidbarang.getText();
            String query = "SELECT nama,hargaJual FROM barang where id = '"+searchItem+"' LIMIT 1";
            try {
                st = con.createStatement();
                rs = st.executeQuery(query);
                if(rs.next() == false ){
                    JOptionPane.showMessageDialog(null, "barang tidak ditemukan", "error", JOptionPane.INFORMATION_MESSAGE);
                    txtnamabarang.setText("");
                    txtharga.setText("");
                } else {
                    do {
                        txtnamabarang.setText(rs.getString(1));
                        txtharga.setText(rs.getString(2));
                        txtidbarang.setEditable(false);
                    } while(rs.next());
                  
                }
                
                rs.close();
            } catch (Exception e) {
                System.out.println("get barang"+e);
            }
        }
    }//GEN-LAST:event_txtidbarangKeyPressed

    private void txtidmemberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidmemberKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String searchItem = txtidmember.getText();
            String query = "SELECT nama FROM member where id = '"+searchItem+"' LIMIT 1";
            try {
                st = con.createStatement();
                rs = st.executeQuery(query);
                if(rs.next() == false ){
                    JOptionPane.showMessageDialog(null, "member tidak ditemukan", "error", JOptionPane.INFORMATION_MESSAGE);
                    txtnamamember.setText("");
                    txtidmember.setText("");
                } else {
                    do {
                        txtnamamember.setText(rs.getString(1));
                        txtidmember.setEditable(false);
                    } while(rs.next());
                  
                }
                
                rs.close();
            } catch (Exception e) {
                System.out.println("get member"+e);
            }
        }
    }//GEN-LAST:event_txtidmemberKeyPressed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
        int qty = Integer.valueOf(txtqty.getText());
        int harga = Integer.valueOf(txtharga.getText());
        int total = qty * harga;
        tabMode.addRow(new Object[]{
                        txtidbarang.getText(),
                        txtnamabarang.getText(),
                        txtqty.getText(),
                        txtharga.getText(),
                        total
                    });
        tborder.setModel(tabMode);
        hitungTotal();
        txtClear();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbayarActionPerformed
        // TODO add your handling code here:
        try {
            int bayar = Integer.valueOf(txtbayar.getText());
            String idOrder = txtidorder.getText();
        if(bayar<totalBeli){
            JOptionPane.showMessageDialog(null, "Uang Kurang", "error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            newOrder();
            setupDB();
            for(int i = 0; i<tborder.getRowCount();i++){
                String idBarang = tborder.getValueAt(i, 0).toString();
                int jumlah = Integer.valueOf(tborder.getValueAt(i, 2).toString());
                int hargaJual = Integer.valueOf(tborder.getValueAt(i,3).toString());
                String queryInsertDetailTransaksi = "INSERT INTO detailtransaksi (idTransaksi, idBarang, hargaJual,jumlah) VALUES (?, ?, ?, ?)";
                String queryUpdateStok = "UPDATE barang SET stok = stok - ? WHERE id = ?";
                try {
                    con.setAutoCommit(false);
                    
                     // insert detail
                     PreparedStatement insertStatementDetailTransaksi = con.prepareStatement(queryInsertDetailTransaksi);
                     insertStatementDetailTransaksi.setString(1, idOrder);
                     insertStatementDetailTransaksi.setString(2, idBarang);
                     insertStatementDetailTransaksi.setInt(3, hargaJual);
                     insertStatementDetailTransaksi.setInt(4, jumlah);
                     insertStatementDetailTransaksi.executeUpdate();
                     // update stok
                     PreparedStatement updateStatement = con.prepareStatement(queryUpdateStok);
                     updateStatement.setInt(1,jumlah);
                     updateStatement.setString(2, idBarang);
                     updateStatement.executeUpdate();
                    // Commit transaction
                     con.commit();
                     System.out.println("Transaction committed successfully.");

                    
                 } catch (Exception e) {
                     System.out.println("create detailtransaksi"+e);
                 }
            
            }
            cetak(idOrder);
            initTab();
            txtClear();
            txtidmember.setEditable(true);
            txtidmember.setText("");
            txtnamamember.setText("");
            // Close the connection
            conn.closeConnection();
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Input Pembayaran Tidak Sesuai", "error", JOptionPane.INFORMATION_MESSAGE);
        }
//         TableColumn[] orderList = new TableColumn[tborder.getColumnCount()];
//         for (int c = 0; c < tborder.getRowCount(); c++) {
//             String id,nama,jumlah,harga,tot;
//             for (int d = 0; d < tborder.getColumnCount(); d++) {
//                 String value = tborder.getModel().getValueAt(c, d).toString();
//                 System.out.println(value);
//             }
//             
//             newOrder();
//            setupDB();
//            String query = "INSERT INTO `detailtransaksi` (`idTransaksi`, `idBarang`, `hargaJual`, `jumlah`) VALUES ('"+txtidorder.getText()+"', '"+txtidbarang.getText()+"', '"+txtharga.getText()+"', '"+txtqty.getText()+"');";
//            try {
//              st = con.createStatement();
//              st.executeUpdate(query);
//              st.close();
//              txtClear();
//          } catch (Exception e) {
//              System.out.println("create detailtransaksi"+e);
//          }
//        }
    }//GEN-LAST:event_btnbayarActionPerformed

    private void btnkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnkeluarActionPerformed

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
            java.util.logging.Logger.getLogger(kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbayar;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton btntambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbtotal;
    private javax.swing.JLabel nmkasir;
    private javax.swing.JTable tborder;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtidbarang;
    private javax.swing.JTextField txtidmember;
    private javax.swing.JTextField txtidorder;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtnamamember;
    private javax.swing.JTextField txtqty;
    // End of variables declaration//GEN-END:variables
}
