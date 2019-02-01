package Dominos;

import java.util.*;

public class Board extends LinkedList<Dominos>{

    public void printBoard(){
        if(this.isEmpty()){
            System.out.println("Board empty: place anything");
        } else {

            System.out.println("Dominos played:");
            for (Dominos s : this) {
                if(this.indexOf(s)%2==0) {
                    System.out.print("[" + s.toString() + "]" + " ");
                }
            }
            System.out.println();
            System.out.print("  ");
            for (Dominos s : this) {
                if(this.indexOf(s)%2==1) {
                    System.out.print("[" + s.toString() + "]" + " ");
                }
            }
            System.out.println();


//            System.out.println("Left domino: " + this.getFirst()
//                    + ", Right Domino: " + this.getLast());
        }
    }

    public boolean legalMoveLeft(Dominos domino){
        if(this.isEmpty()){
            return true;
        }
        Dominos hold;
        hold = this.getFirst();
        if(domino.getRightSide() ==0|| domino.getLeftSide() == 0){
            return true;
        }

        if(domino.getRightSide() == hold.getLeftSide()
            || domino.getLeftSide() == hold.getLeftSide()){
            return true;
        }
        return false;
    }

    public boolean legalMoveRight(Dominos domino){
        if(this.isEmpty()){
            return true;
        }
        if(domino.getRightSide() ==0|| domino.getLeftSide() == 0){
            return true;
        }
        Dominos hold;
        hold = this.getLast();

        if(domino.getRightSide() == hold.getRightSide()
                || domino.getLeftSide() == hold.getRightSide()){
            return true;
        }

        return false;
    }

    public void placePiece(String pos, Dominos piece){
        Dominos hold;
        if(pos.equals("left")){
            hold = this.getFirst();
            if(hold.getLeftSide() != piece.getRightSide()
                    && piece.getRightSide() !=0){
                piece.flip();
            }
            this.addFirst(piece);
        }else{
            hold = this.getLast();
            if(hold.getRightSide() != piece.getLeftSide()
                    && piece.getLeftSide() !=0){
                piece.flip();
            }
            this.addLast(piece);
        }
    }
}
