package Dominos;




public class MainGameLoop {
    private GraveYard grave;
    private Player human;
    private Player computer;
    private int turn;
    private GameState state;
    private Board board;

    public MainGameLoop(){
        newGame();
    }

    public void newGame(){
        turn = 0;
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

    public static void main(String[] args) {
        // write your code here
//        Dominos ex = new Dominos(2,7);
//        GraveYard examp = new GraveYard();
//        examp.print();
        MainGameLoop game = new MainGameLoop();

//        loop for player turns
        while(game.getState() != GameState.GAME_OVER){
            if(game.getTurn()%2 ==0){
                //human turn
//                System.out.println("The board: ");
                // print the left most and the right most and all of the pieces played.
                game.getBoard().printBoard();



                //game.getComputer().printTray();
                boolean validTurn = false;
                while(!validTurn){
                    validTurn = game.getHuman().playTurn(game.getBoard(),game.getHuman());
                    if(!validTurn){
                        game.getHuman().getTray().add(game.getGrave().draw());
                    }
                }



                game.incTurn();
//                game.getBoard().printBoard();
//                break;
            } else {
                //computer turn
//                System.out.println("The board: ");
                boolean validTurn = false;
                System.out.print("Computer's turn: \n");

                while(!validTurn){
                    validTurn = game.getComputer().playTurn(game.getBoard(),game.getComputer());
                    game.getComputer().getTray().add(game.getGrave().draw());
                }

                game.incTurn();

                // loop while it is the computers turn, check every piece for a
                // legal move, play the first legal move and change state to
                //
            }
        }

    }
}
