package automatons;
import java.util.*;

public class State {
    private String id;
    private boolean isInit;
    private boolean isExit;
    private HashMap<String, LinkedList<String>> neighbours;
    private LinkedList<State> epsilon_transitions;

    public State(final String id, final boolean isInit, final boolean isExit, final HashMap<String, LinkedList<String>> neighbours) {
        this.id = id;
        this.isInit = isInit;
        this.isExit = isExit;
        this.neighbours = neighbours;
        this.epsilon_transitions = new LinkedList<State>();
    }

    public State(State other_state) {
        this.id = other_state.id;
        this.isInit = other_state.isInit;
        this.isExit = other_state.isExit;
        if(other_state.getNeighbours().containsKey("*")) {
            HashMap<String, LinkedList<String>> neighbours_epsilon_free = new HashMap<String, LinkedList<String>>(other_state.getNeighbours());
            neighbours_epsilon_free.remove("*");
            this.setNeighbours(neighbours_epsilon_free);
        }else {
            this.setNeighbours(other_state.getNeighbours());
        }
        this.epsilon_transitions = new LinkedList<State>(other_state.epsilon_transitions);
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

    public LinkedList<State> getEpsilon_transitions() {
        return epsilon_transitions;
    }

    public void setEpsilon_transitions(LinkedList<State> epsilon_transitions) {
        this.epsilon_transitions = epsilon_transitions;
    }

    public boolean several_transitions(String letter) {
        //permit to know if a state has several transition with the same letter
        if (neighbours.get(letter).size() > 1) {
            //if for a letter the size of the string linked list is superior to 1, the state has several transition
            return true;
        }
        return false;
    }

    public void print_closed_epsilon() {
        System.out.println("Closed epsilon of "+getId()+" : ");
        int i=0;
        while(i<getEpsilon_transitions().size()-1) {
            System.out.print(getEpsilon_transitions().get(i).getId()+".");
            i++;
        }
        System.out.print(getEpsilon_transitions().getLast().getId());
        System.out.println();
    }

    public void concat(State a, State b, List<String> aut_alph) {
        //permit to concatenate 2 states
        //if you have 0 [a:1->4] | [b:2] and 1[a:1->2]
        //you will have 0.1 [a:1->2->4] | [b:2]
        for(String letter : aut_alph) {
            //we browse all the letter a state can have
            if(!this.neighbours.containsKey(letter)) {
                // for security we check if the letter isn't already in the state in which we concatenate the two
                //3 cases possible the letter :
                //the letter is in both state -> we add all transitions of both states
                //the letter is only in the first state-> we only add the transitions of the first state
                //the letter is only in the second state-> we only add the transitions of the second state
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
        //concatenate the name of the states with a point in the middle like for 0 and 1 you have 0.1
        MyString myStringId = new MyString (getId());
        setId(myStringId.removeDuplicates());
        //if the the two strings has a common name it avoid to have it two times
        //for exemple if you concatenate 0.1 and 1.2.3 it avoid to have 0.1.1.2.3 but 0.1.2.3
        this.isInit = false;
        this.isExit = a.isExit||b.isExit;
    }

    public void simplification(String letter) {
        //function that permit :
        //- to go from a linked list of string in transition to one string with transition separate by a point
        //- remove the duplicates in the list of transitions and sort them
        //you will go from 0 : a->0->1->3->8 | b->2->1->3->->3
        //              to 0 : a->0->1->3->8 | b->1.2.3 if you call the function for b
        LinkedHashSet<String> hSetNeighbours = new LinkedHashSet<String>(this.neighbours.get(letter));
        //we create a list of Hashset type with the same content like the precedent
        //the particularity of Hashset it that it didn't support the duplicates but it function like a linked list
        //we clear the the precedent list and put the Hashset (wich is the same without duplicates)
        this.neighbours.get(letter).clear();
        this.neighbours.get(letter).addAll(hSetNeighbours);
        Collections.sort(this.neighbours.get(letter)); //we sort the list
        String newStateNeighbour ="";
        //we browse the list and add each element in one string separate by a "."
        for(String number : this.neighbours.get(letter)) {
            if(newStateNeighbour != "" || number != "") {
                newStateNeighbour = newStateNeighbour+number+".";
            }
        }
        MyString myStringNeighbour = new MyString (newStateNeighbour);
        newStateNeighbour = myStringNeighbour.removeDuplicates();

        this.neighbours.get(letter).clear(); // on clear à nouveau la liste de nos voisins
        this.neighbours.get(letter).add(newStateNeighbour);
    }
}