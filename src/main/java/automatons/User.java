package automatons;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class User {
    private String word;
    private final String idInit;
    private String idCurrentState;
    private final Automaton automatonTested;

    public User(final String idInit, final Automaton automatonTested) {
        this.idInit = idInit;
        this.idCurrentState = idInit;
        this.automatonTested = automatonTested;
    }

    public void readWord() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter word (\"end\" stop the process of word recognition) : ");
        word = in.nextLine();
    }

    public void isWordRecognizable() {
        int i = 0;
        idCurrentState = idInit;

        while (i < word.length()) {
            State currentState = null;
            char character = word.charAt(i);
            for (State state : automatonTested.getStatesImprove()) {
                if (state.getId().equals(idCurrentState)) {
                    currentState = state;
                }
            }
            assert currentState != null;
            HashMap<String, LinkedList<String>> currentStateNeighbours = currentState.getNeighbours();

            // check case no possible transition
            if (!currentStateNeighbours.containsKey(String.valueOf(character))) {
                System.out.println("Word not recognized. No \"" + character + "\" transition.");
                return;
            }

            // get the next state
            idCurrentState = currentStateNeighbours.get(String.valueOf(character)).get(0);
            ++i;
        }

        if (automatonTested.getStatesImprove().get(Integer.parseInt(idCurrentState)).getIsExit()) {
            System.out.println("Word recognized.");
        } else {
            System.out.println("Word not recognized.");
        }
    }

    public void testWordSequence() {
        readWord();

        // "end" word is the key word to stop the process
        while (!word.equals("end")) {
            isWordRecognizable();
            readWord();
        }
    }
}
