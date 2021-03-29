package automatons;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
public class State {
    private String id;
    private boolean isInit;
    private boolean isExit;
    private HashMap<String, LinkedList<String>> neighbours;

    public State(final String id) {
    	this.id = id;
    	this.isInit = false;
    	this.isExit = false;
	}
    
	public State(final String id, final boolean isInit, final boolean isExit, final HashMap<String, LinkedList<String>> neighbours) {
		this.id = id;
		this.isInit = isInit;
		this.isExit = isExit;
		this.neighbours = neighbours;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isInit() {
		return isInit;
	}

	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}

	public boolean isExit() {
		return isExit;
	}
	
	public void setExit(boolean isExit) {
		this.isExit = isExit;
	}

	public HashMap<String, LinkedList<String>> getNeighbours() {
		return new HashMap<>(neighbours);
	}

	public void setNeighbours(HashMap<String, LinkedList<String>> neighbours) {
		this.neighbours = neighbours;
	}
	/*
	public int contains_letter(String l) {
		for(int i=0; i < neigh_list.size(); i++) {
			if(neigh_list.get(i).letter == l) {
				return i;
			}
		}
		return -1;
	}
	
	public void reduce_list() {
		State new_state = new State(null, false, false, null);
		for(int i=0; i < neigh_list.size(); i++) {
			int place = new_state.contains_letter(neigh_list.get(i).getLetter());
			if(place >= 0) {
				new_state.neigh_list.get(place).number = "" + new_state.neigh_list.get(place).number + this.neigh_list.get(i).getNumber();
			}else {
				new_state.neigh_list.add(this.neigh_list.get(i));
			}
		}
		this.neigh_list = new_state.neigh_list;
		
	}
	
	public void ordonate_list() {
		
	}



	public void concat_states(State a, State b) {
		LinkedList<Neighbour> new_neigh_list = new LinkedList<Neighbour>();  //création de la nouvelle liste de transitions depuis l'état donné
		for(int i = 0 ; i < a.getNeigh_list().size() ; i++) {  //Pour chaque voisin dans A
			for(int j = 0 ; j < b.getNeigh_list().size();j++) {  //Pour chaque voisin dans B
				if(a.getNeigh_list().get(i).getLetter() == b.getNeigh_list().get(j).getLetter()) { // Si la lettre est la même pour les deux états
					Neighbour new_neigh = new Neighbour(a.getNeigh_list().get(i).getLetter(),a.getNeigh_list().get(i).getNumber()+b.getNeigh_list().get(j).getLetter()); // on crée le nouveau voisin à ajouter
					
					/*if(a.getNeigh_list().get(i).getNumber() == b.getNeigh_list().get(j).getNumber()) {  // si le nombre est le même pour les deux états
						new_neigh.setLetter(a.getNeigh_list().get(i).getLetter());  // Le nouveau voisin aura la lettre et les chiffres de A (ou B c'est pareil)
						new_neigh.setNumber(""+a.getNeigh_list().get(i).getNumber()+b.getNeigh_list().get(j).getNumber());
						if(!new_neigh_list.contains(a.getNeigh_list().get(i))){  // Si le nouveau voisin créé n'est pas dans la liste de voisins on l'ajoute
							new_neigh_list.add(a.getNeigh_list().get(i));
							String aaa = "coucou";
							String bbb = ""+aaa.charAt(0)+(aaa.charAt(1));
						}
					}else { // Si les lettres sont les mêmes mais pas les chiffres
						
					}
				}else {
					if(!new_neigh_list.contains(a.getNeigh_list().get(i))){ //Si le voisin de A n'est pas dans la liste on l'ajoute dans la liste
						new_neigh_list.add(a.getNeigh_list().get(i));
					}
					if(!new_neigh_list.contains(b.getNeigh_list().get(j))) {  //Si le voisin de B n'est pas dans la liste on l'ajoute dans la liste
						new_neigh_list.add(b.getNeigh_list().get(j));
					}
				}
			}
		}
		this.id = a.id+b.id;
		//faire en sorte que les id ne soient pas pareil
		this.isInit = a.isInit||b.isInit;
		this.isExit = a.isExit||b.isExit;
		this.neigh_list = new_neigh_list;
		this.reduce_list();
	}
	*/
}
