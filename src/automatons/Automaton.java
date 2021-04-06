package automatons;
//salut1
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
    
    public void det_sync(Automaton a) {
		this.SYNC = true;
		this.S_ALPH = a.S_ALPH;
		LinkedList<State> entries_list = new LinkedList<State>();
		LinkedList<State> new_list = new LinkedList<State>();
		if(a.several_entries()) {
			entries_list.addAll(a.getEntries());
			do {
				State new_state = new State("", false, false, new HashMap<String, LinkedList<String>>());
				new_state.concat(entries_list.get(0), entries_list.get(1), a.aut_alph);
    			entries_list.addLast(new_state);
				entries_list.remove(0);
				entries_list.remove(0);
			}while(entries_list.size()>1);
		}
		new_list.addAll(entries_list);
		boolean end = true;
		LinkedList<State> new_list2 = new LinkedList<State>();
		while(end) {
			end = false;
			new_list2.addAll(new_list);
			for(State states : new_list) {
				for(String letter : aut_alph) {
					for(String Stringstates : states.getNeighbours().get(letter)) {
						State concat_state = a.StringtoState(Stringstates);
						concat_state.removeDuplicates();
						if(!new_list2.contains(concat_state)) {
							new_list2.add(concat_state);
							end = true;
						}
					}
				}
			}
			new_list.clear();
			new_list.addAll(new_list2);
			System.out.println(end);
			
		}
		this.states = new_list;
    }

	public State StringtoState(String stringstates) {
		int i = 0;
		int index=0;
		LinkedList<State> states_concat = new LinkedList<State>();
		while(i<stringstates.length()) {
			index=0;
			for(State s : this.states) {
				if(s.getId().equals(String.valueOf(stringstates.charAt(i)))) {
					states_concat.add(this.states.get(index));
				}
				index++;
			}
			//find the state which has the correct id
			i++;
		}
		while(states_concat.size()>1) {
			State new_state = new State("", false, false, new HashMap<String, LinkedList<String>>());
			new_state.concat(states_concat.get(0), states_concat.get(1), aut_alph);
			states_concat.addLast(new_state);
			states_concat.remove(0);
			states_concat.remove(0);
		}
		return states_concat.getFirst();
	}
}
