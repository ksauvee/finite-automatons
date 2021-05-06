package automatons;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Automaton {
    private final int nbAlphabetSymbols;
    private int nbStates;
    private int nbInitStates;
    private int nbExitStates;
    private int nbTransitions;
    private LinkedList<State> states;
    private final static List<String> alphabet = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
    private final List<String> autAlph;

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

        for (int i = 0; i < nbTransitions; ++i) {
            String initialState = String.valueOf(automatonCharacteristics.get(5+i).charAt(0));
            String symbol = String.valueOf(automatonCharacteristics.get(5+i).charAt(1));
            String arrivalState = String.valueOf(automatonCharacteristics.get(5+i).charAt(2));

            states.get(Integer.parseInt(initialState)).addNeighbour(symbol, arrivalState);
        }
        this.autAlph = alphabet.subList(0, nbAlphabetSymbols);
    }

    public Automaton(Automaton automaton) {
        this.nbAlphabetSymbols = automaton.getNbAlphabetSymbols();
        this.nbStates = automaton.getNbStates();
        this.nbInitStates = automaton.getNbInitStates();
        this.nbExitStates = automaton.getNbExitStates();
        this.nbTransitions = automaton.getNbTransitions();
        this.states = automaton.getStates();
        this.autAlph = alphabet.subList(0, nbAlphabetSymbols);
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

    public LinkedList<String> getEntries() {
        LinkedList<String> entries = new LinkedList<>();
        for (State currentState : states) {
            entries.add(currentState.getId());
        }

        return entries;
    }

    public boolean isStandard() {
        LinkedList<String> entries = getEntries();

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


        LinkedList<State> states = standardizedAutomaton.getStates();
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

        Automaton completeAutomaton = new Automaton(this);
        LinkedList<State> newStates = completeAutomaton.getStates();

        State garbage = new State("P");
        newStates.add(garbage);

        for(State state : newStates) {
            if(state.isIncomplete(nbAlphabetSymbols)) {
                state.completion(autAlph);
            }
        }

        completeAutomaton.setStates(newStates);
        return completeAutomaton;
    }
}
