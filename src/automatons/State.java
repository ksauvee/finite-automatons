package automatons;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Arrays;
import java.util.Collections;


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

	
	public void concat(State a, State b) {
		for(String letter : Automaton.aut_alph) {
			if(!this.neighbours.containsKey(letter)) {
				if(a.neighbours.containsKey(letter) && b.neighbours.containsKey(letter)){
					a.neighbours.get(letter).addAll(b.neighbours.get(letter)); // on modifie la liste de base !?
					this.neighbours.put(letter, a.neighbours.get(letter));
					this.simplification(letter);
				}else if(a.neighbours.containsKey(letter)) {
					this.neighbours.put(letter, a.neighbours.get(letter));
					this.simplification(letter);
				}else if(b.neighbours.containsKey(letter)) {
					this.neighbours.put(letter, b.neighbours.get(letter));
					this.simplification(letter);
				}
			}
		}
		this.id = a.id+b.id;
		//faire en sorte que les id ne soient pas pareil
		this.isInit = a.isInit||b.isInit;
		this.isExit = a.isExit||b.isExit;
	}
	
	public void simplification(String letter) {
		// on a un dictionnaire avec les lettres libellant toutes les transitions du nouvel état
		// Cependant on a des doublons on veut donc les supprimer
		LinkedHashSet<String> hSetNeighbours = new LinkedHashSet<String>(this.neighbours.get(letter)); //on crée une nouvelle liste de type hset qui en supporte pas les doublons
		this.neighbours.get(letter).clear(); // on clear la liste de nos voisins
		this.neighbours.get(letter).addAll(hSetNeighbours); // on ajoute maintenant les éléments dans la liste principale
		Collections.sort(this.neighbours.get(letter)); // on ordonne la liste
		String newStateNeighbour ="";
		for(String number : this.neighbours.get(letter)) {
			newStateNeighbour.concat(number);
		}
		this.neighbours.get(letter).clear(); // on clear à nouveau la liste de nos voisins
		this.neighbours.get(letter).add(newStateNeighbour);
	}
	
    public void removeDoublon_string() {
        String output = new String();
        for (int i = 0; i < this.getId().length(); i++) {
            for (int j = 0; j < output.length(); j++) {
                if (this.getId().charAt(i) != output.charAt(j)) {
                    output = output + this.getId().charAt(i);
                }
            }
        }
        this.id = output;
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
