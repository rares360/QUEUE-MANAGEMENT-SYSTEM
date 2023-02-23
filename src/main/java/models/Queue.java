package models;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements  Runnable{
    private BlockingQueue<Client> clients;
    private int id;
    private AtomicInteger waitingTime;

    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }

    public Queue(int id){
        this.id=id;
        this.clients=new LinkedBlockingDeque<>();
        this.waitingTime=new AtomicInteger(0);
    }

    public int getId() {
        return id;
    }

    public void addClient(Client client) throws InterruptedException {
       this.clients.put(client);
       this.waitingTime.addAndGet(client.getServiceTime());
    }
    @Override
    public void run() {
        while(true){
            Client client = new Client();
            try {
                client = clients.element();
            } catch(Exception e){

            }
            try {
                Thread.sleep(client.getServiceTime()* 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.waitingTime.addAndGet(-client.getServiceTime());
            client.setServiceTime(0);
        }
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }
}
