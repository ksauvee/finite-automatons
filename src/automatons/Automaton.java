package automatons;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Automaton {
    //private int nbStates; a.states.size() renvoie la taille de la liste d'ï¿½tats directement salut
    private LinkedList<State> states;
    protected boolean SYNC;
    protected int S_ALPH=2; // [1;26] et si mot vide [1;27]
    protected final static List<String> alphabet = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
    protected final List<String> aut_alph= alphabet.subList(0, S_ALPH);	
    
    
    public Automaton(LinkedList<State> states, boolean sync, int s_alph) {
		super();
		//this.nbStates = nbStates;
		this.states = states;
		this.SYNC = sync;
		this.S_ALPH = s_alph;
		if(!sync) {
			this.aut_alph.add("*");
		}
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
       	if (several_entries() || several_trans()) {
    		//System.out.println("L'automate n'est pas deterministe");
    		return false;
    	}
    	//System.out.println("L'automate est deterministe");
    	return true;
    }
    
    public boolean several_entries() {
    	//browse every states of an automaton and check if the number of entries is >1
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
    	//give a linked list with 
    	LinkedList<State> entries_list = new LinkedList<State>();
    	for (State state : states) {
    		if (state.isInit()) {
    			entries_list.add(state);
    		}
    	}
    	return entries_list;
    }

    public boolean several_trans() {
    	for (State state : states) {
			HashMap<String, LinkedList<String>> neighbours = state.getNeighbours();
    		for (String letter : neighbours.keySet()) {
    			if (state.several_transitions(letter)) {
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
    
    
    public void simplification_aut() {
    	for(State states : getStates()) {
			for(String letter : aut_alph) {
				if(states.getNeighbours().containsKey(letter)) {
					states.simplification(letter);
				}
			}
		}
    }
    
    public Automaton det_sync() {
    	if(this.isDeterminist()) {
    		return this;
    	}else {
    		simplification_aut();
    		Automaton a = new Automaton(new LinkedList<State>(), true, this.S_ALPH);
    		if(this.several_entries()) {
    			a.getStates().add(concat_list(getEntries()));
    			a.getStates().get(0).setInit(true);
    		}else {
    			a.getStates().add(getEntries().get(0));
    		}
    		LinkedList<State> clone_list = new LinkedList<State>();
    		boolean modif = false;
    		do {
    			modif = false;
    			for(State state : a.getStates()) {
    				for(String letter : aut_alph) {
    					if(state.getNeighbours().containsKey(letter)) {
    						State new_state = this.StringtoState(state.getNeighbours().get(letter).get(0));
    						//new_state.removeDuplicates();
    						clone_list.add(new_state);
    					}
    				}
    			}
    			boolean add = true;
    			for(State state : clone_list) {
    				add = true;
    				for(State state2 : a.getStates()) {
    					if(state.getId().equals(state2.getId())) {
    						add = false;
    					}
    				}
    				if(add && state.getId()!= "") {
    					a.getStates().add(state);
    					modif = true;
    				}
    			}
    			clone_list.clear();
    		}while(modif);
    		return a;
    	}
    }
    
    public State concat_list(LinkedList<State> list) {
    	State state_concat = new State("", false, false, new HashMap<String, LinkedList<String>>());
    	while(list.size()>1) {
    		state_concat.concat(list.get(0), list.get(1), aut_alph);
    		list.remove(0);
    		list.remove(0);
    		list.addFirst(state_concat);
    	}
    	return state_concat;
    }

	public State StringtoState(String stringstates) {
		String[] stringstatesArray = stringstates.split("\\.");
		LinkedList<State> list = new LinkedList<State>();
		for(String index : stringstatesArray) {
			for(State states : this.getStates()) {
				if(index.equals(states.getId())) {
					list.add(states);
				}
			}
		}
		State new_state;
		if(list.size()==1) {
			new_state = list.get(0);
		}else {
			new_state = concat_list(list);
		}
		
		return new_state;
		
	}
	
	
	public void remove_epsilon(){
		HashMap<String, LinkedList<String>> closed_epsilon = new HashMap<String, LinkedList<String>>();
		
	}

}
