package Dominos;

import java.util.*;


public class GraveYard {

    private List<Dominos> grave;

    public GraveYard(){
        int max = Dominos.getMax();
        grave = new LinkedList<>();
        for(int i = 0; i<= max; i++){
            for(int k = 0; k <= max; k++){
                grave.add(new Dominos(i,k));
            }

        }
    }

    public void print(){
        for(Dominos s: grave){
            System.out.print(s.getLeftSide()+":" + s.getRightSide()+", ");

        }
    }


}
