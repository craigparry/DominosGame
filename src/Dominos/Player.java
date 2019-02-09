/** Craig Parry
 * This file is used for the instances of player objects to be used in the
 * domino game. This file should be used with GraveYard.java, Dominos.java,
 * Player.java, DominosGUI.java, GameState.java, Board.java,
 * and MainGameLoop.java
 *
 */

package Dominos;

import java.util.*;

public class Player {

    private boolean comp;
    private List<Dominos> tray;

    /** Constructor for the player class that takes a boolean to define whether
     * computer or human player
     *
     * @param comp
     * @return void
     */
    public Player(boolean comp){
        this.comp = comp;
        tray = new LinkedList<>();
    }

    /** Getter for the players tray
     * @param
     * @return List<Dominos>
     */
    public List<Dominos> getTray() {
        return tray;
    }

    public void setTray(List<Dominos> newTray){
        tray = newTray;
    }

    /** Prints the dominos held in the players tray
     * @param
     * @return void
     */
    public void printTray(){
        for(Dominos s: tray){
            System.out.print("["+s.toString()+"]"+" ");
        }
        System.out.println();
    }

    /** This method matches to String passed into the function to the string
     * representation of the piece in in players tray and return that domino
     * if it exists, null otherwise
     *
     * @param next
     * @return Dominos
     */
    public Dominos matches(String next){
        boolean match;
        for(Dominos s: tray){
            match = s.toString().equals(next);
            if(match) {
                return s;
            }
        }
        return null;
    }

    /** This method checks if there is a domino in the players tray that can
     * be played legally on the board.
     *
     * @param board
     * @param player
     * @return boolean
     */
    public boolean existLegal(Board board, Player player){
        boolean legal =false;
        for(Dominos s: player.getTray()){
            legal =board.legalMoveLeft(s);
            if(legal){
                return legal;
            }
            legal =board.legalMoveRight(s);
            if(legal){
                return legal;
            }
        }

        return legal;
    }

    /** This method plays a turn for the text based version of the game.
     *
     * @param board
     * @param player
     * @return boolean
     */
    public boolean playTurn(Board board, Player player,GraveYard grave){
        boolean left = false;
        boolean right = false;
        Dominos piece;

        if(!comp) {

            String next;
            Scanner sc = new Scanner(System.in);

            System.out.print("Your tray: ");
            player.printTray();

            if(!existLegal(board, player) && !board.isEmpty()){
                System.out.println("No legal moves enter \"yes\" to draw.");
                next = sc.nextLine();

                while(!next.equals("yes")){
                    System.out.println("Dont make me tell you again \"yes\"!");
                    next = sc.nextLine();
                }
//                sc.close();
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
                board.addFirst(piece);
                player.getTray().remove(piece);
//                sc.close();
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
                player.getTray().remove(piece);
                return true;
            }
            if(left){
               board.placePiece("left", piece);
               player.getTray().remove(piece);
//               sc.close();
               return true;
            } else if(right){
                board.placePiece("right", piece);
                player.getTray().remove(piece);
//                sc.close();
                return true;
            }else{
                System.out.println("No legal play: pick another piece.");
//                sc.close();
                return playTurn(board,player,grave);
            }

        } else{

            return computerMove(board, player,grave);

        }
        // could place this in a legal move
        // for every piece in the tray check for a match
        // if there is a map try to place it on the board.
        // if there is no pieces just add to the board.
        // if there are pieces check the first position leftSide
        // or the last position right side for a match
    }

    /** This method makes a computerMove from their tray to the game board
     *
     * @param board
     * @param player
     * @return boolean
     */
    public boolean computerMove(Board board, Player player, GraveYard grave){

//        if(!existLegal(board, player)){
//            return false;
//        }

        Dominos piece = null;
        for(Dominos s: player.getTray()){
            if(board.legalMoveLeft(s)){
                board.placePiece("left",s);
                piece = s;
                break;
            }
            if(board.legalMoveRight(s)){
                board.placePiece("right",s);
                piece = s;
                break;
            }
        }

        if(piece != null){
//            System.out.println("Computer played: "+ piece.toString());
            player.getTray().remove(piece);
            return true;
        }
        if(piece == null && !grave.isEmpty()){
            player.getTray().add(grave.draw());
            return computerMove(board,player,grave);
        }
        if(piece == null && grave.isEmpty()){
            return false;
        }

        return false;
    }

    /** this method overrides toString to print out the string representation
     *  of the Player
     * @param
     * @return String
     */
    @Override
    public String toString(){
        if(comp){
            return "Computer";
        } else return "Player";
    }
}
