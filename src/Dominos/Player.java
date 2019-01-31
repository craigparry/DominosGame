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

    public boolean legalMoveLeft(){
        return false;
    }

    public boolean legalMoveRight(){
        return false;
    }


    public boolean matches(String next){
        boolean match = false;
        for(Dominos s: tray){
            match = s.toString().equals(next);
            if(match) {
                return match;
            }
        }
            return match;
    }
    public void playTurn(Board board, Player player){
        boolean left = false;
        boolean right = false;

        if(!comp) {

            String next;
            Scanner sc = new Scanner(System.in);
            System.out.println("Player make your turn.");
            System.out.print("Your tray: ");
            player.printTray();
            next = sc.nextLine();



            while(!matches(next)){
                System.out.println("Input invalid use syntax: \"#:#\"");
                next =sc.nextLine();
            }
            left = legalMoveLeft();
            right = legalMoveRight();
            if(left & right){
                System.out.println("Play: \"left\" or \"right\"?");
                next = sc.nextLine();
            }
            if(left){

            } else if(right){

            }else{
                System.out.println("No legal play: pick another piece.");
                playTurn(board,player);
            }

        } else{
            computerMove();
        }
        // could place this in a legal move
        // for every piece in the tray check for a match
        // if there is a map try to place it on the board.
        // if there is no pieces just add to the board.
        // if there are pieces check the first position leftSide
        // or the last position right side for a match
    }

    /**
     * implement esse
     */
    public void computerMove(){

    }


}
