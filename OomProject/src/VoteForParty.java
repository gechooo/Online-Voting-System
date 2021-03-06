
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nitin
 */
public class VoteForParty extends javax.swing.JFrame {

    /**
     * Creates new form VoteForParty
     */
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement  pst = null;
    public VoteForParty() {
        initComponents();
        setSize(500,450);
        setLocationRelativeTo(null);
        conn = Javaconnect.dbConnector();
        Combobox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PartySelection = new javax.swing.JComboBox<>();
        Vote = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 465));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PartySelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PartySelectionActionPerformed(evt);
            }
        });
        getContentPane().add(PartySelection, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 160, 40));

        Vote.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Vote.setIcon(new javax.swing.ImageIcon("C:\\Users\\Nitin\\Downloads\\vote-badge-for-political-elections.png")); // NOI18N
        Vote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoteActionPerformed(evt);
            }
        });
        getContentPane().add(Vote, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 80, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Nitin\\Desktop\\OOM PROJECT\\Images\\Background.jpg")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PartySelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PartySelectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PartySelectionActionPerformed

    private void VoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoteActionPerformed
        // TODO add your handling code here:
        String  s1 = (String) PartySelection.getSelectedItem();
        int votes=0;
        try
           {
                String query = "select * from CandidateList";
                pst =  conn.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next())
                {
                    if(rs.getString("Party").equals(s1))
                    {
                        votes = rs.getInt("Votes");
                    }
                }
           }
           catch(Exception e)
           {
               
           }
        votes++;
        //String s = ((ComboBoxItem)PartySelection.SelectedItem).Content.ToString();
        try
           {
               String query = "update CandidateList set Votes='"+votes+""+"' where Party='"+s1+"' "; 
               pst =  conn.prepareStatement(query);
               pst.execute();
           }
           catch(Exception e)
           {
               
           }
        JOptionPane.showMessageDialog(null,"You have casted Your Vote");
        Login vf = new Login();
        vf.setVisible(true);
        dispose();
    }//GEN-LAST:event_VoteActionPerformed

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
            java.util.logging.Logger.getLogger(VoteForParty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VoteForParty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VoteForParty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VoteForParty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VoteForParty().setVisible(true);
            }
        });
    }
    public void Combobox()
    {
        try
        {
            String query = "Select * from CandidateList";
            pst =  conn.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next())
            {
               String party = rs.getString("Party");
               PartySelection.addItem(party);
            }
            
        }
        catch(Exception e)
        {
            
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> PartySelection;
    private javax.swing.JButton Vote;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    
}
