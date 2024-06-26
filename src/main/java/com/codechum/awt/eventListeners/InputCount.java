    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package com.codechum.awt.eventListeners;

/**
 *
 * @author alysa
 */
public class InputCount extends java.awt.Frame {

    /**
     * Creates new form AWTEventListener2
     */
    public InputCount() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textArea1 = new java.awt.TextArea();
        label1 = new java.awt.Label();

        setTitle("Input Count Listener");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textArea1.setName("contentTextArea"); // NOI18N
        textArea1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                textArea1InputMethodTextChanged(evt);
            }
        });
        textArea1.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                textArea1TextValueChanged(evt);
            }
        });
        add(textArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 330, 230));

        label1.setName("textStatisticsLabel"); // NOI18N
        add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 330, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void textArea1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_textArea1InputMethodTextChanged
        // TODO add your handling code here:

        // Get the text from the text area and count the character, word, and line count and display it in the label
        
    }//GEN-LAST:event_textArea1InputMethodTextChanged

    private void textArea1TextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_textArea1TextValueChanged
        // TODO add your handling code here:
        String text = textArea1.getText();
        int charCount = text.replaceAll("\\n", "").length();
        int wordCount = text.split("\\s+").length;
        int lineCount = text.split("\\n").length;

        label1.setText("Characters: " + charCount + " | Words: " + wordCount + " | Ln: " + lineCount);
    }//GEN-LAST:event_textArea1TextValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputCount().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label label1;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
