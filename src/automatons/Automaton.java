package automatons;

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
    private final LinkedList<State> states;

    public Automaton(final LinkedList<String> automatonInformations) {
        nbAlphabetSymbols = Integer.parseInt(automatonInformations.get(0));
        nbStates = Integer.parseInt(automatonInformations.get(1));
        states = new LinkedList<>();

        for (int i = 0; i < nbStates; ++i) {
            states.add(new State(String.valueOf(i)));
        }

        nbInitStates = Integer.parseInt(String.valueOf(automatonInformations.get(2).charAt(0)));

        for (int i = 2; i < 2 * nbInitStates + 1; i += 2) {
            states.get(Integer.parseInt(String.valueOf(automatonInformations.get(2).charAt(i)))).setIsInit(true);
        }

        nbExitStates = Integer.parseInt(String.valueOf(automatonInformations.get(3).charAt(0)));

        for (int i = 2; i < 2 * nbExitStates + 1; i += 2) {
            states.get(Integer.parseInt(String.valueOf(automatonInformations.get(3).charAt(i)))).setIsExit(true);
        }

        nbTransitions = Integer.parseInt(automatonInformations.get(4));

        for (int i = 0; i < nbTransitions; ++i) {
            states.get(Integer.parseInt(String.valueOf(automatonInformations.get(5+i).charAt(0)))).addNeighbour(String.valueOf(automatonInformations.get(5+i).charAt(1)), String.valueOf(automatonInformations.get(5+i).charAt(2)));
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
}
