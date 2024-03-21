package com.codechum.swing.jLabel;

import javax.swing.*;
import java.awt.*;

public class LabelClassQuiz extends JFrame {

    /**
     * Creates new form NewFrame
     */
    public LabelClassQuiz() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        label1.setName("label1");
        label2.setName("label2");
        label3.setName("label3");
        label4.setName("label4");

        setMinimumSize(new Dimension(410, 250));
        setTitle("My First App");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        label1.setFont(new Font("Dialog", 0, 18)); // NOI18N
        label1.setText("Hello World!");
        add(label1);
        label1.setBounds(140, 60, 120, 30);

        label2.setFont(new Font("Dialog", 0, 18)); // NOI18N
        label2.setText("I love programming");
        add(label2);
        label2.setBounds(120, 100, 170, 30);

        label3.setFont(new Font("Dialog", 0, 18)); // NOI18N
        label3.setText("I love CodeChum");
        add(label3);
        label3.setBounds(130, 140, 170, 30);

        label4.setFont(new Font("Dialog", 0, 18)); // NOI18N
        label4.setText("I love Java");
        add(label4);
        label4.setBounds(150, 180, 170, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LabelClassQuiz().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // End of variables declaration//GEN-END:variables
}
