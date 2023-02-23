package controller;

import models.Client;
import models.Queue;
import view.View;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Simulare implements Runnable{
    private int n,q,at1,at2,st1,st2,sim;
    private ArrayList<Client> clients;
    private ArrayList<Queue> queues;
    private View view;
    private int ok=1;

    public Simulare(int n, int q, int at1, int at2, int st1, int st2, int sim, View view, ArrayList<Client> clients){
        this.view=view;
        this.n=n;
        this.q=q;
        this.at1=at1;
        this.at2=at2;
        this.st1=st1;
        this.st2=st2;
        this.sim=sim;
        queues=new ArrayList<>();
        for(int j=0;j<q;j++){
            Queue queue=new Queue(j+1);
            queues.add(queue);
            Thread thread=new Thread(queue);
            thread.start();
        }
        this.clients=clients;
    }

    public int minWaiting(ArrayList<Queue> queues){
        int minWaiting=queues.get(0).getWaitingTime().get();
        for (Queue q:queues) {
            if(q.getWaitingTime().get()<minWaiting){
                minWaiting=q.getWaitingTime().get();
            }
        }
        return minWaiting;
    }

    public String createString(ArrayList<Queue> queues,int currentTime) throws InterruptedException {
        String string="";
        string+="Time: "+currentTime+"\n";
        for(int i=0;i<q;i++){
            string+="Queue " + queues.get(i).getId() +": ";
            if(queues.get(i).getClients().isEmpty()){
                string+=" closed";
            }else {
                for (int j = 0; j < queues.get(i).getClients().size(); j++) {
                    Client c = queues.get(i).getClients().take();
                    string += "(" + c.getId() + "," + c.getArrivalTime() + "," + c.getServiceTime() + ") ";
                    queues.get(i).getClients().add(c);
                }
            }
            string += "\n";
        }
        return string;
    }
    public void writeFile(String string) throws IOException {
        try {
            if(ok==1){
                PrintWriter writer=new PrintWriter("R:\\FACULTATE\\SEM2\\TP\\PT2022_30228_Furtos_Rares_Assignment_2\\pt2022_30228_furtos_rares_assignment_2\\teste.txt");
                writer.print("");
                writer.close();
                ok=0;
            }
            FileWriter write = new FileWriter("R:\\FACULTATE\\SEM2\\TP\\PT2022_30228_Furtos_Rares_Assignment_2\\pt2022_30228_furtos_rares_assignment_2\\teste.txt",true);
            BufferedWriter bufferedWriter=new BufferedWriter(write);
            bufferedWriter.append(string);
            bufferedWriter.newLine();
            bufferedWriter.close();
            write.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int currentTime=1,minWaitingTime;
        while(currentTime<=sim){
            for (int i=0;i<n;i++) {
                if(clients.get(i).getArrivalTime()==currentTime){
                    minWaitingTime=minWaiting(queues);
                    for(int j=0;j<q;j++){
                        if(queues.get(j).getWaitingTime().get()==minWaitingTime){
                            try {
                                queues.get(j).addClient(clients.get(i));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            clients.remove(i);
                            n--;
                            i--;
                            break;
                        }
                    }
                }
            }
            for (int i=0;i<q;i++) {
                if(!queues.get(i).getClients().isEmpty()){
                    if(queues.get(i).getClients().element().getServiceTime()==0){
                        queues.get(i).getClients().remove();
                    }
                }
            }
            String temp= null;
            try {
                temp = createString(queues,currentTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                view.setTextArea(temp);
            try {
                writeFile(temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            view.refresh();
        }

    }

}