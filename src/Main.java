import automatons.Automaton;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // code here
        LinkedList<String> automatonCharacteristics = Automaton.readAutomatonOnFile("./resources/A3-1.txt");
        Automaton automaton = new Automaton(automatonCharacteristics);
        Automaton stantardizedAutomaton = automaton.standardizedAutomaton();
    }
}
