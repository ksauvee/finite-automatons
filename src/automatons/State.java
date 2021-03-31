package automatons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    
    public boolean haveAllTransitions(int nbSymbols) {
		final LinkedList<String> listeTransi = null;
    	for(int i = 0; i < this.neighbours.size(); ++i) {
    		listeTransi.add(neighbours.get(i)[0]);
    	}
		Set<String> mySet = new HashSet<String>(listeTransi);
	    List<String> listeSansDoublons = new ArrayList<String>(mySet);
	    if(listeSansDoublons.size() < nbSymbols) {
	    	return false;
	    }else {
	    	return true;
	    }
    }
}
