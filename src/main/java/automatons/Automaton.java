package automatons;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Automaton {
    private final int nbAlphabetSymbols;
    private int nbStates;
    private LinkedList<State> states;
    private int nbInitStates;
    private int nbExitStates;
    private int nbTransitions;
    private boolean sync;
    private final static List<String> alphabet = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
    private final List<String> autAlph;


    public Automaton(LinkedList<State> states, boolean sync, int s_alph) {
        super();
        this.nbStates = states.size();
        this.states = states;
        this.sync = sync;
        this.nbAlphabetSymbols = s_alph;
        this.autAlph = alphabet.subList(0, nbAlphabetSymbols);

        if(!sync) {
            this.autAlph.set(nbAlphabetSymbols-1, "*");
        }
    }

    public Automaton(final LinkedList<String> automatonCharacteristics) {
        // automatons characteristics are stored in String so we convert to Integer to initialize attributes
        nbAlphabetSymbols = Integer.parseInt(automatonCharacteristics.get(0));
        nbStates = Integer.parseInt(automatonCharacteristics.get(1));
        states = new LinkedList<>();

        for (int i = 0; i < nbStates; ++i) {
            State newState = new State(String.valueOf(i));
            states.add(newState);
        }

        /* the init and exit states are stored in format : nbStates state1 state2 ...
        so we split by whitespace to get only the ids
         */
        String[] initStates = automatonCharacteristics.get(2).split("\\s");
        nbInitStates = Integer.parseInt(initStates[0]);

        for (int i = 1; i < nbInitStates + 1; ++i) {
            String stateId = initStates[i];
            states.get(Integer.parseInt(stateId)).setIsInit(true);
        }

        String[] exitStates = automatonCharacteristics.get(3).split("\\s");
        nbExitStates = Integer.parseInt(exitStates[0]);

        for (int i = 1; i < nbExitStates + 1; ++i) {
            String stateId = exitStates[i];
            states.get(Integer.parseInt(stateId)).setIsExit(true);
        }

        nbTransitions = Integer.parseInt(automatonCharacteristics.get(4));
        sync = true;

        for (int i = 0; i < nbTransitions; ++i) {
            String initialState = String.valueOf(automatonCharacteristics.get(5+i).charAt(0));
            String symbol = String.valueOf(automatonCharacteristics.get(5+i).charAt(1));
            String arrivalState = String.valueOf(automatonCharacteristics.get(5+i).charAt(2));

            if (symbol.equals("*")) {
                sync = false;
            }

            states.get(Integer.parseInt(initialState)).addNeighbour(symbol, arrivalState);
        }

        this.autAlph = alphabet.subList(0, nbAlphabetSymbols);
        if(!sync) {
            this.autAlph.set(nbAlphabetSymbols-1, "*");
        }
    }

    public static LinkedList<String> readAutomatonOnFile(final String filename) {
        LinkedList<String> automatonInformations = new LinkedList<>();
        try {
            File automatonInformation = new File(filename);
            Scanner reader = new Scanner(automatonInformation);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                automatonInformations.add(data);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error : File Not Found");
            e.printStackTrace();
        }

        return automatonInformations;
    }

    public int getNbStates() {
        return nbStates;
    }

    public void setNbStates(int nbStates) {
        this.nbStates = nbStates;
    }

    public LinkedList<State> getStates() {
        LinkedList<State> newStates = new LinkedList<>();

        for (State state : states) {
            newStates.add(new State(state));
        }

        return newStates;
    }

    public void setStates(LinkedList<State> states) {
        this.states = states;
    }

    public boolean isDeterminist() {
        if (several_entries() || several_trans()) {
            //System.out.println("L'automate n'est pas deterministe");
            return false;
        }
        //System.out.println("L'automate est deterministe");
        return true;
    }

    public boolean several_entries() {
        //browse every states of an automaton and check if the number of entries is >1
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
        //give a linked list with
        LinkedList<State> entries_list = new LinkedList<State>();
        for (State state : states) {
            if (state.isInit()) {
                entries_list.add(new State(state));
            }
        }
        return entries_list;
    }

    public boolean several_trans() {
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

    public void simplification_aut() {
        for(State states : getStates()) {
            for(String letter : autAlph) {
                if(states.getNeighbours().containsKey(letter)) {
                    states.simplification(letter);
                }
            }
        }
    }


    public Automaton det_sync() {
        if(this.isDeterminist()) {
            return this;
        }else {
            simplification_aut();
            Automaton a = new Automaton(new LinkedList<State>(), true, this.nbAlphabetSymbols);
            if(this.several_entries()) {
                LinkedList<State> newStates = a.getStates();
                newStates.add(concat_list(getEntries()));
                newStates.get(0).setIsInit(true);
                a.setStates(newStates);
            }else {
                LinkedList<State> newStates = a.getStates();
                newStates.add(getEntries().get(0));
                a.setStates(newStates);
            }
            LinkedList<State> clone_list = new LinkedList<State>();
            boolean modif = false;
            do {
                modif = false;
                for(State state : a.getStates()) {
                    for(String letter : autAlph) {
                        if(state.getNeighbours().containsKey(letter)) {
                            State new_state = this.StringtoState(state.getNeighbours().get(letter).get(0));
                            //new_state.removeDuplicates();
                            clone_list.add(new_state);
                        }
                    }
                }
                boolean add = true;
                for(State state : clone_list) {
                    add = true;
                    for(State state2 : a.getStates()) {
                        if(state.getId().equals(state2.getId())) {
                            add = false;
                        }
                    }
                    if(add && state.getId()!= "") {
                        LinkedList<State> states = a.getStates();
                        states.add(state);
                        a.setStates(states);
                        modif = true;
                    }
                }
                clone_list.clear();
            }while(modif);
            return a;
        }
    }

    public State concat_list(LinkedList<State> list) {
        State state_concat = new State("", false, false, new HashMap<String, LinkedList<String>>());
        while(list.size()>1) {
            state_concat.concat(list.get(0), list.get(1), autAlph);
            list.remove(0);
            list.remove(0);
            list.addFirst(state_concat);
        }
        return state_concat;
    }


    public State StringtoState(String stringstates) {
        String[] stringstatesArray = stringstates.split("\\.");
        LinkedList<State> list = new LinkedList<State>();
        for(String index : stringstatesArray) {
            for(State states : this.getStates()) {
                if(index.equals(states.getId())) {
                    list.add(states);
                }
            }
        }
        State new_state;
        if(list.size()==1) {
            new_state = list.get(0);
        }else {
            new_state = concat_list(list);
        }
        return new_state;
    }

    public State findEpsilon_transitions(State state){ //we use this function to get the simplified writing of each epsilon closed state
        LinkedList<State> list = new LinkedList<State>(); // the final epsilon transitions list for the current state
        list.addFirst(state);
        if(state.getNeighbours().containsKey("*")) {
            Queue<String> q = new LinkedList<String>(); // we use a FIFO in order to progress in the Automaton and find all the states where we can go through epsilon transitions
            q.add(state.getId());
            q.addAll(state.getNeighbours().get("*"));
            while(q.size()>0) {
                State new_state = this.StringtoState(q.remove()); // we call StringtoState function to get the State that corresponds to each String we have in the Queue
                if(!list.contains(new_state)) {
                    list.add(new_state); // we add all states to the list of epsilon transitions ways from the current state
                    //list.add(new State(new_state));
                }
                if(new_state.getNeighbours().containsKey("*")) {
                    q.addAll(new_state.getNeighbours().get("*"));  // we add all the epsilon transitions of each state we have in the Automaton
                }
            }
        }
        Collections.sort(list, Comparator    						// For a better comprehension we need to sort the list. We use Comparator interface
                .comparingInt((State s) -> s.getId().length())		// First we compare the size of all the ID
                .thenComparing((State s) -> s.getId()));			// Then we compare the value with a classic "alphabetical" sorting

        int index=0;   			// We want to put the current state at the beginning of the list so we first search for an occurrence of the state in our list
        boolean test = false;
        for(int i = 0; i<list.size(); i++) {
            if(list.get(i).getId().equals(state.getId())) {
                //if(list.get(i).getId()==state.getId();
                index = i;
                test = true;
            }
        }
        if(test) {   // if we've found an occurrence then we remove the state
            list.remove(index);
        }
        list.addFirst(state);           // we add the state at first position in any case
        //list.addFirst(new State(state));
        State returning_state = new State(state);
        returning_state.setId(state.getId()+"'");
        returning_state.setEpsilon_transitions(list);  // we directly change the value of epsilon_transition's list in the given state
        //state.setId(state.getId()+"'");
        return returning_state;
    }

    public Automaton det_async(boolean print){ // THis function will use the simplified writing of each state to remove epsilon transitions in an automaton
        Automaton synchronized_a = new Automaton(new LinkedList<State>(), true, nbAlphabetSymbols);
        for(int i = 0; i<getStates().size(); i++) {   // For all states in the given Automaton, we find the non-epsilon transitions
            synchronized_a.getStates().add(findEpsilon_transitions(getStates().get(i)));
            for(String letter : autAlph) {
                if(synchronized_a.getStates().get(i).getNeighbours().containsKey(letter)) {
                    for(int j = 0; j< synchronized_a.getStates().get(i).getNeighbours().get(letter).size();j++) {
                        String number = synchronized_a.getStates().get(i).getNeighbours().get(letter).get(j);
                        number+="'";
                        synchronized_a.getStates().get(i).getNeighbours().get(letter).set(j,number);
                    }
                }
            }
        }
        //Now we have the complete Automaton with only non-epsilon transitions and the list of states accessible by epsilon transitions from each state in this Automaton
        for(State states : synchronized_a.getStates()) {
            for(State epsilon_states : states.getEpsilon_transitions()) {
                for(String key : synchronized_a.autAlph) {
                    states.getNeighbours().get(key).addAll(epsilon_states.getNeighbours().get(key));
                }
            }
        }
        for(State states : synchronized_a.getStates()) {
            for(String letter : synchronized_a.autAlph) {
                LinkedHashSet<String> hSetNeighbours = new LinkedHashSet<String>(states.getNeighbours().get(letter));
                states.getNeighbours().get(letter).clear();
                states.getNeighbours().get(letter).addAll(hSetNeighbours);
            }
        }
        if(print) {
            for(State states : synchronized_a.getStates()) {
                states.print_closed_epsilon();
            }
        }
        return synchronized_a.det_sync();
    }
}