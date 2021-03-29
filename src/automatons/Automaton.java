package automatons;

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
    /*
    public boolean is_determinist(Automaton a) {
		
    	if(several_entries(a) && several_tr(a)) {
    		System.out.println("L'automate n'est pas d�terministe car il a plusieurs entr�es "
    				+ "et plusieurs transitions libell�es par le m�me caract�re");
    		return false;
    	}else if(several_entries(a)) {
    		System.out.println("L'automate n'est pas d�terministe car il a plusieurs entr�es");
    		return false;
    	}else if(several_tr(a)) {
    		System.out.println("L'automate n'est pas d�terministe car il a "
    				+ "plusieurs transitions libell�es par le m�me caract�re");
    		return false;
    	}
    	return true;
    }
    
    public boolean several_entries(Automaton a) {
    	int entries_cpt = 0;   //A ce stade l'automate a zero entr�e
    	for(int i = 0; i < (a.states).size() ; i++) {
    		if(a.states.get(i).isInit()) { // on compte les entr�es
    			entries_cpt++;
    		}
    		if(entries_cpt > 1) return false;  // entries est sup�rieur � 1 ? Si oui true sinon false
    	}
    	return true;
    }

    public boolean several_tr(Automaton a) {
    	for(int i = 0; i < a.states.size() ; i++) {  //size() = m�thode dans java.util qui donne la taille d'une LC
    		if(a.states.get(i).getNeigh_list().size() > s_alph) {
    			/*Si la liste des voisins est > � la taille de l'alphabet on a forc�ment plusieurs transitions pour un
    			 * caract�re donn� � partir du i eme �tat -> l'automate est donc no  d�terministe
    			 * 
    			 * idem get(i) donne le i eme element de la LC
    			 * ni copie ni affectation juste on regarde la liste -> pas de modification -> pas besoin de copie s�cur

    			return true;
    		}
    	}
		return false;
    }

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
