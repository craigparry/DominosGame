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




    public Dominos matches(String next){
        boolean match = false;
        for(Dominos s: tray){
            match = s.toString().equals(next);
            if(match) {
                return s;
            }
        }
            return null;
    }

    public boolean existLegal(Board board){


        return false;
    }

    public boolean playTurn(Board board, Player player){
        boolean left = false;
        boolean right = false;
        Dominos piece;

        if(!comp) {

            String next;
            Scanner sc = new Scanner(System.in);

            System.out.print("Your tray: ");
            player.printTray();

            if(!existLegal(board) && !board.isEmpty()){
                System.out.println("No legal moves enter \"yes\" to draw.");
                next = sc.nextLine();
                while(!next.equals("yes")){
                    System.out.println("Dont make me tell you again \"yes\"!");
                    next = sc.nextLine();
                }

                return false;



            }

            System.out.println("Player input your turn: ");
            next = sc.nextLine();


            piece = matches(next);
            while(piece ==null){
                System.out.println("Input invalid use syntax: \"#:#\"");
                next =sc.nextLine();
                piece = matches(next);
            }

            if(board.isEmpty()){
                board.add(piece);
                return true;
            }

            left = board.legalMoveLeft(piece);
            right = board.legalMoveRight(piece);
            if(left & right){
                System.out.println("Play: \"left\" or \"right\"?");
                next = sc.nextLine();
                while(!next.equals("left") && !next.equals("right")){
                    System.out.println("Mis-typed, play: \"left\" or \"right\"?");
                    next = sc.nextLine();
                }

                board.placePiece(next, piece);
            }
            if(left){
               board.placePiece("left", piece);
               return true;
            } else if(right){
                board.placePiece("right", piece);
                return true;
            }else{
                System.out.println("No legal play: pick another piece.");
                return playTurn(board,player);
            }

        } else{
            computerMove();
            return true;
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
