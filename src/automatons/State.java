package automatons;

import java.util.LinkedList;
import java.util.Arrays;

public class State {
    private String id;
    private boolean isInit;
    private boolean isExit;
    private LinkedList<String[]> neighbours;
    
    
    public State(String id, boolean isInit, boolean isExit, LinkedList<String[]> neighbours) {
		super();
		this.id = id;
		this.isInit = isInit;
		this.isExit = isExit;
		this.neighbours = neighbours;
	}
    
	public LinkedList<String[]> getNeighbours() {
		return neighbours;
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

	public void setNeighbours(LinkedList<String[]> neighbours) {
		this.neighbours = neighbours;
	}
	
	public void concat_states(State a, State b) {
		LinkedList<String[]> new_neigh_list = new LinkedList<String[]>();  //création de la nouvelle liste de transitions depuis l'état donné
		int cpt = 0;
		for(int i = 0 ; i < (a.getNeighbours()).size() ; i++) {
			for(int j = 0 ; j < (b.getNeighbours()).size();j++) {
				if(a.getNeighbours().get(i)[0] == b.getNeighbours().get(i)[0]) {
					String new_neigh;
					if(a.getNeighbours().get(i)[1] == b.getNeighbours().get(i)[1]) {
						new_neigh = a.getNeighbours().get(i)[0]+a.getNeighbours().get(i)[1];
						
					}else {
						new_neigh = a.getNeighbours().get(i)[0]+a.getNeighbours().get(i)[1]+b.getNeighbours().get(i)[1];	
					}
					String[] new_neigh_array = new_neigh.split("");
					new_neigh_list.add(new_neigh_array);
				}
			}
		}
		this.id = a.id+b.id;
		this.isInit = a.isInit||b.isInit;
		this.isExit = a.isExit||b.isExit;
		this.neighbours = new_neigh_list;
	}
	
}
