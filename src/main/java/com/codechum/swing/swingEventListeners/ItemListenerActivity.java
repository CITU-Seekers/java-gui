package com.codechum.swing.swingEventListeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ItemListenerActivity extends javax.swing.JFrame implements ItemListener {

    public ItemListenerActivity() {
        initComponents();
        
        cCheckBox.addItemListener(this);
        cppCheckBox.addItemListener(this);
        cSharpCheckBox.addItemListener(this);
        javaCheckBox.addItemListener(this);
        pythonCheckBox.addItemListener(this);
        proficiencyComboBox.addItemListener(this);
        
        setTitle("Item Listener");
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
        jLabel2 = new javax.swing.JLabel();
        proficiencyComboBox = new javax.swing.JComboBox<>();
        cCheckBox = new javax.swing.JCheckBox();
        cppCheckBox = new javax.swing.JCheckBox();
        cSharpCheckBox = new javax.swing.JCheckBox();
        javaCheckBox = new javax.swing.JCheckBox();
        pythonCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        languageLabel = new javax.swing.JLabel();
        proficiencyLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Languages");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Proficiency");

        proficiencyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "Medium", "High" }));
        proficiencyComboBox.setName("proficiencyComboBox"); // NOI18N

        cCheckBox.setText("C");
        cCheckBox.setName("cCheckBox"); // NOI18N

        cppCheckBox.setText("C++");
        cppCheckBox.setName("cppCheckBox"); // NOI18N

        cSharpCheckBox.setText("C#");
        cSharpCheckBox.setName("cSharpCheckBox"); // NOI18N

        javaCheckBox.setText("Java");
        javaCheckBox.setName("javaCheckBox"); // NOI18N

        pythonCheckBox.setText("Python");
        pythonCheckBox.setName("pythonCheckBox"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("My Favorite Languages:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Proficiency:");

        languageLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        languageLabel.setName("languageLabel"); // NOI18N

        proficiencyLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        proficiencyLabel.setName("proficiencyLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(proficiencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cppCheckBox)
                                    .addComponent(cCheckBox)
                                    .addComponent(cSharpCheckBox)
                                    .addComponent(javaCheckBox)
                                    .addComponent(pythonCheckBox)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(proficiencyLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(languageLabel)))))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cppCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cSharpCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(javaCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pythonCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(proficiencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(languageLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(proficiencyLabel))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ItemListenerActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemListenerActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemListenerActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemListenerActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ItemListenerActivity().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cCheckBox;
    private javax.swing.JCheckBox cSharpCheckBox;
    private javax.swing.JCheckBox cppCheckBox;
    private javax.swing.JCheckBox javaCheckBox;
    private javax.swing.JCheckBox pythonCheckBox;
    private javax.swing.JComboBox<String> proficiencyComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JLabel proficiencyLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void itemStateChanged(ItemEvent e) {
        String comma = languageLabel.getText().length() > 0 ? ", " : "";

        if(e.getSource().equals(cCheckBox)) {
            languageLabel.setText(languageLabel.getText() + comma + cCheckBox.getText());
        }

        if(e.getSource().equals(cppCheckBox)) {
            languageLabel.setText(languageLabel.getText() + comma + cppCheckBox.getText());
        }

        if(e.getSource().equals(cSharpCheckBox)) {
            languageLabel.setText(languageLabel.getText() + comma + cSharpCheckBox.getText());
        }

        if(e.getSource().equals(javaCheckBox)) {
            languageLabel.setText(languageLabel.getText() + comma + javaCheckBox.getText());
        }

        if(e.getSource().equals(pythonCheckBox)) {
            languageLabel.setText(languageLabel.getText() + comma + pythonCheckBox.getText());
        }
        
        if(e.getSource().equals(proficiencyComboBox)) {
            String val = proficiencyComboBox.getModel().getSelectedItem().toString();
            proficiencyLabel.setText(val);
        }
    }
}
