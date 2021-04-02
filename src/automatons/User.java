package automatons;

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

    }

    public void isWordRecognizable() {

    }
}
