package automatons;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Automaton {
    private final int nbAlphabetSymbols;
    private int nbStates;
    private LinkedList<State> states;
    private final int nbInitStates;
    private final int nbExitStates;
    private final int nbTransitions;
    private boolean sync;
    private final static List<String> alphabet = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
    private final List<String> autAlph;


    public Automaton(LinkedList<State> states, boolean sync, int nbAlphabetSymbols) {
        this.nbStates = states.size();
        this.states = states;
        this.nbInitStates = 0;
        this.nbExitStates = 0;
        this.nbTransitions = 0;
        this.sync = sync;
        this.nbAlphabetSymbols = nbAlphabetSymbols;
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
        return !severalEntries() && !severalTransitions();
    }

    public boolean severalEntries() {
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
        LinkedList<State> entries = new LinkedList<>();
        for (State state : states) {
            if (state.isInit()) {
                entries.add(new State(state));
            }
        }
        return entries;
    }

    public boolean severalTransitions() {
        for (State state : states) {
            HashMap<String, LinkedList<String>> neighbours = state.getNeighbours();
            for (String letter : neighbours.keySet()) {
                if (state.severalTransitions(letter)) {
                    return true;
                }
            }
        }
        return false;
    }


    public LinkedList<State> getSeveralTrans(){
        LinkedList<State> severalTransitionsList = new LinkedList<>();
        for (State state : states) {
            HashMap<String, LinkedList<String>> neighbours = state.getNeighbours();
            for (String letter : neighbours.keySet()) {
                if (neighbours.get(letter).size() > 1) {
                    severalTransitionsList.add(state);
                }
            }
        }
        return severalTransitionsList;
    }
    //fonction recuperant les entrées et fonction récupérant les états rendant l'automate non déterministe

    public void automatonSimplification() {
        for (State states : getStates()) {
            for (String letter : autAlph) {
                if (states.getNeighbours().containsKey(letter)) {
                    states.simplification(letter);
                }
            }
        }
    }


    public Automaton determinisationSync() {
        if (isDeterminist()) {
            return this;
        } else {
            automatonSimplification();
            Automaton deterministicAutomaton = new Automaton(new LinkedList<>(), true, nbAlphabetSymbols);

            if (severalEntries()) {
                LinkedList<State> newStates = deterministicAutomaton.getStates();
                newStates.add(concatList(getEntries()));
                newStates.get(0).setIsInit(true);
                deterministicAutomaton.setStates(newStates);
            } else {
                LinkedList<State> newStates = deterministicAutomaton.getStates();
                newStates.add(getEntries().get(0));
                deterministicAutomaton.setStates(newStates);
            }

            LinkedList<State> cloneList = new LinkedList<>();
            boolean modification;
            do {
                modification = false;
                for (State state : deterministicAutomaton.getStates()) {
                    for (String letter : autAlph) {
                        if (state.getNeighbours().containsKey(letter)) {
                            State newState = StringtoState(state.getNeighbours().get(letter).get(0));
                            cloneList.add(newState);
                        }
                    }
                }

                boolean add;
                for (State state : cloneList) {
                    add = true;
                    for (State state2 : deterministicAutomaton.getStates()) {
                        if (state.getId().equals(state2.getId())) {
                            add = false;
                            break;
                        }
                    }
                    if (add && !state.getId().equals("")) {
                        LinkedList<State> states = deterministicAutomaton.getStates();
                        states.add(state);
                        deterministicAutomaton.setStates(states);
                        modification = true;
                    }
                }
                cloneList.clear();
            } while(modification);

            return deterministicAutomaton;
        }
    }

    public State concatList(LinkedList<State> list) {
        State stateConcat = new State("", false, false, new HashMap<>());

        while (list.size() > 1) {
            stateConcat.concat(list.get(0), list.get(1), autAlph);
            list.remove(0);
            list.remove(0);
            list.addFirst(stateConcat);
        }

        return stateConcat;
    }


    public State StringtoState(String stringStates) {
        String[] stringStatesArray = stringStates.split("\\.");
        LinkedList<State> list = new LinkedList<>();
        for (String index : stringStatesArray) {
            for (State states : getStates()) {
                if (index.equals(states.getId())) {
                    list.add(states);
                }
            }
        }

        State newState;
        if(list.size() == 1) {
            newState = list.get(0);
        }else {
            newState = concatList(list);
        }

        return newState;
    }

    public State findEpsilon_transitions(State state){ //we use this function to get the simplified writing of each epsilon closed state
        LinkedList<State> list = new LinkedList<>(); // the final epsilon transitions list for the current state
        list.addFirst(state);

        if (state.getNeighbours().containsKey("*")) {
            Queue<String> q = new LinkedList<>(); // we use a FIFO in order to progress in the Automaton and find all the states where we can go through epsilon transitions
            q.add(state.getId());
            q.addAll(state.getNeighbours().get("*"));

            while (q.size() > 0) {
                State newState = StringtoState(q.remove()); // we call StringtoState function to get the State that corresponds to each String we have in the Queue
                if (!list.contains(newState)) {
                    list.add(newState); // we add all states to the list of epsilon transitions ways from the current state
                }
                if (newState.getNeighbours().containsKey("*")) {
                    q.addAll(newState.getNeighbours().get("*"));  // we add all the epsilon transitions of each state we have in the Automaton
                }
            }
        }
        list.sort(Comparator                            // For a better comprehension we need to sort the list. We use Comparator interface
                .comparingInt((State s) -> s.getId().length())        // First we compare the size of all the ID
                .thenComparing(State::getId));			// Then we compare the value with a classic "alphabetical" sorting

        int index=0;   			// We want to put the current state at the beginning of the list so we first search for an occurrence of the state in our list
        boolean test = false;
        for (int i = 0; i<list.size(); i++) {
            if (list.get(i).getId().equals(state.getId())) {
                index = i;
                test = true;
            }
        }
        if (test) {   // if we've found an occurrence then we remove the state
            list.remove(index);
        }
        list.addFirst(state);           // we add the state at first position in any case
        State returningState = new State(state);
        returningState.setId(state.getId()+"'");
        returningState.setEpsilonTransitions(list);  // we directly change the value of epsilon_transition's list in the given state
        return returningState;
    }

    public Automaton determinisationAsync(boolean print){ // THis function will use the simplified writing of each state to remove epsilon transitions in an automaton
        Automaton synchronizedAutomaton = new Automaton(new LinkedList<>(), true, nbAlphabetSymbols);

        for (int i = 0; i < getStates().size(); i++) {   // For all states in the given Automaton, we find the non-epsilon transitions
            synchronizedAutomaton.getStates().add(findEpsilon_transitions(getStates().get(i)));

            for (String letter : autAlph) {
                if (synchronizedAutomaton.getStates().get(i).getNeighbours().containsKey(letter)) {
                    for (int j = 0; j < synchronizedAutomaton.getStates().get(i).getNeighbours().get(letter).size(); j++) {
                        String number = synchronizedAutomaton.getStates().get(i).getNeighbours().get(letter).get(j);
                        number += "'";
                        synchronizedAutomaton.getStates().get(i).getNeighbours().get(letter).set(j,number);
                    }
                }
            }
        }

        //Now we have the complete Automaton with only non-epsilon transitions and the list of states accessible by epsilon transitions from each state in this Automaton
        for (State states : synchronizedAutomaton.getStates()) {
            for (State epsilon_states : states.getEpsilonTransitions()) {
                for (String key : synchronizedAutomaton.autAlph) {
                    states.getNeighbours().get(key).addAll(epsilon_states.getNeighbours().get(key));
                }
            }
        }
        for (State states : synchronizedAutomaton.getStates()) {
            for (String letter : synchronizedAutomaton.autAlph) {
                LinkedHashSet<String> hSetNeighbours = new LinkedHashSet<>(states.getNeighbours().get(letter));
                states.getNeighbours().get(letter).clear();
                states.getNeighbours().get(letter).addAll(hSetNeighbours);
            }
        }
        if (print) {
            for (State states : synchronizedAutomaton.getStates()) {
                states.printClosedEpsilon();
            }
        }

        return synchronizedAutomaton.determinisationSync();
    }
}