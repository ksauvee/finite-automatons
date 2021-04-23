package automatons;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Automaton {
    private final int nbAlphabetSymbols;
    private int nbStates;
    private int nbInitStates;
    private int nbExitStates;
    private int nbTransitions;
    private LinkedList<State> states;
    private boolean sync;
    private final static List<String> alphabet = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
    private final List<String> autAlph;

    public Automaton(LinkedList<State> states, boolean sync, int nbAlphabetSymbols) {
        this.nbStates = states.size();
        this.states = states;
        this.sync = sync;
        this.nbAlphabetSymbols = nbAlphabetSymbols;
        this.autAlph = alphabet.subList(0, nbAlphabetSymbols);

        if (!sync) {
            this.autAlph.add("*");
        }
    }

    public Automaton(final LinkedList<String> automatonCharacteristics) {
        // automatons characteristics are stored in String so we convert to Integer to initialize attributes
        nbAlphabetSymbols = Integer.parseInt(automatonCharacteristics.get(0));
        this.sync = false;
        this.autAlph = alphabet.subList(0, nbAlphabetSymbols);
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

        for (int i = 0; i < nbTransitions; ++i) {
            String initialState = String.valueOf(automatonCharacteristics.get(5+i).charAt(0));
            String symbol = String.valueOf(automatonCharacteristics.get(5+i).charAt(1));
            String arrivalState = String.valueOf(automatonCharacteristics.get(5+i).charAt(2));

            if (symbol.equals("*")) {
                this.sync = true;
            }

            states.get(Integer.parseInt(initialState)).addNeighbour(symbol, arrivalState);
        }

        if(!sync) {
            this.autAlph.add("*");
        }
    }

    public Automaton(Automaton automaton) {
        this.nbAlphabetSymbols = automaton.getNbAlphabetSymbols();
        this.nbStates = automaton.getNbStates();
        this.nbInitStates = automaton.getNbInitStates();
        this.nbExitStates = automaton.getNbExitStates();
        this.nbTransitions = automaton.getNbTransitions();
        this.states = automaton.getStates();
        this.sync = automaton.getSync();
        this.autAlph = alphabet.subList(0, nbAlphabetSymbols);

        if (!sync) {
            this.autAlph.add("*");
        }
    }

    public int getNbAlphabetSymbols() {
        return nbAlphabetSymbols;
    }

    public int getNbStates() {
        return nbStates;
    }

    public void setNbStates(final int nbStates) {
        if (nbStates >= 0) {
            this.nbStates = nbStates;
        }
    }

    public int getNbInitStates() {
        return nbInitStates;
    }

    public void setNbTransitions(final int nbTransitions) {
        if (nbTransitions >= 0) {
            this.nbTransitions = nbTransitions;
        }
    }

    public void setNbInitStates(final int nbInitStates) {
        if (nbInitStates >= 0) {
            this.nbInitStates = nbInitStates;
        }
    }

    public int getNbExitStates() {
        return nbExitStates;
    }

    public void setNbExitStates(final int nbExitStates) {
        if (nbExitStates >= 0) {
            this.nbExitStates = nbExitStates;
        }
    }

    public int getNbTransitions() {
        return nbTransitions;
    }

    public LinkedList<State> getStates() {
        LinkedList<State> newStates = new LinkedList<>();

        for (State state : states) {
            newStates.add(new State(state));
        }

        return newStates;
    }

    public void setStates(final LinkedList<State> states) {
        this.states = new LinkedList<>(states);
    }

    public boolean getSync() {
        return sync;
    }

    public void setSync(final boolean sync) {
        this.sync = sync;
    }

    public void addState(State state) {
        states.add(state);
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

    public Automaton automatonComplementarization() {
        Automaton complementaryAutomaton = new Automaton(this);
        LinkedList<State> states = complementaryAutomaton.getStates();

        for (State state : states) {
            state.setIsExit(!state.getIsExit());
        }

        complementaryAutomaton.setStates(states);

        return complementaryAutomaton;
    }

    public int findNbTransitionsAutomaton() {
        int nbTransitions = 0;

        for (State state : getStates()) {
            // automaton's nbTransitions is the sum of the transitions of all states with all symbols
            HashMap<String, LinkedList<String>> neighbours = state.getNeighbours();

            for (String symbols : neighbours.keySet()) {
                nbTransitions += neighbours.get(symbols).size();
            }
        }

        return nbTransitions;
    }

    public boolean isStandard() {
        for (State currentState : states) {
            if (currentState.getIsInit()) {
                for (String symbol : currentState.getNeighbours().keySet()) {
                    // we check is there a transition to currentState
                    for (String arrivalState : currentState.getNeighbours().get(symbol)) {
                        if (arrivalState.equals(currentState.getId())) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public Automaton automatonStandardization() {
        if (isStandard()) {
            System.out.println("Automaton is already standard");
            return null;
        }
        Automaton standardizedAutomaton = new Automaton(this);

        // we create the new entry
        State newEntry = new State("i", true);
        standardizedAutomaton.setNbStates(standardizedAutomaton.getNbStates()+1);


        LinkedList<State> states = standardizedAutomaton.getStates();
        for (State currentState : states) {
            // we add the transitions of the old entries to the new entry
            if (currentState.getIsInit()) {
                currentState.setIsInit(!currentState.getIsInit());

                // we get the transitions of the old entry
                HashMap<String, LinkedList<String>> currentStateNeighbours = currentState.getNeighbours();

                // we merge the transitions between the old entries and the new entry
                for (String symbol : currentStateNeighbours.keySet()) {
                    for (String arrivalState : currentStateNeighbours.get(symbol)) {
                        newEntry.addNeighbour(symbol, arrivalState);
                    }
                }

                // if one entry is also an exit then the new entry becomes an exit
                if (currentState.getIsExit()) {
                    newEntry.setIsExit(true);
                    standardizedAutomaton.setNbExitStates(standardizedAutomaton.getNbExitStates()+1);
                }
            }
        }

        // modification the standardized automaton characteristics
        standardizedAutomaton.setNbInitStates(1);
        standardizedAutomaton.setStates(states);
        standardizedAutomaton.addState(newEntry);
        standardizedAutomaton.setNbTransitions(standardizedAutomaton.findNbTransitionsAutomaton());

        return standardizedAutomaton;
    }

    public boolean isDeterminist() {
        return !severalEntries() && !severalTrans();
    }

    public boolean severalEntries() {
        //browse every states of an automaton and check if the number of entries is > 1
        int nbEntries = 0;
        for (State state : states) {
            if (state.getIsInit()) {
                ++nbEntries;
            }
            if (nbEntries > 1) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<State> getEntries(){
        LinkedList<State> entries = new LinkedList<>();
        for (State state : states) {
            if (state.getIsInit()) {
                entries.add(new State(state));
            }
        }
        return entries;
    }

    public boolean severalTrans() {
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
        LinkedList<State> severalTransList = new LinkedList<>();
        for (State state : states) {
            HashMap<String, LinkedList<String>> neighbours = state.getNeighbours();
            for (String letter : neighbours.keySet()) {
                if (neighbours.get(letter).size() > 1) {
                    severalTransList.add(state);
                }
            }
        }
        return severalTransList;
    }

    public void simplificationAut() {
        for (State states : getStates()) {
            for (String letter : autAlph) {
                if (states.getNeighbours().containsKey(letter)) {
                    states.simplification(letter);
                }
            }
        }
    }

    public Automaton detSync() {
        if (this.isDeterminist()) {
            return this;
        } else {
            simplificationAut();
            Automaton a = new Automaton(new LinkedList<>(), true, nbAlphabetSymbols);
            if (this.severalEntries()) {
                a.getStates().add(concatList(getEntries()));
                a.getStates().get(0).setIsInit(true);
            } else {
                a.getStates().add(getEntries().get(0));
            }
            LinkedList<State> cloneList = new LinkedList<>();
            boolean modif;
            do {
                modif = false;
                for (State state : a.getStates()) {
                    for (String letter : autAlph) {
                        if (state.getNeighbours().containsKey(letter)) {
                            State new_state = this.StringtoState(state.getNeighbours().get(letter).get(0));
                            cloneList.add(new_state);
                        }
                    }
                }

                boolean add;
                for (State state : cloneList) {
                    add = true;
                    for (State state2 : a.getStates()) {
                        if (state.getId().equals(state2.getId())) {
                            add = false;
                            break;
                        }
                    }
                    if (add && !state.getId().equals("")) {
                        a.getStates().add(state);
                        modif = true;
                    }
                }
                cloneList.clear();
            } while (modif);
            return a;
        }
    }

    public State concatList(LinkedList<State> list) {
        State stateConcat = new State("", false, false, new HashMap<>());

        while (list.size()>1) {
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
            for (State states : states) {
                if (index.equals(states.getId())) {
                    list.add(new State(states));
                }
            }
        }

        State newState;
        if (list.size()==1) {
            newState = list.get(0);
        } else {
            newState = concatList(list);
        }
        return newState;
    }

    public State findEpsilonTransitions(State state){ //we use this function to get the simplified writing of each epsilon closed state
        LinkedList<State> list = new LinkedList<>(); // the final epsilon transitions list for the current state
        list.addFirst(state);

        if (state.getNeighbours().containsKey("*")) {
            Queue<String> q = new LinkedList<>(); // we use a FIFO in order to progress in the Automaton and find all the states where we can go through epsilon transitions
            q.add(state.getId());
            q.addAll(state.getNeighbours().get("*"));

            while (q.size() > 0) {
                State new_state = StringtoState(q.remove()); // we call StringtoState function to get the State that corresponds to each String we have in the Queue
                if (!list.contains(new_state)) {
                    list.add(new_state); // we add all states to the list of epsilon transitions ways from the current state
                }
                if (new_state.getNeighbours().containsKey("*")) {
                    q.addAll(new_state.getNeighbours().get("*"));  // we add all the epsilon transitions of each state we have in the Automaton
                }
            }
        }
        list.sort(Comparator                            // For a better comprehension we need to sort the list. We use Comparator interface
                .comparingInt((State s) -> s.getId().length())        // First we compare the size of all the ID
                .thenComparing(State::getId));			// Then we compare the value with a classic "alphabetical" sorting

        int index=0;   			// We want to put the current state at the beginning of the list so we first search for an occurrence of the state in our list
        boolean test = false;
        for(int i = 0; i<list.size(); i++) {
            if (list.get(i).getId().equals(state.getId())) {
                index = i;
                test = true;
            }
        }

        if (test) {   // if we've found an occurrence then we remove the state
            list.remove(index);
        }
        list.addFirst(state); // we add the state at first position in any case
        State returningState = new State(state);
        returningState.setId(state.getId()+"'");
        returningState.setEpsilon_transitions(list);  // we directly change the value of epsilon_transition's list in the given state
        return returningState;
    }

    public Automaton detAsync(boolean print){ // THis function will use the simplified writing of each state to remove epsilon transitions in an automaton
        Automaton synchronizedA = new Automaton(new LinkedList<>(), true, nbAlphabetSymbols);

        for(int i = 0; i < getStates().size(); i++) {   // For all states in the given Automaton, we find the non-epsilon transitions
            synchronizedA.getStates().add(findEpsilonTransitions(getStates().get(i)));
            for (String letter : autAlph) {
                if (synchronizedA.getStates().get(i).getNeighbours().containsKey(letter)) {
                    for (int j = 0; j < synchronizedA.getStates().get(i).getNeighbours().get(letter).size(); j++) {
                        String number = synchronizedA.getStates().get(i).getNeighbours().get(letter).get(j);
                        number += "'";
                        synchronizedA.getStates().get(i).getNeighbours().get(letter).set(j,number);
                    }
                }
            }
        }
        //Now we have the complete Automaton with only non-epsilon transitions and the list of states accessible by epsilon transitions from each state in this Automaton
        for (State states : synchronizedA.getStates()) {
            for (State epsilon_states : states.getEpsilon_transitions()) {
                for (String key : synchronizedA.autAlph) {
                    states.getNeighbours().get(key).addAll(epsilon_states.getNeighbours().get(key));
                }
            }
        }
        for (State states : synchronizedA.getStates()) {
            for (String letter : synchronizedA.autAlph) {
                LinkedHashSet<String> hSetNeighbours = new LinkedHashSet<>(states.getNeighbours().get(letter));
                states.getNeighbours().get(letter).clear();
                states.getNeighbours().get(letter).addAll(hSetNeighbours);
            }
        }
        if (print) {
            for(State states : synchronizedA.getStates()) {
                states.print_closed_epsilon();
            }
        }
        return synchronizedA.detSync();
    }
}
