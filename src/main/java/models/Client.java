package models;

public class Client implements Comparable<Client> {
    private int id;
    private int arrivalTime;
    private int serviceTime;
    public Client(int id,int arrivalTime,int serviceTime){
        this.id=id;
        this.arrivalTime=arrivalTime;
        this.serviceTime=serviceTime;
    }
    public Client(){

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public int getServiceTime() {
        return serviceTime;
    }
    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public int compareTo(Client o) {
        if(this.arrivalTime<o.arrivalTime){
            return -1;
        }
        else if(this.arrivalTime>o.arrivalTime){
                return 1;
            }
        else{
            if(this.serviceTime<o.serviceTime){
                return -1;
            }else if(this.serviceTime>o.serviceTime){
                return 1;
            }
            else return 0;
        }
    }
}
