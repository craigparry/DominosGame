/** Craig Parry
 * This file is used to create a Garveyard for the DominosGame that holds the
 * reserve of dominos for the players to draw from. This file needs to run with
 * Dominos.java, Player.java, DominosGUI.java, GameState.java, Board.java,
 * and MainGameLoop.java
 *
 */
package Dominos;
import java.util.*;

public class GraveYard extends LinkedList<Dominos> {

    /** Constructor for the GraveYard class
     *
     */
    public GraveYard(){
        int max = Dominos.getMax();
        for(int i = 0; i<= max; i++){
            for(int k = 0; k <= max; k++){
                this.add(new Dominos(i,k));
            }

        }
    }

    /** print function for testing the graveyard
     * @param
     * @return void
     */
    public void print(){
        for(Dominos s: this){
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
        Collections.shuffle(this);
        return this.remove(0);
    }

}
