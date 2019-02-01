package Dominos;


public class MainGameLoop {
    private GraveYard grave;
    private Player human;
    private Player computer;
    private int turn;
    private GameState state;
    private Board board;
    private String winner;

    public MainGameLoop(){
        newGame();
    }

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

    public GameState getState(){
        return state;
    }

    public void setState(GameState newState){
        state = newState;
    }

    public int getTurn(){
        return turn;
    }

    public void incTurn(){
        turn++;
    }

    public Player getHuman(){
        return human;
    }

    public Player getComputer(){
        return computer;
    }

    public Board getBoard(){
        return board;
    }

    public GraveYard getGrave(){
        return grave;
    }
    public void setWinner(String who){
        winner = who;
    }
    public String getWinner(){
        return winner;
    }

    public GameState gameOver(Board board, Player player, GraveYard grave){


        return getState();
    }
    public GameState gameOver(){
        System.out.println(winner + "wins! Play again?");
        return GameState.GAME_OVER;
    }

    public static void main(String[] args) {
        // write your code here
//        Dominos ex = new Dominos(2,7);
//        GraveYard examp = new GraveYard();
//        examp.print();
        MainGameLoop game = new MainGameLoop();

//        loop for player turns
        while(game.getState() != GameState.GAME_OVER){
            // need to write a game over method that will take into account
            // whose turn it is and who played the last piece
            /* check whos turn it is then call gameOver if they have a they have
            a legal move and grave yard no, if they dont have a legal move is graveyard empty
            if no not over, if they dont have a legal move and the graveyard is not empty then
            yes. if the player is asked to draw on an empty graveyard then break the draw loop
            and let the turn increment to next player and check for game over. If game is over
            break the main loop and set gamestate to game over.
             */



            if(game.getTurn()%2 == 0){
                game.setState(GameState.HUM_TURN);
                //human turn
//                System.out.println("The board: ");
                // print the left most and the right most and all of the pieces played.
                game.getBoard().printBoard();



                //game.getComputer().printTray();
                boolean validTurn = false;
                while(!validTurn){
                    validTurn = game.getHuman().playTurn(game.getBoard(),game.getHuman());
                    if(!validTurn){
                        //make sure graveyard is not empty then draw
                        if(game.getGrave().isEmpty()){
                            // game over
                            game.gameOver();
                        }
                        //if grave yard is empty endgame other player wins
                        game.getHuman().getTray().add(game.getGrave().draw());


                    }
                }
                if(validTurn){
                    game.setWinner(game.getHuman().toString());
                }



                game.incTurn();
//                game.getBoard().printBoard();
//                break;
            } else {
                //computer turn
//                System.out.println("The board: ");
                game.setState(GameState.CPU_TURN);
                boolean validTurn = false;
                System.out.print("Computer's turn: \n");

                while(!validTurn){
                    validTurn = game.getComputer().playTurn(game.getBoard(),game.getComputer());
                    // make sure graveyardd is not empty!!! then draw
                    if(!validTurn){
                        if(game.getGrave().isEmpty()){
                            // game over
                            game.gameOver();
                        }
                        game.getComputer().getTray().add(game.getGrave().draw());
                    }

                }
                if(validTurn){
                    game.setWinner(game.getComputer().toString());
                }
                game.incTurn();

                // loop while it is the computers turn, check every piece for a
                // legal move, play the first legal move and change state to
                //
            }
        }

    }
}
