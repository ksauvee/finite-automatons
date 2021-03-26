package automatons;

import java.util.LinkedList;

public class State {
    private final String id;
    private boolean isInit;
    private boolean isExit;
    private final LinkedList<String[]> neighbours;

    public State(String id) {
        this.id = id;
        neighbours = new LinkedList<>();
    }

    public void setIsInit(final boolean isInit) {
        this.isInit = isInit;
    }

    public void setIsExit(final boolean isExit) {
        this.isExit = isExit;
    }

    public void addNeighbour(final String letter, final String arrivalState) {
        String[] transition = new String[2];

        transition[0] = letter;
        transition[1] = arrivalState;

        neighbours.add(transition);
    }
}
