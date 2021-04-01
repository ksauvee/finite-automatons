package automatons;

import java.util.HashMap;
import java.util.LinkedList;


public class State {
    private final String id;
    private boolean isInit;
    private boolean isExit;
    private final HashMap<String, LinkedList<String>> neighbours;

    public State(String id) {
        this.id = id;
        neighbours = new HashMap<>();
    }

    public State(State state) {
        this.id = state.getId();
        this.isInit = state.getIsInit();
        this.isExit = state.getIsExit();
        this.neighbours = state.getNeighbours();
    }

    public String getId() {
        return id;
    }

    public boolean getIsInit() {
        return isInit;
    }

    public boolean getIsExit() {
        return isExit;
    }

    public HashMap<String, LinkedList<String>> getNeighbours() {
        return new HashMap<>(neighbours);
    }

    public void setIsInit(final boolean isInit) {
        this.isInit = isInit;
    }

    public void setIsExit(final boolean isExit) {
        this.isExit = isExit;
    }

    public void addNeighbour(final String letter, final String arrivalState) {
        LinkedList<String> letterTransitions = neighbours.get(letter);

        if (letterTransitions == null) {
            LinkedList<String> newTransition = new LinkedList<>();
            newTransition.add(arrivalState);
            neighbours.put(letter, newTransition);
        } else {
            letterTransitions.add(arrivalState);
        }
    }
}
