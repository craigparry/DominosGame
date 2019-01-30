package Dominos;

import java.util.*;

public class Player {
    private boolean comp;

    private List<Dominos> tray;

    public Player(boolean comp){
        comp = comp;
        tray = new LinkedList<>();

    }

    public List<Dominos> getTray() {
        return tray;
    }

    public void printTray(){
        for(Dominos s: tray){
            System.out.print(s.toString()+", ");
        }
        System.out.println();
    }

    public void setTray(List<Dominos> newTray){
        tray = newTray;

    }

    public void legalMove(Board board, String next){
        for(Dominos s: tray){
            // if the domino typed exists in the tray
            if(s.toString().equals(next)){
                // if the board is empty just place it
                if(board.isEmpty()){
                    board.add(s);
                }

                // check first left
                // check last righ and place the match
            }
        }
    }
    public void playTurn(Board board){
        String next;
        Scanner sc = new Scanner(System.in);
        next =sc.nextLine();

        legalMove(board, next);

        // could place this in a legal move
        // for every piece in the tray check for a match
        // if there is a map try to place it on the board.
        // if there is no pieces just add to the board.
        // if there are pieces check the first position leftSide
        // or the last position right side for a match
    }

}
