package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame{
    private JTextField textFieldN;
    private JTextField textFieldQ;
    private JTextField textFieldSimulation;
    private JTextField textFieldAT1;
    private JTextField textFieldAT2;
    private JTextField textFieldST1;
    private JTextField textFieldST2;

    private JButton btnStart;

    private JTextArea textArea;
    private JScrollBar scroll;

    public View() {
        this.getContentPane().setBackground(UIManager.getColor("PasswordField.selectionBackground"));
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblN = new JLabel("N (clients) :");
        lblN.setBounds(6, 6, 115, 30);
        this.getContentPane().add(lblN);

        JLabel lblQ = new JLabel("Q (queues) :");
        lblQ.setBounds(6, 48, 115, 30);
        this.getContentPane().add(lblQ);

        JLabel lblSimulationTime = new JLabel("Simulation Time:");
        lblSimulationTime.setBounds(6, 90, 115, 30);
        this.getContentPane().add(lblSimulationTime);

        JLabel lblArrivalTime = new JLabel("Arrival Time");
        lblArrivalTime.setBounds(6, 132, 115, 30);
        this.getContentPane().add(lblArrivalTime);

        JLabel lblServiceTime = new JLabel("Service Time");
        lblServiceTime.setBounds(6, 212, 115, 30);
        this.getContentPane().add(lblServiceTime);

        textFieldN = new JTextField();
        textFieldN.setBounds(133, 8, 80, 26);
        this.getContentPane().add(textFieldN);
        textFieldN.setColumns(10);

        textFieldQ = new JTextField();
        textFieldQ.setColumns(10);
        textFieldQ.setBounds(133, 50, 80, 26);
        this.getContentPane().add(textFieldQ);

        textFieldSimulation = new JTextField();
        textFieldSimulation.setColumns(10);
        textFieldSimulation.setBounds(133, 92, 80, 26);
        this.getContentPane().add(textFieldSimulation);

        textFieldAT1 = new JTextField();
        textFieldAT1.setColumns(10);
        textFieldAT1.setBounds(6, 174, 80, 26);
        this.getContentPane().add(textFieldAT1);

        textFieldAT2 = new JTextField();
        textFieldAT2.setColumns(10);
        textFieldAT2.setBounds(133, 174, 80, 26);
        this.getContentPane().add(textFieldAT2);

        textFieldST1 = new JTextField();
        textFieldST1.setColumns(10);
        textFieldST1.setBounds(6, 254, 80, 26);
        this.getContentPane().add(textFieldST1);

        textFieldST2 = new JTextField();
        textFieldST2.setColumns(10);
        textFieldST2.setBounds(133, 254, 80, 26);
        this.getContentPane().add(textFieldST2);

        btnStart = new JButton("START");
        btnStart.setBounds(50, 292, 117, 29);
        this.getContentPane().add(btnStart);


        textArea = new JTextArea();
        textArea.setRows(20);
        textArea.setColumns(10);
        textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        textArea.setBounds(260, 30, 500, 486);

        JScrollPane scroll=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.getContentPane().add(scroll);

        JLabel lblStatus = new JLabel("STATUS");
        lblStatus.setBounds(260, 13, 54, 16);
        this.getContentPane().add(lblStatus);

        this.setVisible(true);
    }
    public String getTextFieldN() {return textFieldN.getText();}

    public String getTextFieldQ() {return textFieldQ.getText();}

    public String getTextFieldSimulation() {return textFieldSimulation.getText();}

    public String getTextFieldAT1() {return textFieldAT1.getText();}

    public String getTextFieldAT2() {return textFieldAT2.getText();}

    public String getTextFieldST1() {return textFieldST1.getText();}

    public String getTextFieldST2() {return textFieldST2.getText();}

    public void setTextArea(String string) {this.textArea.setText(string);}

    public void getBtnStart(ActionListener action) {btnStart.addActionListener(action);}
    public void refresh(){
        this.setTextArea(null);
    }
}
