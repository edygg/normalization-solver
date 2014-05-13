
package edu.unitec.normalization;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Edilson
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfRelationCK = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfFunctionalDependenciesCK = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taCandidateKeys = new javax.swing.JTextArea();
        btnSolveCK = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Relation:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Functional Dependencies:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Candidate Keys:");

        taCandidateKeys.setEditable(false);
        taCandidateKeys.setColumns(20);
        taCandidateKeys.setRows(3);
        jScrollPane1.setViewportView(taCandidateKeys);

        btnSolveCK.setText("Solve");
        btnSolveCK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolveCKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfRelationCK)
                            .addComponent(tfFunctionalDependenciesCK, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(0, 265, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSolveCK)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfRelationCK, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfFunctionalDependenciesCK, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSolveCK)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Candidate Keys", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Normalization", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSolveCKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolveCKActionPerformed
        try {
            taCandidateKeys.setText("");
            this.currentRelation = parseRelation();
            List<String> keys=this.currentRelation.candidateKeys();
            System.out.println("Candidate Keys:\n\n");
            for (int i = 0; i < keys.size(); i++) {
                System.out.println((i+1)+". "+keys.get(i));
                taCandidateKeys.append(keys.get(i)+"\n");
            }
        } catch (InvalidDataException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSolveCKActionPerformed

    public Relation parseRelation() throws InvalidDataException {
        String relationString = this.tfRelationCK.getText();
        String functionalDependenciesString = this.tfFunctionalDependenciesCK.getText();
        
        if (relationString.isEmpty() || !relationString.matches(Relation.RELATION_REGEX)) {
            throw new InvalidDataException("Invalid relation. Please try again.");
        }
        if (functionalDependenciesString.isEmpty() || !functionalDependenciesString.matches(FunctionalDependency.FUNCTIONAL_DEPENDENCY_REGEX)) {
            throw new InvalidDataException("Invalid functional dependencies. Please try again.");
        }
        
        Relation neoRelation = new Relation();
        
        // Parsing Relation
        relationString = relationString.replaceAll("[r|R][(]", "");
        relationString = relationString.replaceAll("[)]", "");
        
        String[] individualFields = relationString.split(",");
        
        for (int i = 0; i < individualFields.length; i++) {
            List<Character> neoList = new ArrayList();
            neoList.add(individualFields[i].charAt(0));
            neoRelation.addField(new Field(neoList, false));
        }
        
        // Parsing Functional Dependencies
        String[] individualFunctionalDependencies = functionalDependenciesString.split(",");
        for (int i = 0; i < individualFunctionalDependencies.length; i++) {
            String[] fdParts = individualFunctionalDependencies[i].split("[\\-][\\->]");
            List<Field> right = new ArrayList();
            List<Field> left = new ArrayList();
            
            for (int j = 0; j < fdParts[0].length(); j++) {
                List<Character> neoList = new ArrayList();
                neoList.add(fdParts[0].charAt(j));
                
                Field neoField = new Field(neoList, false);
                
                if (!neoRelation.hasField(neoField)) {
                    throw new InvalidDataException("A field does not exist in the relation.");
                } 
                
                left.add(neoField);
            }
            
            for (int j = 0; j < fdParts[1].length(); j++) {
                List<Character> neoList = new ArrayList();
                neoList.add(fdParts[1].charAt(j));

                Field neoField = new Field(neoList, false);

                if (!neoRelation.hasField(neoField)) {
                    throw new InvalidDataException("A field does not exist in the relation.");
                }

                right.add(neoField);
            }
            
            neoRelation.addFunctionalDependency(new FunctionalDependency(left, right));
        }
        
        return neoRelation;
    }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    
    // Main Relation
    private Relation currentRelation;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSolveCK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea taCandidateKeys;
    private javax.swing.JTextField tfFunctionalDependenciesCK;
    private javax.swing.JTextField tfRelationCK;
    // End of variables declaration//GEN-END:variables
}
