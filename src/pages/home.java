/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import utils.koneksi;

/**
 *
 * @author Mahardicka
 */
public class home extends javax.swing.JFrame {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    String idKar;
    barang barangPage = new barang();
    kasir kasirPage = new kasir();
    karyawan karyawanPage = new karyawan();
    vendor vendorPage = new vendor();
//    member memberPage = new member();
    /**
     * Creates new form home
     */
    public home() {
        initComponents();
        krblogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/image/logo2.png")));
        // krblogo.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/res/image.png")).getImage().getScaledInstance(200, 50, Image.SCALE_DEFAULT)));
    }
    void setupDB(){
         // db
        koneksi DB = new koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
    }   
    
    public void onLoad(String id){
       idKar = id;
       sql = "SELECT nama,role,email FROM karyawan WHERE id='"+id+"'";
       setupDB();
        try {
            rs = stat.executeQuery(sql);
            if(rs.next()){          
                namaKar.setText(rs.getString("nama"));
                idKaryawan.setText(id);
                emailKar.setText(rs.getString("email"));
                roleKar.setText(rs.getString("role"));
            } else {
                JOptionPane.showMessageDialog(null,"error database get profile.");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "error "+e.getMessage());
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

        namaKar = new javax.swing.JLabel();
        emailKar = new javax.swing.JLabel();
        roleKar = new javax.swing.JLabel();
        idKaryawan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        krblogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        namaKar.setText("nama karyawan");
        getContentPane().add(namaKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        emailKar.setText("email karyawan");
        getContentPane().add(emailKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        roleKar.setText("role karyawan");
        getContentPane().add(roleKar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        idKaryawan.setText("id karyawan");
        getContentPane().add(idKaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        krblogo.setMaximumSize(new java.awt.Dimension(400, 317));
        krblogo.setMinimumSize(new java.awt.Dimension(400, 317));
        krblogo.setPreferredSize(new java.awt.Dimension(400, 317));
        getContentPane().add(krblogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 230, 230));

        jLabel1.setText("Nama :");
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel3.setText("ID        :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel4.setText("Email      :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, -1));

        jLabel5.setText("Jabatan :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jMenu1.setText("Form");

        jMenuItem1.setText("Admin");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Member");
        jMenu1.add(jMenuItem2);

        jMenuItem4.setText("Vendor");
        jMenu1.add(jMenuItem4);

        jMenuItem3.setText("Barang");
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Karyawan");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Transaksi");

        jMenuItem9.setText("Kasir");
        jMenu2.add(jMenuItem9);

        jMenuItem10.setText("Penerimaan Barang");
        jMenu2.add(jMenuItem10);

        jMenuItem11.setText("Barang Return");
        jMenu2.add(jMenuItem11);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Report");

        jMenuItem6.setText("Penjualan");
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Penerimaan Barang");
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Barang Return");
        jMenu3.add(jMenuItem8);

        jMenuItem12.setText("Vendor");
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        karyawanPage.onLoad(roleKar.getText());
        karyawanPage.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailKar;
    private javax.swing.JLabel idKaryawan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JLabel krblogo;
    private javax.swing.JLabel namaKar;
    private javax.swing.JLabel roleKar;
    // End of variables declaration//GEN-END:variables
}
