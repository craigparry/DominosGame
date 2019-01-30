package Dominos;

public class MainGameLoop {
    private GraveYard grave;
    private Player human;
    private Player computer;
    private int turn;
    public MainGameLoop(){

    }
    public void newGame(){
        turn = 0;
        human = new Player();
        computer = new Player();
        grave = new GraveYard();


    }

    public static void main(String[] args) {
        // write your code here
//        Dominos ex = new Dominos(2,7);
        GraveYard examp = new GraveYard();
        examp.print();
    }
}
