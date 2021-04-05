package automatons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Automaton {
    //private int nbStates; a.states.size() renvoie la taille de la liste d'ï¿½tats directement
    private LinkedList<State> states;
    protected boolean SYNC;
    protected int S_ALPH=2; // [1;26] et si mot vide [1;27]
    protected final static List<String> alphabet = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
    protected final List<String> aut_alph= alphabet.subList(0, S_ALPH);
    
    //Vous me suivez ? Vous confirmez ?
    
    public Automaton(LinkedList<State> states, boolean sync, int s_alph) {
		super();
		//this.nbStates = nbStates;
		this.states = states;
		this.SYNC = sync;
		this.S_ALPH = s_alph;
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
    	LinkedList<State> entries_list = new LinkedList<State>();
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
    
 public Automaton automatonDeterminisation() {
    	return new Automaton(states, true, S_ALPH);
    }
    public void det_sync(Automaton a) {
		Automaton new_a = new Automaton(a.states, a.SYNC, a.S_ALPH);
		LinkedList<State> entries_list = new LinkedList<State>();
		if(new_a.several_entries()) {
			entries_list.addAll(a.getEntries());
			do {
				State new_state = new State("", false, false, new HashMap<String, LinkedList<String>>());
				System.out.println(entries_list.get(0).getId() + ""+ entries_list.get(0).getNeighbours());
				System.out.println("\n");
				new_state.concat(entries_list.get(0), entries_list.get(1), a.aut_alph);
    			entries_list.addLast(new_state);
				entries_list.remove(0);
				entries_list.remove(0);
				System.out.println(entries_list.get(0).getId() + ""+ entries_list.get(0).getNeighbours());
				System.out.println("\n");
			}while(entries_list.size()>1);
			
			/*if(new_a.several_transitions()) {
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
			new_list.clear();*/
			
		}
		this.states.addAll(entries_list);
		this.SYNC = true;
		this.S_ALPH = a.S_ALPH;
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
