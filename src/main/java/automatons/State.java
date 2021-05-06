package automatons;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class State {
    private final String id;
    private boolean isInit;
    private boolean isExit;
    private HashMap<String, LinkedList<String>> neighbours;

    public State(String id) {
        this.id = id;
        this.isInit = false;
        this.isExit = false;
        neighbours = new HashMap<>();
    }

    public State(String id, final boolean isInit) {
        this.id = id;
        this.isInit = isInit;
        this.isExit = false;
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

    public void addNeighbour(final String symbol, final String arrivalState) {
        LinkedList<String> letterTransitions = neighbours.get(symbol);

        if (letterTransitions == null) {
            LinkedList<String> newTransition = new LinkedList<>();
            newTransition.add(arrivalState);
            neighbours.put(symbol, newTransition);
        } else if (!letterTransitions.contains(arrivalState)) {
            // we check the transition doesn't exist in order to avoid duplicates
            letterTransitions.add(arrivalState);
        }
    }

    public boolean isIncomplete(int nbAlphabetSymbols) {
        return neighbours.size() != nbAlphabetSymbols;
    }

    public int completion(List<String> alphabet) {
        int nbSupTransitions = 0;
        for(String letter : alphabet) {
            if(!neighbours.containsKey(letter)){
                addNeighbour(letter, "P");
                nbSupTransitions++;
            }
        }
        return nbSupTransitions;
    }
}
