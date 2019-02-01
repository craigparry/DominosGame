package Dominos;

import java.util.*;

public class Board extends LinkedList<Dominos>{

    public void printBoard(){
        if(this.isEmpty()){
            System.out.println("Empty: place anything");
        } else {

            System.out.print("Dominos played:");
            for (Dominos s : this) {
                if(this.indexOf(s)%2==0) {
                    System.out.print("[" + s.toString() + "]" + " ");
                }
            }
            System.out.println();
            System.out.print("    ");
            for (Dominos s : this) {
                if(this.indexOf(s)%2==1) {
                    System.out.print("[" + s.toString() + "]" + " ");
                }
            }
            System.out.println();


            System.out.println("Left domino: " + this.getFirst()
                    + ", Right Domino: " + this.getLast());
        }
    }

    public boolean legalMoveLeft(Dominos domino){
        if(this.isEmpty()){
            return true;
        }
        Dominos hold;
        hold = this.getFirst();

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
            if(hold.getLeftSide() != piece.getRightSide()){
                piece.flip();
            }
            this.addFirst(piece);
        }else{
            hold = this.getLast();
            if(hold.getRightSide() != piece.getLeftSide()){
                piece.flip();
            }
            this.addLast(piece);
        }
    }

    public void addFront(Dominos domino){
        if(this.isEmpty()){
            this.addFirst(domino);
            return;
        }
        int hold = this.getFirst().getLeftSide();
        if(domino.getRightSide() == hold){
            this.addFirst(domino);
        } else{
            domino.flip();
            this.addFirst(domino);
        }

    }

    public void addEnd(Dominos domino){
        if(this.isEmpty()){
            this.addLast(domino);
            return;
        }
        int hold = this.getFirst().getRightSide();
        if(domino.getLeftSide() == hold){
            this.addFirst(domino);
        } else{
            domino.flip();
            this.addFirst(domino);
        }
    }
}
