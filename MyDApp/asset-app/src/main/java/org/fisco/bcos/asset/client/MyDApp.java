package org.fisco.bcos.asset.clientt;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.math.BigInteger;

public class MyDApp extends JFrame {
    SupplyChainCtrl supplyChainCtrl = new SupplyChainCtrl();

    public MyDApp() {
        super("My DApp");
        this.setSize(700, 600);
        this.setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(17,5));
        //  
        loginAndAssetPanel();
        serchReceiptPanel();
        functionalTestLabelPanel();
        signReceiptPanel();
        transferReceiptPanel();
        financingWithReceiptPanel();
        clearReceiptPanel();
        this.setVisible(true);
        //  
        supplyChainCtrl = new SupplyChainCtrl();
        supplyChainCtrl.ctrlInit();
    }

	public static void main(String[] args) {
		MyDApp my_DApp = new MyDApp();
    }

    void loginAndAssetPanel() {
        //  
        JPanel jpanel_0_1 = new JPanel();
        JLabel jlabel_0_1 = new JLabel("");
        jpanel_0_1.add(jlabel_0_1);
        JPanel jpanel_0_2 = new JPanel();
        JLabel jlabel_0_2 = new JLabel("-------------------------------- MyApp Login --------------------------------");
        jpanel_0_2.add(jlabel_0_2);
        this.add(jpanel_0_1);
        this.add(jpanel_0_2);
        //  
        JPanel jpanel_1_1 = new JPanel();
        jpanel_1_1.setLayout(new FlowLayout());
        JLabel jlabel_1_1 = new JLabel("Company Name:");
        JTextField jtextField_1 = new JTextField("");
        jtextField_1.setColumns(10);
        JButton jbutton_1 = new JButton("login");
        //  
        JPanel jpanel_1_2 = new JPanel();
        jpanel_1_2.setLayout(new FlowLayout());
        JLabel jlabel_1_2 = new JLabel("Company Asset: 0");
        //  
        jbutton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                supplyChainCtrl.loginCtrl(jtextField_1.getText());
                jlabel_1_2.setText("Company Asset: " + supplyChainCtrl.getAssetCtrl());
			}
        });
        //  
        jpanel_1_1.add(jlabel_1_1);
        jpanel_1_1.add(jtextField_1);
        jpanel_1_1.add(jbutton_1);
        jpanel_1_2.add(jlabel_1_2);
        this.add(jpanel_1_1);
        this.add(jpanel_1_2);
    }

    void serchReceiptPanel() {
        //  
        JPanel jpanel_0_2 = new JPanel();
        JLabel jlabel_0_2 = new JLabel("------------------------------- Query Receipt -------------------------------");
        jpanel_0_2.add(jlabel_0_2);
        this.add(jpanel_0_2);
        //  
        JPanel jpanel_2_1 = new JPanel();
        jpanel_2_1.setLayout(new FlowLayout());
        JLabel jlabel_2_2 = new JLabel("To Company Name:");
        JTextField jtextField_2 = new JTextField("");
        jtextField_2.setColumns(10);
        JButton jbutton_2 = new JButton("return");
        // 
        JPanel jpanel_2_2 = new JPanel();
        jpanel_2_2.setLayout(new FlowLayout()); 
        JLabel jlabel_2_3 = new JLabel("Receipt Amount: 0");
        //  
        jbutton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String to_company_name = jtextField_2.getText();
                jlabel_2_3.setText("Receipt Amount: " + supplyChainCtrl.getReceiptAmountCtrl(to_company_name));
			}
        });
        //  
        jpanel_2_1.add(jlabel_2_2);
        jpanel_2_1.add(jtextField_2);
        jpanel_2_1.add(jbutton_2);
        jpanel_2_2.add(jlabel_2_3);
        this.add(jpanel_2_1);
        this.add(jpanel_2_2);
    }

    void functionalTestLabelPanel() {
        //  
        JPanel jpanel_0_2 = new JPanel();
        JLabel jlabel_0_2 = new JLabel("------------------------------ Functional Test ------------------------------");
        jpanel_0_2.add(jlabel_0_2);
        this.add(jpanel_0_2);
    }

    void signReceiptPanel() {
        //
        JPanel jpanel_3 = new JPanel();
        JLabel jlabel_4_1 = new JLabel("-------- Sign Receipt --------");
        jpanel_3.add(jlabel_4_1);
        this.add(jpanel_3);
        //
        JPanel jpanel_4 = new JPanel();
        jpanel_4.setLayout(new FlowLayout());
        JLabel jlabel_4_2 = new JLabel("From Company Name ");
        JTextField jtextField_4_1 = new JTextField("");
        jtextField_4_1.setColumns(8);
        JLabel jlabel_4_3 = new JLabel("Receipt Amount ");
        JTextField jtextField_4_2 = new JTextField("");
        jtextField_4_2.setColumns(5);
        JButton jbutton_4 = new JButton("return");
        //  
        jbutton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String from_company_name = jtextField_4_1.getText();
                int receipt_amount = Integer.valueOf(jtextField_4_2.getText());
                supplyChainCtrl.signReceiptCtrl(from_company_name, receipt_amount);
                jtextField_4_1.setText("");
                jtextField_4_2.setText("");
            }
        });
        //  
        jpanel_4.add(jlabel_4_2);
        jpanel_4.add(jtextField_4_1);
        jpanel_4.add(jlabel_4_3);
        jpanel_4.add(jtextField_4_2);
        jpanel_4.add(jbutton_4);
        this.add(jpanel_4);
    }

    void transferReceiptPanel() {
        //  
        JPanel jpanel_3 = new JPanel();
        JLabel jlabel_5_1 = new JLabel("-------- Transfer Receipt --------");
        jpanel_3.add(jlabel_5_1);
        this.add(jpanel_3);
        //
        JPanel jpanel_5 = new JPanel();
        jpanel_5.setLayout(new FlowLayout());
        JLabel jlabel_5_2 = new JLabel("From CN ");
        JTextField jtextField_5_1 = new JTextField("");
        jtextField_5_1.setColumns(8);
        JLabel jlabel_5_3 = new JLabel("To CN ");
        JTextField jtextField_5_2 = new JTextField("");
        jtextField_5_2.setColumns(8);
        JLabel jlabel_5_4 = new JLabel("Amount ");
        JTextField jtextField_5_3 = new JTextField("");
        jtextField_5_3.setColumns(5);
        JButton jbutton_5 = new JButton("return");
        //  
        jbutton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String from_company_name = jtextField_5_1.getText();
                String to_company_name = jtextField_5_2.getText();
                int receipt_amount = Integer.valueOf(jtextField_5_3.getText());
                supplyChainCtrl.transferReceiptCtrl(from_company_name, to_company_name, receipt_amount);
                jtextField_5_1.setText("");
                jtextField_5_2.setText("");
                jtextField_5_3.setText("");
            }
        });
        //  
        jpanel_5.add(jlabel_5_2);
        jpanel_5.add(jtextField_5_1);
        jpanel_5.add(jlabel_5_3);
        jpanel_5.add(jtextField_5_2);
        jpanel_5.add(jlabel_5_4);
        jpanel_5.add(jtextField_5_3);
        jpanel_5.add(jbutton_5);
        this.add(jpanel_5);
    }

    void financingWithReceiptPanel() {
        //  
        JPanel jpanel_3 = new JPanel();
        JLabel jlabel_6_1 = new JLabel("-------- Financing With Receipt --------");
        jpanel_3.add(jlabel_6_1);
        this.add(jpanel_3);
        //
        JPanel jpanel_6 = new JPanel();
        jpanel_6.setLayout(new FlowLayout());
        JLabel jlabel_6_2 = new JLabel("Amount ");
        JTextField jtextField_6 = new JTextField("");
        jtextField_6.setColumns(10);
        JButton jbutton_6 = new JButton("return");
        //  
        jbutton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.valueOf(jtextField_6.getText());
                String flag = supplyChainCtrl.financingWithReceiptCtrl(amount);
                jtextField_6.setText(flag);
            }
        });
        //  
        jpanel_6.add(jlabel_6_2);
        jpanel_6.add(jtextField_6);
        jpanel_6.add(jbutton_6);
        this.add(jpanel_6);
    }

    void clearReceiptPanel() {
        //  
        JPanel jpanel_3 = new JPanel();
        JLabel jlabel_7_1 = new JLabel("-------- Clear Receipt --------");
        jpanel_3.add(jlabel_7_1);
        this.add(jpanel_3);
        //
        JPanel jpanel_7 = new JPanel();
        jpanel_7.setLayout(new FlowLayout());
        JLabel jlabel_7_2 = new JLabel("Company Name ");
        JTextField jtextField_7 = new JTextField("");
        jtextField_7.setColumns(10);
        JButton jbutton_7 = new JButton("return");
        //  
        jbutton_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String company_name = jtextField_7.getText();
                String flag = supplyChainCtrl.clearReceiptCtrl(company_name);
                jtextField_7.setText(flag);
            }
        });
        //  
        jpanel_7.add(jlabel_7_2);
        jpanel_7.add(jtextField_7);
        jpanel_7.add(jbutton_7);
        this.add(jpanel_7);
    }
}