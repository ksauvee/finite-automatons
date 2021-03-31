package automatons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Collections;

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
		final LinkedList<String> listeTransi = new LinkedList<String>();
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
    
    public void completeTransition(int nbSymbols) {
    	//on stock toute les transition dans un tableau séparé pour les traiter, sous forme d'entier en ASCII -- A METTRE EN ENTIER ASCII
		final LinkedList<Integer> listeTransi = new LinkedList<Integer>();
		for(int i = 0; i < neighbours.size(); i++ ) {
			listeTransi.add(neighbours.get(i)[0]);
		}
		//on supprime les doublons
		Set<Integer> mySet = new HashSet<Integer>(listeTransi);
	    List<Integer> listeSansDoublons = new ArrayList<Integer>(mySet);
	    //on range la liste
	    Collections.sort(listeSansDoublons);
	    int nbVides = 0, currentValue = 0;
	    //on vérifie pour chaque mot si il existe une transition sinon on la crée -- A REVOIR
	    for(int i = 0; i < nbSymbols ; i++) {
	    	currentValue = Character.getNumericValue(listeSansDoublons.get(i-nbVides).charAt(0));
	    	if(currentValue - 97 != i) {
	    		this.addNeighbour(Character.toString((char)i+nbVides), "P");
	    		nbVides++;
	    		currentValue++;
	    	}
	    }
	    /*
	    for(int i = 0; i < listeSansDoublons.size() ; i++) {
	    	currentValue = Character.getNumericValue(listeSansDoublons.get(i+nbVides).charAt(0));
	    	while(!(currentValue - 97 == i + nbVides)) {
	    		this.addNeighbour(Character.toString((char)i+nbVides), "P");
	    		nbVides++;
	    	}
	    }
	    */
    }
}
