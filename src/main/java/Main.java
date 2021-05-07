import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Entrez le numero de l'Automate a tester : ");
        Scanner clavier = new Scanner(System.in);
        int automatonNumber = clavier.nextInt();
        String stringAutomatonNumber = "Test";
        if (automatonNumber < 10) {
            stringAutomatonNumber+="0";
        }
        stringAutomatonNumber+= automatonNumber +".txt";
        System.out.printf("L'Automate selectionna est %s\n", stringAutomatonNumber);
    }
}