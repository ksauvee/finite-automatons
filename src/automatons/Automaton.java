package automatons;

import java.util.LinkedList;

public class Automaton {
	private Automaton next;
    private int nbStates;
    private LinkedList<State> states;
    public static int s_alph; // [1;26]
    
    //Vous me suivez ? Vous confirmez ?
    
    
    public boolean is_determinist(Automaton a) {
    	int n=1;
    	while(a.states != null) {
    		
    		a.states = (a.states).next;
    		n++;
    		while((this.neighbours).states != null) {
    			
    		}
	    	if(n > s_alph) {
	    		return false;
	    	}
    	}
    	
    	return true;
    }
}
