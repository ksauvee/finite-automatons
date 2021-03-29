package automatons;

import java.util.HashMap;
import java.util.LinkedList;

public class Automaton {
    //private int nbStates; a.states.size() renvoie la taille de la liste d'�tats directement
    private LinkedList<State> states;
    public static int s_alph; // [1;26] et si mot vide [1;27]
    
    //Vous me suivez ? Vous confirmez ?
    
    public Automaton(int nbStates, LinkedList<State> states) {
		super();
		//this.nbStates = nbStates;
		this.states = states;
	}

	/*public int getNbStates() {
		return nbStates;
	}

	public void setNbStates(int nbStates) {
		this.nbStates = nbStates;
	}*/

	public LinkedList<State> getStates() {
		return states;
	}

	public void setStates(LinkedList<State> states) {
		this.states = states;
	}

	public static int getS_alph() {
		return s_alph;
	}

	public static void setS_alph(int s_alph) {
		Automaton.s_alph = s_alph;
	}

    public boolean isDeterminist() {
    	if (several_entries() || several_transitions()) {
    		System.out.println("L'automate n'est pas deterministe");
    		return false;
    	}
    	System.out.println("L'automate est deterministe");
    	return true;
    }
    
    public boolean several_entries() {
    	int nbEntries = 0;
    	for (State state : states) {
    		if (state.isInit()) {
    			++nbEntries;
    		}

    		if (nbEntries > 1) {
    			return true;
			}
    	}
    	return true;
    }

    public boolean several_transitions() {
    	for (State state : states) {
			HashMap<String, LinkedList<String>> neighbours = state.getNeighbours();
    		for (String letter : neighbours.keySet()) {
    			if (neighbours.get(letter).size() > 1) {
    				return true;
				}
			}
		}

    	return false;
    }

    /*
	public Automaton det_sync(Automaton a){
    	if(is_determinist(a)) { //si l'automate est d�j� d�terministe
    		return a;
    	}else {
    		Automaton a_det = new Automaton(0, null);
    		LinkedList<State> new_states_list = new LinkedList<State>();
			if(several_entries(a)) {
				LinkedList<State> entries_list = new LinkedList<State>();
				for(int i = 0; i < a.states.size() ; i++) {
					if(a.states.get(i).isInit()) {
						entries_list.add(states.get(i));  // on r�cup�re la liste des entr�es
					}
				}
				LinkedList<State> new_entries_list = (LinkedList<State>) entries_list.clone();
				for(int i = 0 ; i < entries_list.size() ; i++) {
					State new_entrie = new State(null, false, false, null);
					new_entrie.concat_states(entries_list.get(i), entries_list.get(i+1));
					new_entries_list.add(new_entrie);
				}
			}
	    	return a_det;
    	}
    	
    }
    
    
    
    */
}
