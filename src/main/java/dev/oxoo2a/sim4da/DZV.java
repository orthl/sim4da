package dev.oxoo2a.sim4da;

public class DZV {

    int send1 = 0;
    int send2 = 0;
    int receive1 = 0;
    int receive2 = 0;
    //TODO wie greife ich auf die numberOfNodes zu?
    int nodes = 8 - 1; //Node.numberOfNodes() - 1;

    public void evaluation () {
        count1();
        count2();

        while (send1 != send2 && receive1 != receive2 && send1 != receive1 && send2 != receive2) {
            System.out.println("DZV keine Terminierung");
            send1 = send2;
            receive1 = receive2;
            count2();
        }

        System.out.println("DZV Terminierung");
    }

    private void count1(){
        for (int i = 0; i <= nodes; i++){
            send1 = send1 + Node.sendCount[i];
            receive1 = receive1 + Node.receiveCount[i];
        }
    }

    private void count2(){
        for (int i = 0; i <= nodes; i++){
            send2 = send2 + Node.sendCount[i];
            receive2 = receive2 + Node.receiveCount[i];
        }
    }
}
