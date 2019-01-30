package Dominos;

import java.util.*;

public class Player {


    private List<Dominos> tray;

    public Player(){

        tray = new LinkedList<>();

    }

    public List<Dominos> getTray() {
        return tray;
    }

}
