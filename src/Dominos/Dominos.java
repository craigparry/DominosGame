package Dominos;

public class Dominos{

    private int leftSide;
    private int rightSide;
    private static final int max = 6;
    private DomPos position;

    public Dominos(int left, int right){
        this.leftSide = left;
        this.rightSide = right;
        position = DomPos.NONE;
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
//    @Override
//    public boolean equals(Dominos passed){
//        if(this.leftSide == passed.getLeftSide() &&
//           this.rightSide == passed.getLeftSide())
//
//    }

    public enum DomPos {
        TOP_ROW, BOT_ROW,NONE;
    }

}
