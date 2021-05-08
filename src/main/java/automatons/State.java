package automatons;
import java.util.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class State {
    private String id;
    private boolean isInit;
    private boolean isExit;
    private HashMap<String, LinkedList<String>> neighbours;
    private LinkedList<State> epsilonTransitions;

    public State(String id) {
        this.id = id;
        this.isInit = false;
        this.isExit = false;
        neighbours = new HashMap<>();
        epsilonTransitions = new LinkedList<>();
    }

    public State(final String id, final boolean isInit, final boolean isExit, final HashMap<String, LinkedList<String>> neighbours) {
        this.id = id;
        this.isInit = isInit;
        this.isExit = isExit;
        this.neighbours = neighbours;
        this.epsilonTransitions = new LinkedList<>();
    }

    public State(State other_state) {
        this.id = other_state.id;
        this.isInit = other_state.isInit;
        this.isExit = other_state.isExit;

        if (other_state.getNeighbours().containsKey("*")) {
            HashMap<String, LinkedList<String>> neighbours_epsilon_free = new HashMap<>(other_state.getNeighbours());
            neighbours_epsilon_free.remove("*");
            setNeighbours(neighbours_epsilon_free);
        } else {
            setNeighbours(other_state.getNeighbours());
        }

        this.epsilonTransitions = new LinkedList<>(other_state.epsilonTransitions);
    }

    public State(String i, boolean isInit) {
        this.id = i;
        this.isInit = isInit;
        this.isExit = false;
        this.neighbours = new HashMap<>();
        this.epsilonTransitions = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getIsInit() {
        return isInit;
    }

    public void setIsInit(boolean isInit) {
        this.isInit = isInit;
    }

    public boolean getIsExit() {
        return isExit;
    }

    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    public HashMap<String, LinkedList<String>> getNeighbours() {
        return new HashMap<>(neighbours);
    }

    public void setNeighbours(HashMap<String, LinkedList<String>> neighbours) {
        this.neighbours = neighbours;
    }

    public void addNeighbour(final String symbol, final String arrivalState) {
        LinkedList<String> letterTransitions = neighbours.get(symbol);

        if (letterTransitions == null) {
            LinkedList<String> newTransition = new LinkedList<>();
            newTransition.add(arrivalState);
            neighbours.put(symbol, newTransition);
        } else if (!letterTransitions.contains(arrivalState)) {
            // we check the transition doesn't exist in order to avoid duplicates
            letterTransitions.add(arrivalState);
        }
    }

    public LinkedList<State> getEpsilonTransitions() {
        return epsilonTransitions;
    }

    public void setEpsilonTransitions(LinkedList<State> epsilonTransitions) {
        this.epsilonTransitions = epsilonTransitions;
    }

    public boolean severalTransitions(String letter) {
        //permit to know if a state has several transition with the same letter
        //if for a letter the size of the string linked list is superior to 1, the state has several transition
        return neighbours.get(letter).size() > 1;
    }

    public void printClosedEpsilon() {
        System.out.println("Closed epsilon of "+getId()+" : ");
        int i = 0;

        while (i < getEpsilonTransitions().size()-1) {
            System.out.print(getEpsilonTransitions().get(i).getId()+".");
            i++;
        }

        System.out.print(getEpsilonTransitions().getLast().getId());
        System.out.println();
    }

    public void concat(State a, State b, List<String> autAlph) {
        //permit to concatenate 2 states
        //if you have 0 [a:1->4] | [b:2] and 1[a:1->2]
        //you will have 0.1 [a:1->2->4] | [b:2]
        for (String letter : autAlph) {
            //we browse all the letter a state can have
            if (!neighbours.containsKey(letter)) {
                // for security we check if the letter isn't already in the state in which we concatenate the two
                //3 cases possible the letter :
                //the letter is in both state -> we add all transitions of both states
                //the letter is only in the first state-> we only add the transitions of the first state
                //the letter is only in the second state-> we only add the transitions of the second state
                if (a.neighbours.containsKey(letter) && b.neighbours.containsKey(letter)){
                    neighbours.put(letter, new LinkedList<>());
                    neighbours.get(letter).addAll(a.neighbours.get(letter));
                    neighbours.get(letter).addAll(b.neighbours.get(letter));
                    simplification(letter);
                }else if (a.neighbours.containsKey(letter)) {
                    neighbours.put(letter, new LinkedList<>());
                    neighbours.get(letter).addAll(a.neighbours.get(letter));
                    simplification(letter);
                } else if (b.neighbours.containsKey(letter)) {
                    neighbours.put(letter, new LinkedList<>());
                    neighbours.get(letter).addAll(b.neighbours.get(letter));
                    simplification(letter);
                }
            }
        }

        id = a.getId()+"."+b.getId();
        //concatenate the name of the states with a point in the middle like for 0 and 1 you have 0.1
        MyString myStringId = new MyString(getId());
        setId(myStringId.removeDuplicates());
        //if the the two strings has a common name it avoid to have it two times
        //for exemple if you concatenate 0.1 and 1.2.3 it avoid to have 0.1.1.2.3 but 0.1.2.3
        isInit = false;
        this.isExit = a.isExit||b.isExit;
    }

    public void simplification(String letter) {
        //function that permit :
        //- to go from a linked list of string in transition to one string with transition separate by a point
        //- remove the duplicates in the list of transitions and sort them
        //you will go from 0 : a->0->1->3->8 | b->2->1->3->->3
        //              to 0 : a->0->1->3->8 | b->1.2.3 if you call the function for b
        LinkedHashSet<String> hSetNeighbours = new LinkedHashSet<>(neighbours.get(letter));
        //we create a list of Hashset type with the same content like the precedent
        //the particularity of Hashset it that it didn't support the duplicates but it function like a linked list
        //we clear the the precedent list and put the Hashset (wich is the same without duplicates)
        neighbours.get(letter).clear();
        neighbours.get(letter).addAll(hSetNeighbours);
        Collections.sort(neighbours.get(letter)); //we sort the list
        StringBuilder newStateNeighbour = new StringBuilder();
        //we browse the list and add each element in one string separate by a "."
        for (String number : neighbours.get(letter)) {
            if (!newStateNeighbour.toString().equals("") || !number.equals("")) {
                newStateNeighbour.append(number).append(".");
            }
        }
        MyString myStringNeighbour = new MyString (newStateNeighbour.toString());
        newStateNeighbour = new StringBuilder(myStringNeighbour.removeDuplicates());

        neighbours.get(letter).clear(); // on clear à nouveau la liste de nos voisins
        neighbours.get(letter).add(newStateNeighbour.toString());
    }

    public boolean isIncomplete(int nbAlphabetSymbols) {
        return neighbours.size() != nbAlphabetSymbols;
    }

    public int completion(List<String> alphabet) {
        int nbSupTransitions = 0;
        for(String letter : alphabet) {
            if(!neighbours.containsKey(letter)){
                addNeighbour(letter, "P");
                nbSupTransitions++;
            }
        }
        return nbSupTransitions;
    }
}