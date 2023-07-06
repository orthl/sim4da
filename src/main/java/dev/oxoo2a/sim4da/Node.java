package dev.oxoo2a.sim4da;

import java.util.Random;

public abstract class Node implements Simulator2Node {

    private boolean state;
    private double initialProb = 10; //Wahrscheinlichkeit Verstellmöglichkeit
    //TODO wie greife ich auf die numberOfNodes zu?
    //TODO werden die Arrays richtig pro Node initiiert?
    public static int receiveCount[] = new int[8];//Node.numberOfNodes()];
    public static int[] sendCount = new int[8];//Node.numberOfNodes()];
    public static int[] nkvCount= new int[8];//Node.numberOfNodes()];

    public Node ( int my_id ) {
        this.myId = my_id;
        state = true;
        t_main = new Thread(this::main);
    }

    @Override
    public void setSimulator(Node2Simulator s ) {
        this.simulator = s;
    }

    @Override
    public void start () {
        t_main.start();
    }

    private void sleep ( long millis ) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {};
    }

    protected int numberOfNodes() { return simulator.numberOfNodes(); };

    protected boolean stillSimulating () {
        return simulator.stillSimulating();
    }

    protected void sendUnicast ( int receiver_id, String m ) {
        System.out.println("Drinnen");
        if (state==true){
            int prob2 = sendProb();
            if (initialProb > 0.01 && prob2 == 0){
                sendCount();
                nkvSendCount(receiver_id);
                state = false;
                simulator.sendUnicast(myId,receiver_id,m);
            }
        }
    }

    protected void sendUnicast ( int receiver_id, Message m ) {
        if (state==true){
            int prob2 = sendProb();
            if (initialProb > 0.01 && prob2 == 0){
                sendCount();
                nkvSendCount(receiver_id);
                state = false;
                simulator.sendUnicast(myId,receiver_id,m);
            }
        }
    }

    protected void sendBroadcast ( String m ) {
        //sendCount();
        //nkvSendCount(0);
        //this.state = false;
        //simulator.sendBroadcast(myId,m);
    }

    protected void sendBroadcast ( Message m ) {
        //sendCount();
        //nkvSendCount(0);
        //this.state = false;
        //simulator.sendBroadcast(myId,m);
    }

    protected Network.Message receive () {
        state = true;
        receiveCount();
        nkvReceiveCount();
        newMessage();
        return simulator.receive(myId);
    }

    protected void emit ( String format, Object ... args ) {
        simulator.emit(format,args);
    }
    // Module implements basic node functionality
    protected abstract void main ();

    protected void newMessage(){
        Random random = new Random();
        int randomNode = random.nextInt(numberOfNodes());

        sendUnicast(randomNode, "test");
    }

    protected int sendProb (){
        //Zufälligen Node bestimmen
        int n = numberOfNodes();
        Random random = new Random();

        double prob1 = initialProb * 0.2;
        int prob2 = random.nextInt(2);

        initialProb = prob1;
        return prob2;
    }

    protected void sendCount(){
        int nodeID = myId - 1;
        sendCount[nodeID] = sendCount[nodeID] + 1;
    }

    protected void receiveCount(){
        int nodeID = myId - 1;
        receiveCount[nodeID] = receiveCount[nodeID] + 1;
    }

    protected void nkvSendCount(int receiverID){
        receiverID = receiverID - 1;
        nkvCount[receiverID] = nkvCount[receiverID] + 1;
    }

    protected void nkvReceiveCount(){
        int nodeID = myId - 1;
        nkvCount[nodeID] = nkvCount[nodeID] - 1;
    }

    @Override
    public void stop () {
        try {
            t_main.join();
        }
        catch (InterruptedException ignored) {};
    }

    protected final int myId;
    private Node2Simulator simulator;
    private final Thread t_main;
}