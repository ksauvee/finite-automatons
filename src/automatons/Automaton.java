package automatons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Automaton {
    //private int nbStates; a.states.size() renvoie la taille de la liste d'ï¿½tats directement
    private LinkedList<State> states;
    protected final boolean SYNC;
    protected final int S_ALPH; // [1;26] et si mot vide [1;27]
    public final static List<String> alphabet = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
    protected static List<String> aut_alph;
    
    //Vous me suivez ? Vous confirmez ?
    
    public Automaton(int nbStates, LinkedList<State> states, boolean sync, int s_alph) {
		super();
		//this.nbStates = nbStates;
		this.states = states;
		this.SYNC = sync;
		this.S_ALPH = s_alph;
		aut_alph=alphabet.subList(0, s_alph);
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
    	return false;
    }
    
    public LinkedList<State> getEntries(){
    	LinkedList<State> entries_list = new LinkedList<State>(null);
    	for (State state : states) {
    		if (state.isInit()) {
    			entries_list.add(state);
    		}
    	}
    	return entries_list;
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

    
    public LinkedList<State> getSeveralTrans(){
    	LinkedList<State> several_trans_list = new LinkedList<State>(null);
    	for (State state : states) {
			HashMap<String, LinkedList<String>> neighbours = state.getNeighbours();
    		for (String letter : neighbours.keySet()) {
    			if (neighbours.get(letter).size() > 1) {
    				several_trans_list.add(state);
				}
			}
		}
    	return several_trans_list;
    }
    //fonction recuperant les entrées et fonction récupérant les états rendant l'automate non déterministe

    public Automaton det_sync(Automaton a) {
		Automaton new_a = a;
		LinkedList<State> entries_list = null;
		LinkedList<State> new_list = null;
		while(!new_a.isDeterminist()) {
			if(new_a.several_entries()) {
    			entries_list = a.getEntries();
    			int i = 0;
    			do {
    				State new_state = new State(null);
    				new_state.concat(entries_list.get(0), entries_list.get(1));
    				entries_list.remove(0);
    				entries_list.remove(1);
    				entries_list.add(new_state);
    			}while(entries_list.size()>1);
    		}
			if(new_a.several_transitions()) {
				new_list = a.getSeveralTrans();
				do {
    				State new_state = new State(null);
    				new_state.concat(entries_list.get(0), entries_list.get(1));
    				entries_list.remove(0);
    				entries_list.remove(1);
    				entries_list.add(new_state);
    			}while(entries_list.size()>1);
			}
			entries_list.clear();
			new_list.clear();
			
		}
		return new_a;
    }
    /*
	public Automaton det_sync(Automaton a){
    	if(is_determinist(a)) { //si l'automate est dï¿½jï¿½ dï¿½terministe
    		return a;
    	}else {
    		Automaton a_det = new Automaton(0, null);
    		LinkedList<State> new_states_list = new LinkedList<State>();
			if(several_entries(a)) {
				LinkedList<State> entries_list = new LinkedList<State>();
				for(int i = 0; i < a.states.size() ; i++) {
					if(a.states.get(i).isInit()) {
						entries_list.add(states.get(i));  // on rï¿½cupï¿½re la liste des entrï¿½es
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
