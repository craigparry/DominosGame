package Dominos;

import java.util.*;

public class Board extends LinkedList<Dominos>{

    public void printBoard(){
        if(this.isEmpty()){
            System.out.println("Empty: place anything");
        } else {

            System.out.print("Dominos played:");
            for (Dominos s : this) {
                System.out.print(s.toString() + ", ");
            }
            System.out.println();

            System.out.println("Left domino: " + this.getFirst()
                    + ", Right Domino: " + this.getLast());
        }
    }
}
