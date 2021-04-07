package automatons;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Automaton {
    //private int nbStates; a.states.size() renvoie la taille de la liste d'�tats directement
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
    	if (several_entries() || several_transitions()) {
    		//System.out.println("L'automate n'est pas deterministe");
    		return false;
    	}
    	//System.out.println("L'automate est deterministe");
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
    //fonction recuperant les entr�es et fonction r�cup�rant les �tats rendant l'automate non d�terministe
    
    public void det_sync(Automaton a) {
		this.SYNC = true;
		this.S_ALPH = a.S_ALPH;
		LinkedList<State> entries_list = new LinkedList<State>();
		entries_list.addAll(a.getEntries());
		if(a.several_entries()) {
			do {
				State new_state = new State("", false, false, new HashMap<String, LinkedList<String>>());
				new_state.concat(entries_list.get(0), entries_list.get(1), a.aut_alph);
    			entries_list.addLast(new_state);
				entries_list.remove(0);
				entries_list.remove(0);
			}while(entries_list.size()>1);
		}
		LinkedList<State> new_list = new LinkedList<State>();
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
							//end = true;
						}
					}
				}
			}
			new_list.clear();
			new_list.addAll(new_list2);
			
		}
		this.states = new_list;
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
		System.out.println("iiiiiiiiii");
		for(State states : list) {
			System.out.println("ID : "+states.getId());
			System.out.println("Init : "+states.isInit());
			System.out.println("Exit : "+states.isExit());
			for(Map.Entry<String, LinkedList<String>> entry : states.getNeighbours().entrySet()) {
				System.out.println(entry);
			}
			System.out.println("             fin Etat "+states.getId()+"\n\n");
		}
		return this.getStates().get(0);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*//System.out.println("stringstates"+stringstates);
		String[] stringstatesArray = stringstates.split("\\."); //01.23.34 [01., 23. ,34. ] [01, ., 23, .,34,.]
	    //System.out.println(Arrays.toString(stringstatesArray));
	    LinkedList<State> list = new LinkedList<State>();
	    for(String number : stringstatesArray){
	    	//System.out.println("for : String");
	    	for(State states : this.getStates()) {
	    		//System.out.println("for : states");
	    		if(states.getId()==(number)) {
	    			list.add(states);
	    			//System.out.println("On est l�"+states.getId());
	    		}
	    	}
	    }
	    //System.out.println("Affichage de list");
		/*for(State states1 : list) {
			System.out.println("Affichage de list for");
			System.out.println("ID : "+states1.getId());
			System.out.println("Init : "+states1.isInit());
			System.out.println("Exit : "+states1.isExit());
			for(Map.Entry<String, LinkedList<String>> entry : states1.getNeighbours().entrySet()) {
				System.out.println(entry);
			}
		}
	    
	    
	    
	    State new_state = new State("", false, false, new HashMap<String, LinkedList<String>>());
	    if(list.size()>0) {
	    	do {
				new_state.concat(list.get(0), list.get(1), this.aut_alph);
				list.addLast(new_state);
				list.remove(0);
				list.remove(0);
			}while(list.size()>1);
	    }
		return new_state;
		
		*
		public State StringtoState(String stringstates) {
		int i = 0;
		State new_state = new State("", false, false, new HashMap<String, LinkedList<String>>());
		State new_state2 = new State(new_state);
		while(i<stringstates.length()) {
			//System.out.println("Automate :" + this.states.get((int)stringstates.charAt(i)-49).getId()+ this.states.get((int)stringstates.charAt(i)-49).getNeighbours());
			new_state.concat(new_state2, this.states.get((int)stringstates.charAt(i)-49), aut_alph);
			i++;
			new_state2 = new State(new_state);
		}
		return new_state;
	}*/
	}
}
