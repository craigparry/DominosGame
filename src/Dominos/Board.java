/** Craig Parry
 * This file is uses as the gameboard for the Dominos game and should be used
 * with the MainGameLoop.java, DominosGUI.java, Player.java, Dominos.java,
 * GraveYard.java, and GameState.java
 *
 */
package Dominos;

import java.util.*;

public class Board extends LinkedList<Dominos>{
    /** prints the gameboards content in two rows spacing the bottom row by half
     * a domino tile to match the numbers that have been played correctly
     * @param
     * @return void
     */
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

    /** This method checks for a legal move on the left side of the playing board
     * and returns a boolean if a legal move exists
     * @param domino
     * @return boolean
     */
    public boolean legalMoveLeft(Dominos domino){
        if(this.isEmpty()){
            return true;
        }
        Dominos hold;
        hold = this.getFirst();
        if(domino.getRightSide() ==0|| domino.getLeftSide() == 0
                || hold.getLeftSide() == 0){
            return true;
        }

        if(domino.getRightSide() == hold.getLeftSide()
            || domino.getLeftSide() == hold.getLeftSide()){
            return true;
        }
        return false;
    }

    /** This method checks for a legal move on the right side of the playing board
     * and returns a boolean if a legal move exists
     * @param domino
     * @return boolean
     */
    public boolean legalMoveRight(Dominos domino){
        if(this.isEmpty()){
            return true;
        }
        Dominos hold;
        hold = this.getLast();

        if(domino.getRightSide() ==0|| domino.getLeftSide() == 0
                || hold.getRightSide() == 0){
            return true;
        }


        if(domino.getRightSide() == hold.getRightSide()
                || domino.getLeftSide() == hold.getRightSide()){
            return true;
        }

        return false;
    }

    /** This method places a piece on the board to the left or the right side
     * based on the String representation of the position specified on the
     * gameboard. Assumes that a legal move exists at that position
     *
     * @param pos
     * @param piece
     */
    public void placePiece(String pos, Dominos piece){
        Dominos hold;
        if(pos.equals("left")){
            hold = this.getFirst();

            if(piece.getLeftSide() == 0){
                piece.flip();
                this.addFirst(piece);
                return;
            }
            if(piece.getRightSide() == 0){
                this.addFirst(piece);
                return;
            }

            if(hold.getLeftSide() != piece.getRightSide()
                    && hold.getLeftSide() != 0){
                piece.flip();
            }
            this.addFirst(piece);
        }else{
            hold = this.getLast();
            if(piece.getRightSide() == 0){
                piece.flip();
                this.addLast(piece);
                return;
            }
            if(piece.getLeftSide() == 0){
                this.addLast(piece);
                return;
            }
            if(hold.getRightSide() != piece.getLeftSide()
                    && hold.getRightSide() !=0){
                piece.flip();
            }
            this.addLast(piece);
        }
    }
}
