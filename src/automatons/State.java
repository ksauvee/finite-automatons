package automatons;

import java.util.LinkedList;
import java.util.Arrays;
public class State {
    private String id;
    private boolean isInit;
    private boolean isExit;
    private LinkedList<Neighbour> neigh_list; //ListA = [["a","0"] , ["b", "1"] String[]  neigh = ["a","0"]  String neigh = "a0"
    											//ListB = [["a","2"]]  ListAB = [["a", String "02"]] 
    
    
	public State(String id, boolean isInit, boolean isExit, LinkedList<Neighbour> neigh_list) {
		super();
		this.id = id;
		this.isInit = isInit;
		this.isExit = isExit;
		this.neigh_list = neigh_list;
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

	public LinkedList<Neighbour> getNeigh_list() {
		return neigh_list;
	}



	public void setNeigh_list(LinkedList<Neighbour> neigh_list) {
		this.neigh_list = neigh_list;
	}



	public void concat_states(State a, State b) {
		LinkedList<Neighbour> new_neigh_list = new LinkedList<Neighbour>();  //création de la nouvelle liste de transitions depuis l'état donné
		for(int i = 0 ; i < a.getNeigh_list().size() ; i++) {  //Pour chaque voisin dans A
			for(int j = 0 ; j < b.getNeigh_list().size();j++) {  //Pour chaque voisin dans B
				if(a.getNeigh_list().get(i).getLetter() == b.getNeigh_list().get(j).getLetter()) { // Si la lettre est la même pour les deux états
					Neighbour new_neigh = new Neighbour("", ""); // on crée le nouveau voisin à ajouter
					new_neigh.concat(a.getNeigh_list().get(i), b.getNeigh_list().get(j));
					
					
					
					
					
					
					/*if(a.getNeigh_list().get(i).getNumber() == b.getNeigh_list().get(j).getNumber()) {  // si le nombre est le même pour les deux états
						new_neigh.setLetter(a.getNeigh_list().get(i).getLetter());  // Le nouveau voisin aura la lettre et les chiffres de A (ou B c'est pareil)
						new_neigh.setNumber(""+a.getNeigh_list().get(i).getNumber()+b.getNeigh_list().get(j).getNumber());
						if(!new_neigh_list.contains(a.getNeigh_list().get(i))){  // Si le nouveau voisin créé n'est pas dans la liste de voisins on l'ajoute
							new_neigh_list.add(a.getNeigh_list().get(i));
							String aaa = "coucou";
							String bbb = ""+aaa.charAt(0)+(aaa.charAt(1));
						}
					}else { // Si les lettres sont les mêmes mais pas les chiffres
						
					}*/
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
	}
	
}
