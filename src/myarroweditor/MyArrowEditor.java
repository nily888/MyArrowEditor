/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myarroweditor;

/**
 * The link to the MyArrow database
 */
import cleanuptables.CleanupTables;
import updatemobile.UpdateMobileSpeicher;
import mappinggid.MappingGIDSpeicher;
import db.MyArrowDB;

import javax.swing.DefaultListModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author René Düber
 */
public class MyArrowEditor extends javax.swing.JFrame {

    
    ArrayList<String[]> update_mobile = new ArrayList();

    /**
     * Creates new form ContactEditor
     */
    public MyArrowEditor() {
        System.out.println("=====================================================================");
        System.out.println("System: Constructor(): MyArrowEditor wird initialisiert...");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        comboTable = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jUpdate = new javax.swing.JButton();
        jCancel = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("MyArrow - Datenbrowser");

        comboTable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bogen", "Pfeil", "Ziel", "Parcour", "Schuetzen" }));
        comboTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTableActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jList1);

        jUpdate.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jUpdate.setText("Update");
        jUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jUpdateMouseClicked(evt);
            }
        });

        jCancel.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jCancel.setText("Cancel");
        jCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1031, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTableActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = new DefaultListModel();
        String strTable = (String) comboTable.getSelectedItem();
        ArrayList<String[]> tempArray = new CleanupTables().getWorklist(strTable);
        for (int n=0; n < tempArray.size(); n++) {
            model.addElement(
                    String.format("%1$-50s", tempArray.get(n)[0]) + "-" +
                    String.format("%1$-50s", tempArray.get(n)[1]) + "-" +
                    String.format("%1$-80s", tempArray.get(n)[2])
            );
        }
        jList1.setModel(model);
    }//GEN-LAST:event_comboTableActionPerformed

    private void jCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCancelMouseClicked
        // TODO add your handling code here:
        // this will hide and dispose the frame, so that the application quits by
        // itself if there is nothing else around. 
        setVisible(false);
        dispose();
        // if you have other similar frames around, you should dispose them, too.
        System.out.println("System: jCancelMouseClicked(): MyArrowEditor will be closed and shutdown !!");
        System.out.println("=====================================================================");
        // finally, call this to really exit. 143446
        // i/o libraries such as WiiRemoteJ need this. 
        // also, this is what swing does for JFrame.EXIT_ON_CLOSE
        System.exit(0);
    }//GEN-LAST:event_jCancelMouseClicked

    private void jUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jUpdateMouseClicked
        // TODO add your handling code here:
        String[] strElement;
        String strSQL;
        String strTable = (String) comboTable.getSelectedItem();
        List<String> aSelected =  new ArrayList<String>();
        ArrayList<String[]> mapping = null;
        aSelected = jList1.getSelectedValuesList();
        for (int n=0; n < aSelected.size();n++){
            strElement = aSelected.get(n).split("-");
            System.out.println("System: jUpdateMouseClicked(): Line      - " + aSelected.get(n).trim());
            System.out.println("System: jUpdateMouseClicked(): Tablename - " + strTable.toLowerCase());
            System.out.println("System: jUpdateMouseClicked(): old GID   - " + strElement[0].trim());
            System.out.println("System: jUpdateMouseClicked(): new GID   - " + strElement[1].trim());
            mapping = new MappingGIDSpeicher().loadMappingGIDDetails(strTable);
            for (int m=0; m< mapping.size(); m++) {
                
                if (strTable.toLowerCase().equals(mapping.get(m)[0].toLowerCase())) {
                    strSQL = "delete from " + mapping.get(m)[0].trim() + " " +
                             "where " + mapping.get(m)[1].trim() + "=" + strElement[0].trim() + ";";
                } else {
                    strSQL = "update " + mapping.get(m)[0].trim() + " " +
                             "set " + mapping.get(m)[1].trim() + "=" + strElement[1].trim() + " " +
                             "where " + mapping.get(m)[1].trim() + "=" + strElement[0].trim() + ";";
                }
                System.out.println("System: jUpdateMouseClicked(): strSQL    - " + strSQL);
                new MyArrowDB().executeSQL(strSQL);
                
                /**
                 * Entry to update the mobile phones
                 */
                new UpdateMobileSpeicher().insertUpdateMobile(
                        mapping.get(m)[0],
                        mapping.get(m)[1],
                        strElement[0],
                        strElement[1]
                    );
            }
            mapping = null;
        }
        comboTable.setSelectedIndex(0);
    }//GEN-LAST:event_jUpdateMouseClicked

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
            java.util.logging.Logger.getLogger(MyArrowEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyArrowEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyArrowEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyArrowEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyArrowEditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboTable;
    private javax.swing.JButton jCancel;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jUpdate;
    // End of variables declaration//GEN-END:variables
}
