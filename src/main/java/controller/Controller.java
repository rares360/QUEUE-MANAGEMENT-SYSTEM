package controller;

import models.Client;
import models.Queue;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Controller {
    private View view;

    public Controller(View view) {
        this.view=view;
        this.view.getBtnStart(new BtnStartListener());
    }

    private int verificareInput(String string){
        try {
            Integer.parseInt(string);
            return 1;
        } catch(NumberFormatException e){
            return 0;
        }
    }
   public int random(int a,int b){
        Random random = new Random();
        return random.nextInt(b-a+1)+a;
    }

    class BtnStartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int check1=1,check2=1;
            int n=0,q=0;
            int at1=0,at2=0;
            int st1=0,st2=0;
            int sim=0;
            if(view.getTextFieldN().isEmpty() || view.getTextFieldQ().isEmpty() || view.getTextFieldAT1().isEmpty() || view.getTextFieldAT2().isEmpty() || view.getTextFieldST1().isEmpty() || view.getTextFieldST2().isEmpty() || view.getTextFieldSimulation().isEmpty()){
                check1=0;
                JOptionPane.showMessageDialog(null, "Some boxes are empty!");
            }
            if((verificareInput(view.getTextFieldN())==0 || verificareInput(view.getTextFieldQ())==0 || verificareInput(view.getTextFieldST1())==0 || verificareInput(view.getTextFieldST2())==0 || verificareInput(view.getTextFieldAT1())==0 || verificareInput(view.getTextFieldAT2())==0 || verificareInput(view.getTextFieldSimulation())==0) && check1==1){
                check2=0;
                JOptionPane.showMessageDialog(null, "Wrong Input Format!");
            }
            if(check1==1 && check2==1){
                n=Integer.parseInt(view.getTextFieldN());
                q=Integer.parseInt(view.getTextFieldQ());
                at1=Integer.parseInt(view.getTextFieldAT1());
                at2=Integer.parseInt(view.getTextFieldAT2());
                st1=Integer.parseInt(view.getTextFieldST1());
                st2=Integer.parseInt(view.getTextFieldST2());
                sim=Integer.parseInt(view.getTextFieldSimulation());
                ArrayList<Client> clients =new ArrayList<>();
                //generator random clienti
                for(int i=0;i<n;i++){
                    int at,st;
                    at=random(at1,at2);
                    st=random(st1,st2);
                    Client client=new Client(i+1,at,st);
                    clients.add(client);
                }
                //sortam clientii
                Collections.sort(clients);
                for(int i=0;i<n;i++){
                    System.out.println("Client"+ clients.get(i).getId() +": " + "arrival:" + clients.get(i).getArrivalTime() + " service:" + clients.get(i).getServiceTime() + " \n");
                }

                Simulare simu=new Simulare(n,q,at1,at2,st1,st2,sim,view,clients);
                Thread thread=new Thread(simu);
                    thread.start();
            }
        }
    }
}

