/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.list;

/**
 *
 * @author AzalithenPC
 */
public class MyPlaylist extends java.awt.Frame {

    /**
     * Creates new form MyPlaylist
     */
    public MyPlaylist() {
        initComponents();
        
        musicList.add("Music Main 1");
        musicList.add("Music Main 2");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        musicList = new java.awt.List();
        label1 = new java.awt.Label();
        musicTextField = new java.awt.TextField();
        label2 = new java.awt.Label();
        addButton = new java.awt.Button();
        removeButton = new java.awt.Button();

        setTitle("Music Player");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        musicList.setName("musicList"); // NOI18N
        add(musicList, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 270, 180));

        label1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        label1.setText("Music List");
        add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        musicTextField.setName("musicTextField"); // NOI18N
        add(musicTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 190, -1));

        label2.setName(""); // NOI18N
        label2.setText("Title:");
        add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 60, -1));

        addButton.setLabel("Add");
        addButton.setName("addButton"); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 90, -1));

        removeButton.setLabel("Remove");
        removeButton.setName("removeButton"); // NOI18N
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        add(removeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        String addMusic = musicTextField.getText();
        
        if (addMusic.isEmpty()) {
            return;
        }
        
        musicList.add(addMusic);
        musicTextField.setText("");
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = musicList.getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        musicList.remove(selectedIndex);
    }//GEN-LAST:event_removeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyPlaylist().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button addButton;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.List musicList;
    private java.awt.TextField musicTextField;
    private java.awt.Button removeButton;
    // End of variables declaration//GEN-END:variables
}
