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
        grave.initDraw(human.getTray());
        computer = new Player(true);
        grave.initDraw(computer.getTray());

        System.out.print("New Game: \n"+ "When inputting dominos use syntax"
                + " \"#:#\" as it appears in your tray\n");
    }

    /**
     *
     * @return
     */
    public GameState getState(){
        return state;
    }

    /**
     *
     * @param newState
     */
    public void setState(GameState newState){
        state = newState;
    }

    /**
     *
     * @return
     */
    public int getTurn(){
        return turn;
    }

    /**
     *
     */
    public void incTurn(){
        turn++;
    }

    /**
     *
     * @return
     */
    public Player getHuman(){
        return human;
    }

    /**
     *
     * @return
     */
    public Player getComputer(){
        return computer;
    }

    /**
     *
     * @return
     */
    public Board getBoard(){
        return board;
    }

    /**
     *
     * @return
     */
    public GraveYard getGrave(){
        return grave;
    }

    /**
     *
     * @param who
     */
    public void setWinner(String who){
        winner = who;
    }

    /**
     *
     * @return
     */
    public String getWinner(){
        return winner;
    }

    /**
     *
     * @return
     */
    public GameState gameOver(){
        System.out.println(winner + " wins!");
        return GameState.GAME_OVER;
    }

    /**
     *
     */
    public void playGame(){
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
                    validTurn = getHuman().playTurn(getBoard(),getHuman());
                    if(!validTurn){
                        //make sure graveyard is not empty then draw
                        if(getGrave().isEmpty()){
                            // game over
                            if(gameOver() == GameState.GAME_OVER){
                                return;
                            }
                        }
                        //if grave yard is empty endgame other player wins
                        getHuman().getTray().add(getGrave().draw());


                    }
                }
                if(validTurn){
                    setWinner(getHuman().toString());
                }



                incTurn();
//                game.getBoard().printBoard();
//                break;
            } else {
                //computer turn
//                System.out.println("The board: ");
                setState(GameState.CPU_TURN);
                boolean validTurn = false;
                System.out.print("Computer's turn: \n");

                while(!validTurn){
                    validTurn = getComputer().playTurn(getBoard(),getComputer());
                    // make sure graveyardd is not empty!!! then draw
                    if(!validTurn){
                        if(getGrave().isEmpty()){
                            // game over
                            if(gameOver() == GameState.GAME_OVER){
                                return;
                            }
                        }
                        getComputer().getTray().add(getGrave().draw());
                        System.out.println("Computer drew");
                    }

                }
                if(validTurn){
                    setWinner(getComputer().toString());
                }
                incTurn();

                // loop while it is the computers turn, check every piece for a
                // legal move, play the first legal move and change state to
                //
            }
        }

    }

    public static void main(String[] args) {
        // write your code here
//        Dominos ex = new Dominos(2,7);
//        GraveYard examp = new GraveYard();
//        examp.print();
        String newGame;
        MainGameLoop game = new MainGameLoop();

       do{
           game.playGame();
           Scanner sc = new Scanner(System.in);
           System.out.println("Play new game: \"yes\" or \"no\"");
          do{
              newGame = sc.nextLine();
          }while(!newGame.equals("yes")&&!newGame.equals("no"));
          if(newGame.equals("yes")) game.newGame();
       }while(newGame.equals("yes"));
    }
}
