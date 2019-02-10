/**Craig Parry
 * This file is used to start and instaniate the MainGameLoop of the Domino game
 * This file needs to be run with the Board.java, GraveYard.java, Dominos.java,
 * Player.java, GameState.java and DominosGUI.java
 *
 */

package Dominos;
import java.util.*;

public class MainGameLoop {
    private GraveYard grave;
    private Player human;
    private Player computer;
    private int turn;
    private GameState state;
    private Board board;
    private String winner;

    /** Constructor for the MainGameLoop
     *
     */
    public MainGameLoop(){
        newGame();
    }

    /** New game method initializes the memeber variables of the class and
     * sets all member integers to zero and String to default
     *
     */
    public void newGame(){
        turn = 0;
        state = GameState.NEW_GAME;
        grave = new GraveYard();
        board = new Board();
        /*initialize the player and computer trays*/
        human = new Player(false);
       // human.setTray(grave.initDraw(human.getTray()));
        computer = new Player(true);
    }

    /** Getter for the gameState
     * @param
     * @return GameState
     */
    public GameState getState(){
        return state;
    }

    /** sets the state of the game
     *
     * @param newState
     * @return void
     */
    public void setState(GameState newState){
        state = newState;
    }

    /** getter for the games turn
     *
     * @param
     * @return int
     */
    public int getTurn(){
        return turn;
    }

    /** increments the turn for the game
     * @param
     * @return void
     *
     */
    public void incTurn(){
        turn++;
    }

    /** gets the Player: human
     *
     * @param
     * @return Player
     */
    public Player getHuman(){
        return human;
    }

    /** gets the Player: computer
     * @param
     * @return Player
     */
    public Player getComputer(){
        return computer;
    }

    /** gets the board for the game
     * @param
     * @return Board
     */
    public Board getBoard(){
        return board;
    }

    /** gets the grveyard for the game
     * @param
     * @return GraveYard
     */
    public GraveYard getGrave(){
        return grave;
    }

    /** Sets the winner for the game
     *
     * @param who
     * @return void
     */
    public void setWinner(String who){
        winner = who;
    }

    /** gets the winner of the game
     * @param
     * @return String
     */
    public String getWinner(){
        return winner;
    }

    /** gets the gameOver state and returns a message of gameover to the console
     * @param
     * @return GameState
     */
    public GameState gameOver(){

        /// check for legal move for player and for computer

        System.out.println(getWinner() + " wins!");
        return GameState.GAME_OVER;
    }

    /** Plays a text based version of the dominos game on the console
     * with a computer player. It gives instructions for input for the console
     * commands of playing a piece or drawing from the graveyard and gives a
     * visual representation of the board.
     *
     * @param
     * @return void
     */
    public void playTextGame(){
        grave.initDraw(computer.getTray());
        grave.initDraw(human.getTray());
        System.out.print("New Game: \n"+ "When inputting dominos use syntax"
                + " \"#:#\" as it appears in your tray\n");


        // maybe put the game logic in main here
        while(getState() != GameState.GAME_OVER){
            // need to write a game over method that will take into account
            // whose turn it is and who played the last piece
            /* check whos turn it is then call gameOver if they have a they have
            a legal move and grave yard no, if they dont have a legal move is graveyard empty
            if no not over, if they dont have a legal move and the graveyard is not empty then
            yes. if the player is asked to draw on an empty graveyard then break the draw loop
            and let the turn increment to next player and check for game over. If game is over
            break the main loop and set gamestate to game over.
             */
            // might move the game logic in the main method into a method in main game loop



            if(getTurn()%2 == 0){
                setState(GameState.HUM_TURN);
                //human turn
//                System.out.println("The board: ");
                // print the left most and the right most and all of the pieces played.
                getBoard().printBoard();



                //game.getComputer().printTray();
                boolean validTurn = false;
                while(!validTurn){
                    validTurn = getHuman().playTurn(getBoard(),getHuman(),getGrave());

                    if(validTurn){
                        setWinner(getHuman().toString());
                    }

                    if(!validTurn && getGrave().isEmpty()){
                        System.out.println("No Move and Grave Empty.");
                        if(!computer.existLegal(board,computer)&& !human.existLegal(board,human)){
                            setState(GameState.GAME_OVER);

                        }
                        break;
                    }

                        if(!validTurn && !getGrave().isEmpty()) {
                            getHuman().getTray().add(getGrave().draw());
                        }
                }
                incTurn();
            } else {
                //computer turn
//                System.out.println("The board: ");
                setState(GameState.CPU_TURN);
                boolean validTurn = false;
                System.out.print("Computer's turn: \n");





                    validTurn = getComputer().playTurn(getBoard(),getComputer(),getGrave());
                    // make sure graveyardd is not empty!!! then draw
                    if(validTurn){
                        setWinner(getComputer().toString());
                    }

                    // need two conditions if !validTurn and getGrave().isEmpty()
                    // and !validTurn and !getGrave().isEmpty()

                    if(!validTurn && getGrave().isEmpty()){

                            // game over
                            // set to game over if no moves left else
                        if(!computer.existLegal(board,computer)&& !human.existLegal(board,human)){
                            setState(GameState.GAME_OVER);

                        }
                        break;
                    }


                incTurn();

                // loop while it is the computers turn, check every piece for a
                // legal move, play the first legal move and change state to
                //
            }
        }
    gameOver();
        System.out.println("Player tray:");
        human.printTray();
        System.out.println("Computer tray:");
        computer.printTray();
    }

    /**need to make a method that will play the turn from the GUI and update the
    * state of the gameboard, player tray if the domino is clicked and has a legal
    * move
     * @param Dominos domino, Player player, String where
     * * @return boolean
     * */

    public boolean playTurnGUI(Dominos domino, Player player, String where){
        if(getBoard().isEmpty()){
            getBoard().add(domino);
            player.getTray().remove(domino);
            return true;
        }

        if(where.equals("right")){
            board.placePiece("right",domino);
            player.getTray().remove(domino);
            return true;
        }

        if(where.equals("left")){
            board.placePiece("left",domino);
            player.getTray().remove(domino);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {

        String newGame;
        MainGameLoop game = new MainGameLoop();

       do{
           game.playTextGame();
           Scanner sc = new Scanner(System.in);
           System.out.println("Play new game: \"yes\" or \"no\"");
          do{
              newGame = sc.nextLine();
          }while(!newGame.equals("yes")&&!newGame.equals("no"));
          if(newGame.equals("yes")) game.newGame();
       }while(newGame.equals("yes"));
    }
}
