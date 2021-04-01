package automatons;

import java.util.HashMap;
import java.util.LinkedList;


public class State {
    private final String id;
    private boolean isInit;
    private boolean isExit;
    private HashMap<String, LinkedList<String>> neighbours;

    public State(String id) {
        this.id = id;
        neighbours = new HashMap<>();
    }

    public State(String id, final boolean isInit) {
        this.id = id;
        this.isInit = isInit;
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
        HashMap<String, LinkedList<String>> newNeighbours = new HashMap<>();

        for (String transition : neighbours.keySet()) {
            LinkedList<String> newStateNeighbours = new LinkedList<>(neighbours.get(transition));
            newNeighbours.put(transition, newStateNeighbours);
        }

        return newNeighbours;
    }

    public void setNeighbours(HashMap<String, LinkedList<String>> neighbours) {
        this.neighbours = new HashMap<>(neighbours);
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
