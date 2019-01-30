package Dominos;

import java.util.*;


public class GraveYard {

    private List<Dominos> grave;

    public GraveYard(){
        int max = Dominos.getMax();
        grave = new LinkedList<>();
        for(int i = 0; i<= max; i++){
            for(int k = 0; k <= max; k++){
                grave.add(new Dominos(i,k));
            }

        }
    }

    /** print function for testing
     * @param none
     * @return void
     */
    public void print(){
        for(Dominos s: grave){
            System.out.print(s.toString()+ ", ");

        }
    }

    /** Inital draw for player trays, draws 7 random dominos
     *
     * @param tray
     * @return List<Dominos>
     */
    public List<Dominos> initDraw(List<Dominos> tray){
        for(int i = 0; i< 7; i++){
            tray.add(draw());
        }
        return tray;
    }
    /** Draws a random domino tile from the the graveyard assuming that there are
     * dominos remaining in the graveyard.
     *
     * @return Dominos
     */
    public Dominos draw(){
        Collections.shuffle(grave);
        return grave.remove(0);
    }

}
