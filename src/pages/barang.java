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
import utils.koneksi;

/**
 *
 * @author Mahardicka
 */
public class barang extends javax.swing.JFrame {
    Connection con;
    Statement st;
    ResultSet rs;
    String sql,selectRole;
    /**
     * Creates new form barang
     */
    public barang() {
        initComponents();
        generateIdBarang();
        getDataTable();
        cbkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Tinju", "Bola", "Silat"}));
        
    }
    
    void setupDB(){
         // db
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        st = DB.stm;
    }
    
    void txtClear(){
        txtidvendor.setText("");
        txtidvendor.setEditable(true);
        txtnamabarang.setText("");
        txtnamavendor.setText("");
        txtstock.setText("");
        txtbeli.setText("");
        txtjual.setText("");
    }
    
    void generateIdBarang(){
        setupDB();
        String query = "select COUNT(*) from barang";
        Number count;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                count = Integer.parseInt(rs.getString(1))+1;
                txtidbarang.setText("b"+count);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("generate id"+e);
        }
    }
    
    public void getDataTable(){
        setupDB();
        String searchItem = txtcari.getText();
        String query = "SELECT barang.id as id, barang.nama as namaBarang, barang.kategori as kategori, barang.stok as stok, vendor.nama as namaVendor, barang.hargaBeli as hargaBeli, barang.hargaJual as hargaJual FROM barang inner join vendor on barang.idVendor = vendor.id where barang.id like '%"+searchItem+"%' or barang.nama like '%"+searchItem+"%' order by id asc";
        Object[] baris = {"ID","Nama","Kategori","Stok","Vendor","Harga Beli","Harga Jual"};
        DefaultTableModel tabmode = new DefaultTableModel(null, baris);
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                    tabmode.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                    });
            }
            rs.close();
            tbbarang.setModel(tabmode);
        } catch (Exception e) {
            System.out.println("tabel kar : "+e);
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
        cbkategori = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtbeli = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtjual = new javax.swing.JTextField();
        btntambah = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbbarang = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        btnkeluar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtidvendor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtnamavendor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Barang");

        jLabel1.setText("ID Barang");

        txtidbarang.setEditable(false);
        txtidbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidbarangActionPerformed(evt);
            }
        });

        jLabel2.setText("Nama Barang");

        txtnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamabarangActionPerformed(evt);
            }
        });

        jLabel3.setText("Kategori Barang");

        cbkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("ID Vendor");

        jLabel5.setText("Jumlah Stock");

        txtstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstockActionPerformed(evt);
            }
        });

        jLabel6.setText("Harga Beli");

        txtbeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbeliActionPerformed(evt);
            }
        });

        jLabel7.setText("Harga Jual");

        txtjual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjualActionPerformed(evt);
            }
        });

        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnbatal.setText("Batal");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        tbbarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Kategori", "Stock", "Vendor", "Harga Beli", "Harga Jual"
            }
        ));
        jScrollPane1.setViewportView(tbbarang);

        jLabel8.setText("Cari Barang");

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });

        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        btnkeluar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnkeluar.setText("Keluar");
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("DATA BARANG");

        txtidvendor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidvendorKeyPressed(evt);
            }
        });

        jLabel10.setText("Nama Vendor");

        txtnamavendor.setEditable(false);
        txtnamavendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamavendorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btncari))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLabel4))
                                            .addComponent(txtidbarang, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                            .addComponent(txtidvendor))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel10)
                                                    .addComponent(txtnamavendor, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(txtbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel3)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(22, 22, 22)
                                                        .addComponent(jLabel7))
                                                    .addComponent(txtjual, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnbatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btntambah, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(cbkategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(txtbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtjual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnbatal)
                                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtidbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtidvendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnamavendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addGap(26, 26, 26)))))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncari))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btntambah)
                        .addGap(108, 108, 108)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnkeluar, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
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

    private void txtstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstockActionPerformed

    private void txtbeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbeliActionPerformed

    private void txtjualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjualActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
        String idBarang = txtidbarang.getText();
        String idVendor = txtidvendor.getText();
        String namaVendor = txtnamavendor.getText();
        if(namaVendor.isEmpty()){
            JOptionPane.showMessageDialog(null, "Vendor belum terisi", "error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String namaBarang = txtnamabarang.getText();
        if(namaBarang.isEmpty()){
            JOptionPane.showMessageDialog(null, "nama barang belum terisi", "error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String kategori = cbkategori.getSelectedItem().toString();
        
        if(txtbeli.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "harga barang belum terisi", "error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Number hargaBeli = Integer.valueOf(txtbeli.getText());
         
        if(txtstock.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "stok belum terisi", "error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Number stok = Integer.valueOf(txtstock.getText());
        
        if(txtjual.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "harga jual belum terisi", "error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Number hargaJual = Integer.valueOf(txtjual.getText());
        
        // query add barang
        setupDB();
        String query = "INSERT INTO `barang` (`id`, `nama`, `kategori`, `stok`, `hargaBeli`, `hargaJual`, `idVendor`) VALUES ('"+idBarang+"', '"+namaBarang+"', '"+kategori+"', '"+stok+"', '"+hargaBeli+"', '"+hargaJual+"', '"+idVendor+"')";
        try {
            st = con.createStatement();
            st.executeUpdate(query);
            generateIdBarang();
            getDataTable();
            st.close();
            txtClear();
        } catch (Exception e) {
            System.out.println("create pelanggan"+e);
        }
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbatalActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        getDataTable();
    }//GEN-LAST:event_btncariActionPerformed

    private void btnkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnkeluarActionPerformed

    private void txtnamavendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamavendorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamavendorActionPerformed

    private void txtidvendorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidvendorKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            setupDB();
            String searchItem = txtidvendor.getText();
            String query = "SELECT nama FROM vendor where id = '"+searchItem+"' LIMIT 1";
            try {
                System.out.println(txtidvendor.getText());
                st = con.createStatement();
                rs = st.executeQuery(query);
                if(rs.next() == false ){
                    System.out.println("get vendor null");
                    JOptionPane.showMessageDialog(null, "Vendor tidak ditemukan", "error", JOptionPane.INFORMATION_MESSAGE);
                    txtnamavendor.setText("");
                } else {
                    do {
                        txtnamavendor.setText(rs.getString(1));
                        txtidvendor.setEditable(false);
                    } while(rs.next());
                  
                }
                
                rs.close();
            } catch (Exception e) {
                System.out.println("get vendor"+e);
            }

        }
    }//GEN-LAST:event_txtidvendorKeyPressed

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            getDataTable();
        }
    }//GEN-LAST:event_txtcariKeyPressed

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
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton btntambah;
    private javax.swing.JComboBox<String> cbkategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbbarang;
    private javax.swing.JTextField txtbeli;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtidbarang;
    private javax.swing.JTextField txtidvendor;
    private javax.swing.JTextField txtjual;
    private javax.swing.JTextField txtnamabarang;
    private javax.swing.JTextField txtnamavendor;
    private javax.swing.JTextField txtstock;
    // End of variables declaration//GEN-END:variables
}
