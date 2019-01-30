package Dominos;

import java.util.*;

public class Player {
    private boolean comp;

    private List<Dominos> tray;

    public Player(boolean comp){
        comp = comp;
        tray = new LinkedList<>();

    }

    public List<Dominos> getTray() {
        return tray;
    }

    public void printTray(){
        for(Dominos s: tray){
            System.out.print(s.toString()+", ");
        }
        System.out.println();
    }

    public void setTray(List<Dominos> newTray){
        tray = newTray;

    }

}
