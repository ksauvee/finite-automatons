package automatons;

import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Automaton {
    private int nbStates;
    private LinkedList<State> states;

    public static void readAutomatonOnFile(final String filename) {
        try {
            File automatonInformation = new File(filename);
            Scanner reader = new Scanner(automatonInformation);

            for (int i = 0; i < 5; ++i) {
                if (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    System.out.println(data);
                }
            }

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error : File Not Found");
            e.printStackTrace();
        }
    }
}
