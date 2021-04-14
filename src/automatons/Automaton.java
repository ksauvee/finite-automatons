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
    	if (several_entries() || A()) {
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

    public boolean A() {
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

    
    public Automaton det_sync() {
    	if(this.isDeterminist()) {
    		return this;
    	}else {
    		Automaton a = new Automaton(new LinkedList<State>(), true, this.S_ALPH);
    		if(a.several_entries()) {
    			a.getStates().add(concat_list(getEntries()));
    		}else {
    			a.getStates().add(getEntries().get(0));
    		}
    		int size_before;
    		do{
    			size_before=a.getStates().size();
    			LinkedList<State> list = new LinkedList<State>(a.getStates());
    			for(State states : a.getStates()) {
    				for(String letter : a.aut_alph) {
    					if(states.getNeighbours().containsKey(letter)) {
    						for(String id : states.getNeighbours().get(letter)) {
        						if(!a.getStates().contains(this.StringtoState(id))){
        							list.add(this.StringtoState(id));
        						}
        					}
    					}
    				}
    			}
    			a.getStates().clear();
    			a.getStates().addAll(list);
    			System.out.println(a.getStates().size()+" avant "+size_before);
    		}while(size_before != a.getStates().size());
    		return a;
    	}
		
		/*LinkedList<State> entries_list = new LinkedList<State>();
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
		this.states = new_list;*/
    }
    
    public State concat_list(LinkedList<State> list) {
    	State state_concat = new State("", false, false, new HashMap<String, LinkedList<String>>());
    	while(list.size()>1) {
    		state_concat.concat(list.get(0), list.get(1), aut_alph);
    		list.remove(0);
    		list.remove(0);
    		list.addFirst(state_concat);
    		System.out.println("here");
    	}
    	return state_concat;
    }

	public State StringtoState(String stringstates) {
		System.out.println("Salut");
		System.out.println(stringstates);
		String[] stringstatesArray = stringstates.split("\\.");
		int j = 0;
		for(String i : stringstatesArray) {
			System.out.println("Element "+j+"du tableau : "+ i);
			j++;
		}
		LinkedList<State> list = new LinkedList<State>();
		for(String index : stringstatesArray) {
			for(State states : this.getStates()) {
				if(index.equals(states.getId())) {
					list.add(states);
				}
			}
		}
		State new_state = concat_list(list);
		return new_state;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
	    			//System.out.println("On est là"+states.getId());
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
