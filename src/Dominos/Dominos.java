package Dominos;

public class Dominos {

    private int leftSide;
    private int rightSide;
    private static final int max = 6;

    public Dominos(int left, int right){
        this.leftSide = left;
        this.rightSide = right;
    }

    public static int getMax() {
        return max;
    }
    public int getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(int leftSide) {
        this.leftSide = leftSide;
    }

    public int getRightSide() {
        return rightSide;
    }

    public void setRightSide(int rightSide) {
        this.rightSide = rightSide;
    }

    @Override
    public String toString(){
        return leftSide+ ":"+ rightSide;
    }
    public void flip(){
        int hold = leftSide;
        leftSide= rightSide;
        rightSide= hold;
    }

}
