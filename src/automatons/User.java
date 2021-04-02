package automatons;

import java.util.Scanner;

public class User {
    private String word;
    private final String idInit;
    private final String idCurrentState;
    private final Automaton automatonTested;

    public User(final String idInit, final Automaton automatonTested) {
        this.idInit = idInit;
        this.idCurrentState = idInit;
        this.automatonTested = automatonTested;
    }

    public void readWord() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter word : (\"end\" stop the process of word recognition)");
        word = in.nextLine();
    }

    public void isWordRecognizable() {

    }
}
