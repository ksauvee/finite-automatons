package automatons;

import java.util.LinkedList;

public class State {
    private String id;
    private boolean isInit;
    private boolean isExit;
    private LinkedList<String[]> neighbours;
}
