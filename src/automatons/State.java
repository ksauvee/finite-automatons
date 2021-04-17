package automatons;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Arrays;
import java.util.Collections;

//salut 
public class State {
    private String id;
    private boolean isInit;
    private boolean isExit;
    private HashMap<String, LinkedList<String>> neighbours;


    
	public State(final String id, final boolean isInit, final boolean isExit, final HashMap<String, LinkedList<String>> neighbours) {
		this.id = id;
		this.isInit = isInit;
		this.isExit = isExit;
		this.neighbours = neighbours;
	}

	public State(State other_state) {
		this.id = other_state.id;
		this.isInit = other_state.isInit;
		this.isExit = other_state.isExit;
		this.neighbours = new HashMap<String, LinkedList<String>>(other_state.neighbours);
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
	
	public boolean several_transitions(String letter) {
		if (neighbours.get(letter).size() > 1) {
			return true;
		}
		return false;
	}

	
	public void concat(State a, State b, List<String> aut_alph) {
		for(String letter : aut_alph) {
			if(!this.neighbours.containsKey(letter)) {
				if(a.neighbours.containsKey(letter) && b.neighbours.containsKey(letter)){
					this.neighbours.put(letter, new LinkedList<>());
					this.neighbours.get(letter).addAll(a.neighbours.get(letter));
					this.neighbours.get(letter).addAll(b.neighbours.get(letter));
					this.simplification(letter);
				}else if(a.neighbours.containsKey(letter)) {
					this.neighbours.put(letter, new LinkedList<>());
					this.neighbours.get(letter).addAll(a.neighbours.get(letter));
					this.simplification(letter);
				}else if(b.neighbours.containsKey(letter)) {
					this.neighbours.put(letter, new LinkedList<>());
					this.neighbours.get(letter).addAll(b.neighbours.get(letter));
					this.simplification(letter);
				}
			}
		}
		this.id = a.getId()+"."+b.getId();
		this.removeDuplicates();
		//faire en sorte que les id ne soient pas pareil
		this.isInit = false;
		this.isExit = a.isExit||b.isExit;
	}
	
	public void simplification(String letter) {
		// on a un dictionnaire avec les lettres libellant toutes les transitions du nouvel état
		// Cependant on a des doublons on veut donc les supprimer
		
		LinkedHashSet<String> hSetNeighbours = new LinkedHashSet<String>(this.neighbours.get(letter));
		//on crée une nouvelle liste de type hset qui en supporte pas les doublons
		this.neighbours.get(letter).clear(); // on clear la liste de nos voisins
		this.neighbours.get(letter).addAll(hSetNeighbours); // on ajoute maintenant les éléments dans la liste principale
		Collections.sort(this.neighbours.get(letter)); // on ordonne la liste
		String newStateNeighbour ="";
		for(String number : this.neighbours.get(letter)) {
			if(newStateNeighbour != "" || number != "") {
				newStateNeighbour = newStateNeighbour+number+".";
			}
		}
		if(newStateNeighbour.length()>0) {
			newStateNeighbour = newStateNeighbour.substring(0, newStateNeighbour.length()-1);
		}
		
		this.neighbours.get(letter).clear(); // on clear à nouveau la liste de nos voisins
		this.neighbours.get(letter).add(newStateNeighbour);
	}
	
    public void removeDuplicates() {
        String result = "";
        for (int i = 0; i < this.getId().length(); i++) {
            if(!result.contains(String.valueOf(this.getId().charAt(i)))||this.getId().charAt(i)=='.') {
                result += String.valueOf(this.getId().charAt(i));
            }
        }
        this.setId(result);
    }
}
