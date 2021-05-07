package automatons;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
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
            int j = 0;
            StringBuilder initialState = new StringBuilder();
            while (automatonCharacteristics.get(5+i).charAt(j) < 97 || automatonCharacteristics.get(5+i).charAt(j) > 122) {
                initialState.append(automatonCharacteristics.get(5 + i).charAt(j));
                j++;
            }
            String symbol = String.valueOf(automatonCharacteristics.get(5+i).charAt(j));
            j++;

            StringBuilder arrivalState = new StringBuilder();
            while (j != automatonCharacteristics.get(5+i).length() && (automatonCharacteristics.get(5+i).charAt(j) < 97 || automatonCharacteristics.get(5+i).charAt(j) > 122)) {
                arrivalState.append(automatonCharacteristics.get(5 + i).charAt(j));
                j++;
            }

            states.get(Integer.parseInt(initialState.toString())).addNeighbour(symbol, arrivalState.toString());
            if (symbol.equals("*")) {
                sync = false;
            }
        }

        this.autAlph = alphabet.subList(0, nbAlphabetSymbols);
        if(!sync) {
            this.autAlph.set(nbAlphabetSymbols-1, "*");
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
    }

    public Automaton(final int nbAlphabetSymbols) {
        this.nbAlphabetSymbols = nbAlphabetSymbols;
        states = new LinkedList<>();
        this.autAlph = alphabet.subList(0, nbAlphabetSymbols);
    }

    public int getNbAlphabetSymbols() {
        return nbAlphabetSymbols;
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

    public Boolean getSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
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
        return states;
    }

    public LinkedList<State> getStatesImprove() {
        LinkedList<State> newStates = new LinkedList<>();

        for (State state : states) {
            newStates.add(new State(state));
        }

        return newStates;
    }

    public void setStates(final LinkedList<State> states) {
        this.states = new LinkedList<>(states);
    }

    public void addState(State state) {
        states.add(state);
    }

    public boolean isDeterminist() {
        return !severalEntries() && !severalTransitions();
    }

    public boolean severalEntries() {
        //browse every states of an automaton and check if the number of entries is >1
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
        //give a linked list with
        LinkedList<State> entries = new LinkedList<>();
        for (State state : states) {
            if (state.getIsInit()) {
                entries.add(new State(state));
            }
        }
        return entries;
    }

    public LinkedList<String> getEntries2() {
        LinkedList<String> entries = new LinkedList<>();
        for (State currentState : states) {
            entries.add(currentState.getId());
        }

        return entries;
    }

    public Automaton complementarization() {
        Automaton complementaryAutomaton = new Automaton(this);
        LinkedList<State> states = complementaryAutomaton.getStates();
        
        for (State state : states) {
            state.setIsExit(!state.getIsExit());
        }

        complementaryAutomaton.setStates(states);
        complementaryAutomaton.setNbExitStates(complementaryAutomaton.getNbStates() - complementaryAutomaton.getNbExitStates());

        return complementaryAutomaton;
    }
    
    public LinkedList<String> getExits(){
        //give a linked list with
        LinkedList<String> exits = new LinkedList<>();
        for (State state : states) {
            if (state.getIsExit()) {
                exits.add(state.getId());
            }
        }
        return exits;
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

    public int findNbTransitionsAutomaton() {
        int nbTransitions = 0;

        for (State state : getStatesImprove()) {
            // automaton's nbTransitions is the sum of the transitions of all states with all symbols
            HashMap<String, LinkedList<String>> neighbours = state.getNeighbours();

            for (String symbols : neighbours.keySet()) {
                nbTransitions += neighbours.get(symbols).size();
            }
        }

        return nbTransitions;
    }


    public Automaton determinisationSync() {
        if (isDeterminist()) {
            return this;
        } else {
            automatonSimplification();
            Automaton deterministicAutomaton = new Automaton(new LinkedList<>(), true, nbAlphabetSymbols);

            if (severalEntries()) {
                LinkedList<State> newStates = deterministicAutomaton.getStatesImprove();
                newStates.add(concatList(getEntries()));
                newStates.get(0).setIsInit(true);
                deterministicAutomaton.setStates(newStates);
            } else {
                LinkedList<State> newStates = deterministicAutomaton.getStatesImprove();
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
                    state.setIsInit(false);
                    if (add && !state.getId().equals("")) {
                        LinkedList<State> states = deterministicAutomaton.getStatesImprove();
                        states.add(state);
                        deterministicAutomaton.setStates(states);
                        modification = true;
                    }
                }
                cloneList.clear();
            } while(modification);

            deterministicAutomaton.setNbStates(deterministicAutomaton.getStatesImprove().size());
            deterministicAutomaton.setNbInitStates(deterministicAutomaton.getEntries().size());
            deterministicAutomaton.setNbExitStates(deterministicAutomaton.getExits().size());
            deterministicAutomaton.setNbTransitions(deterministicAutomaton.findNbTransitionsAutomaton());
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

        // refresh neighbours in order to avoid bug in memory
        for (State state : synchronizedAutomaton.getStates()) {
            for (String letter : state.getNeighbours().keySet()) {
                LinkedList<String> newArrivalStates = new LinkedList<>(state.getNeighbours().get(letter));
                HashMap<String, LinkedList<String>> newNeighbours = state.getNeighbours();
                newNeighbours.replace(letter, state.getNeighbours().get(letter), newArrivalStates);
                state.setNeighbours(newNeighbours);
            }
        }

        return synchronizedAutomaton.determinisationSync();
    }

    public boolean isStandard() {
        LinkedList<String> entries = getEntries2();

        if (entries.size() > 1) {
            return false;
        }

        for (State currentState : states) {
            for (String symbol : currentState.getNeighbours().keySet()) {
                // we check is there a transition to currentState
                for (String arrivalState : currentState.getNeighbours().get(symbol)) {
                    for (String entry : entries) {
                        if (arrivalState.equals(entry)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public Automaton standardization() {
        if (isStandard()) {
            System.out.println("Automaton is already standard");
            return null;
        }
        Automaton standardizedAutomaton = new Automaton(this);

        // we create the new entry
        State newEntry = new State("i", true);
        standardizedAutomaton.setNbStates(standardizedAutomaton.getNbStates()+1);


        LinkedList<State> states = standardizedAutomaton.getStatesImprove();
        for (State currentState : states) {
            // we add the transitions of the old entries to the new entry
            if (currentState.getIsInit()) {
                currentState.setIsInit(false);

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

    private boolean isComplete() {
        for(State state : states) {
            if(state.isIncomplete(nbAlphabetSymbols)) {
                return false;
            }
        }
        return true;
    }

    public Automaton completion() {
        if (isComplete()) {
            System.out.println("Automaton already complete");
            return null;
        }
        int nbSupTransitions = 0;

        Automaton completeAutomaton = new Automaton(this);
        LinkedList<State> newStates = completeAutomaton.getStates();

        State garbage = new State("P");
        newStates.add(garbage);

        for(State state : newStates) {
            if(state.isIncomplete(nbAlphabetSymbols)) {
                nbSupTransitions += state.completion(autAlph);
            }
        }

        completeAutomaton.setNbTransitions(completeAutomaton.getNbTransitions() + nbSupTransitions);
        completeAutomaton.setStates(newStates);
        completeAutomaton.setNbStates(newStates.size());
        return completeAutomaton;
    }

    public Automaton minimization() {
        HashMap<String, LinkedList<String>> theta = new HashMap<>();
        LinkedList<String> exits = new LinkedList<>();
        LinkedList<String> noExits = new LinkedList<>();

        for (State state : states) {
            if (state.getIsExit()) {
                exits.add(state.getId());
            } else {
                noExits.add(state.getId());
            }
        }

        theta.put("T", exits);
        theta.put("NT", noExits);

        HashMap<String, LinkedList<String>> newTheta = new HashMap<>();
        int i = 0;

        while (theta.size() != newTheta.size()) {
            if (i > 0) {
                theta = new HashMap<>(newTheta);
                newTheta.clear();
            }
            for (String key : theta.keySet()) {
                HashMap<String, LinkedList<String>> intermediateTheta = new HashMap<>();
                String newValue = "";
                for (String id : theta.get(key)) {
                    StringBuilder newKey = new StringBuilder();

                    for (State state : states) {
                        if (state.getId().equals(id)) {
                            newValue = state.getId();
                            for (String letter : state.getNeighbours().keySet()) {
                                for (String key2 : theta.keySet()) {
                                    for (String id2 : theta.get(key2)) {
                                        if (state.getNeighbours().get(letter).get(0).equals(id2)) {
                                            newKey.append(key2).append(",");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    boolean find = false;
                    for (String key5 : intermediateTheta.keySet()) {
                        if (key5.equals(newKey.toString())) {
                            find = true;
                            break;
                        }
                    }

                    if (!find) {
                        intermediateTheta.put(newKey.toString(), new LinkedList<>());
                    }

                    intermediateTheta.get(newKey.toString()).add(newValue);
                }

                for (String key3 : intermediateTheta.keySet()) {
                    StringBuilder newThetaValue = new StringBuilder();
                    for (String n : intermediateTheta.get(key3)) {
                        newThetaValue.append(n).append(",");
                    }
                    newThetaValue.deleteCharAt(newThetaValue.length()-1);
                    newTheta.put(newThetaValue.toString(), intermediateTheta.get(key3));
                }
            }
            i++;
        }

        if (i == 0) {
            System.out.println("Already minimized");
        }

        Automaton automatonMinimized = new Automaton(nbAlphabetSymbols);
        LinkedList<State> newStates = new LinkedList<>();
        for (String key : theta.keySet()) {
            State newState = new State(key);
            newStates.add(newState);
        }
        automatonMinimized.setStates(newStates);
        newStates = automatonMinimized.getStates();

        for (State state : newStates) {
            char index = state.getId().charAt(0);
            for (State stat2 : states) {
                if (stat2.getId().equals(String.valueOf(index))) {
                    for (String letter : stat2.getNeighbours().keySet()) {
                        for (String id : stat2.getNeighbours().get(letter)) {
                            for (String thetaKey : theta.keySet()) {
                                for (String thetaValue : theta.get(thetaKey)) {
                                    if (thetaValue.equals(id)) {
                                        state.addNeighbour(letter, thetaKey);
                                        automatonMinimized.setNbTransitions(automatonMinimized.getNbTransitions() + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (State state : newStates) {
            boolean stateIsInit = false;
            boolean stateIsExit = false;
            int j = 0;
            while (j < state.getId().length()) {
                int k = j;
                StringBuilder index = new StringBuilder();
                while (k < state.getId().length() && state.getId().charAt(k) != ',') {
                    index.append(state.getId().charAt(k));
                    k++;
                }
                j = k + 1;

                for (State state1 : states) {
                    if (state1.getId().equals(String.valueOf(index))) {
                        if (!stateIsInit && state1.getIsInit()) {
                            stateIsInit = true;
                            automatonMinimized.setNbInitStates(automatonMinimized.getNbInitStates() + 1);
                        }

                        if (!stateIsExit && state1.getIsExit()) {
                            stateIsExit = true;
                            automatonMinimized.setNbExitStates(automatonMinimized.getNbExitStates() + 1);
                        }
                    }
                }
            }
        }

        automatonMinimized.setNbStates(newStates.size());

        automatonMinimized.setStates(newStates);

        return automatonMinimized;
    }

    public void print() {
        System.out.print("Etats Initiaux : ");
        for (State state : states) {
            if (state.getIsInit()) {
                System.out.println(state.getId() + " ");
            }
        }

        System.out.print("Etats Terminaux : ");
        for (State state : states) {
            if (state.getIsExit()) {
                System.out.println(state.getId() + " ");
            }
        }

        System.out.print("  ");
        char letter = 97;
        for (int i = 0; i < nbAlphabetSymbols; i++) {
            System.out.print(letter + " ");
            letter++;
        }
        System.out.println();

        for (State state : states) {
            System.out.print(state.getId() + " ");
            for (String letter2 : state.getNeighbours().keySet()) {
                for (String neighbourId : state.getNeighbours().get(letter2)) {
                    System.out.print(neighbourId);
                }
            }
            System.out.println();
        }
    }

    public void writeAutomatonOnFile(String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(nbAlphabetSymbols + "\n");
            myWriter.write(nbStates + "\n");
            LinkedList<String> entries = new LinkedList<>();
            LinkedList<String> exits = new LinkedList<>();

            for (State state : states) {
                if (state.getIsInit()) {
                    entries.add(state.getId());
                }

                if (state.getIsExit()) {
                    exits.add(state.getId());
                }
            }
            myWriter.write(nbInitStates + " ");
            for (String entryId : entries) {
                myWriter.write(entryId + " ");
            }
            myWriter.write("\n");

            for (String exitId : exits) {
                myWriter.write(exitId + " ");
            }
            myWriter.write("\n");

            myWriter.write(nbTransitions + "\n");

            int transitions = 0;
            for (State state : states) {
                for (String letter : state.getNeighbours().keySet()) {
                    for (String neighbourId : state.getNeighbours().get(letter)) {
                        transitions++;
                        if (transitions == nbTransitions) {
                            myWriter.write(state.getId() + letter + neighbourId);
                        } else {
                            myWriter.write(state.getId() + letter + neighbourId + "\n");
                        }
                    }
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
}
