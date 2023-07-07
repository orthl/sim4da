package dev.oxoo2a.sim4da;

public class NKV {
    //TODO Wie greife ich auf den Vektor des entsprechenden Node zu?
    //TODO wie greife ich auf die numberOfNodes zu?
    int nodes = 8 - 1; //Node.numberOfNodes()
    int nullVector[] = new int[nodes];

    public void check(){
        boolean allZero = false;

        // Prüft ob alle Stellen im Vector = 0 sind
        while (!allZero) {

            allZero = true;

            count();

            for (int number : nullVector) {
                if (number != 0) {
                    allZero = false;
                    break;
                }
            }


            // Gibt den Status der Terminierung aus
            if (!allZero) {
                System.out.println("NKV keine Terminierung");
            } else {
                System.out.println("NKV Terminierung");
            }
        }
    }

    //Addition der Vektoren zum Nullvektor
    public void count(){
        for(int i=0;i<=nodes - 1;i++){
            nullVector[i] = nullVector[i] + Node.nkvCount[i];
        }
    }
}
