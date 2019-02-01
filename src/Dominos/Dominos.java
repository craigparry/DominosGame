/** Craig Parry
 * This file is used to create Dominos for the DominoGame. Use this file with
 * GraveYard.java, Player.java, DominosGUI.java, GameState.java, MainGameLoop.java,
 * and Board.java
 *
 */

package Dominos;

public class Dominos{

    private int leftSide;
    private int rightSide;
    private static final int max = 6;

    /** Constructor for the Domino class that takes and int for the left and
     * right side of the domino.
     *
     * @param left
     * @param right
     */
    public Dominos(int left, int right){
        this.leftSide = left;
        this.rightSide = right;
    }

    /** Getter for the max amount of numbers allowed on the tile of the dominos
     *
     * @return int
     */
    public static int getMax() {
        return max;
    }

    /** getter for the number on the left side of the domino
     *
     * @return int
     */
    public int getLeftSide() {
        return leftSide;
    }

    /** getter for the number on the right side of the domino
     *
     * @return int
     */
    public int getRightSide() {
        return rightSide;
    }

    /** Writes the domino as a string left value : right value
     *
     * @return String
     */
    @Override
    public String toString(){
        return leftSide+ ":"+ rightSide;
    }

    /** flips the numbers on the dominos
     *
     */
    public void flip(){
        int hold = leftSide;
        leftSide= rightSide;
        rightSide= hold;
    }
}
