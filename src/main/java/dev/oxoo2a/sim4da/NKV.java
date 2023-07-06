package dev.oxoo2a.sim4da;

public class NKV {
    //TODO Wie greife ich auf den Vektor des entsprechenden Node zu?
    //TODO wie greife ich auf die numberOfNodes zu?
    int nodes = 8 - 1; //Node.numberOfNodes()
    int nullVector[] = new int[nodes];

    public void check(){
        boolean allZero = false;

        while (!allZero) {

            allZero = true;

            count();

            for (int number : nullVector) { // Durchlaufe jedes Element im Array.
                if (number != 0) { // Wenn das Element nicht Null ist,
                    allZero = false; // setze allZero auf false.
                    break; // und beende die Schleife.
                }
            }

            if (!allZero) {
                System.out.println("NKV keine Terminierung");
            } else {
                System.out.println("NKV Terminierung");
            }
        }
    }

    public void count(){
        for(int i=0;i<=nodes - 1;i++){
            nullVector[i] = nullVector[i] + Node.nkvCount[i];
        }
    }
}
