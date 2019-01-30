package Dominos;




public class MainGameLoop {
    private GraveYard grave;
    private Player human;
    private Player computer;
    private int turn;
    private GameState state;

    public MainGameLoop(){
        newGame();
    }

    public void newGame(){
        turn = 0;
        grave = new GraveYard();
        /*initialize the player and computer trays*/
        human = new Player();
       // human.setTray(grave.initDraw(human.getTray()));
        grave.initDraw(human.getTray());
        computer = new Player();
        grave.initDraw(computer.getTray());

        System.out.print("New Game: \n");
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
                System.out.println("Player make your turn.");
                System.out.print("Your tray: ");
                game.getHuman().printTray();

                game.incTurn();
                break;
            } else {
                //computer turn

                System.out.print("Computer's turn: \n");

                // loop while it is the computers turn, check every piece for a
                // legal move, play the first legal move and change state to
            }
        }

    }
}
