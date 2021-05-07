import automatons.Automaton;
import automatons.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean end;
        do{
            System.out.print("Entrez le numero de l'Automate a tester : ");
            //we create a new Scanner object
            Scanner keyboardInputAutomatonNumber = new Scanner(System.in);
            //we ask to the user what Automaton he wants to test
            int automatonNumber = keyboardInputAutomatonNumber.nextInt();
            String automatonFileName = "./resources/Test";

            //if the number is < 10 then we have to put a '0' in the file name
            if (automatonNumber < 10) {
                automatonFileName+="0";
            }

            automatonFileName += automatonNumber +".txt";
            System.out.printf("L'Automate selectionne est %s\n", automatonFileName);
            Automaton testedAutomaton = new Automaton (Automaton.readAutomatonOnFile(automatonFileName));
            System.out.println("Voici l'Automate");
            testedAutomaton.printAutomaton();


            System.out.println();
            System.out.println("\t\t\t\t\tMenu\nTapez les commandes suivantes pour utiliser les fonctionnalites :");
            System.out.println("""
                    - dc : Determinisation et Completion
                    - m : Minimisation (l'automate sera d'abord déterminise et complete)
                    - w : Reconnaissance de mots (l'automate sera d'abord determinise)
                    - c : Construction de l'Automate reconnaissant le langage complementaire (l'automate minimal sera utilise)
                    - s : Standardisation""");
            Scanner keyboardInputFunctionality = new Scanner(System.in);
            String functionality = keyboardInputFunctionality.nextLine();

            switch(functionality){
                case "dc"-> {
                    System.out.println("La fonctionnalite choisie est Determinisation et Completion\n\n\n");
                    Automaton deterministAutomaton = new Automaton();
                    Automaton completeAutomaton = new Automaton();
                    if(!testedAutomaton.getSync()){
                        boolean goodInput = false;
                        do{
                            System.out.println("- 1 : Afficher les clotures epsilon\n- 2 : ne pas afficher les clotures epsilon");
                            Scanner keyboardInputEpsilonTransitionsPrint = new Scanner(System.in);
                            int epsilonTransitionsPrint = keyboardInputEpsilonTransitionsPrint.nextInt();
                            if(epsilonTransitionsPrint == 1){
                                System.out.println("Vous avez choisi d'afficher les clotures epsilon\n");
                                deterministAutomaton = testedAutomaton.determinisationAsync(true);
                                goodInput = true;
                            }else if(epsilonTransitionsPrint == 2){
                                System.out.println("Vous avez choisi de ne pas afficher les clotures epsilon\n");
                                deterministAutomaton = testedAutomaton.determinisationAsync(false);
                                goodInput = true;
                            }else{
                                System.out.println("Veuillez saisir <1> ou <2> ");
                            }
                        }while(!goodInput);
                    }else{
                        deterministAutomaton = testedAutomaton.determinisationSync();
                        completeAutomaton = deterministAutomaton.completion();
                    }
                    System.out.println("Voici l'automate déterminise");
                    completeAutomaton.printAutomaton();
                }
                case "m"-> {
                    System.out.println("La fonctionnalite choisie est Minimisation\n\n\n");
                    Automaton minimalAutomaton;
                    Automaton deterministAutomaton = new Automaton();
                    if(testedAutomaton.getSync()){
                        deterministAutomaton = testedAutomaton.determinisationSync();
                    }else{
                        System.out.println("Votre Automate est asynchrone");
                        boolean goodInput = false;
                        do{
                            System.out.println("- 1 : Afficher les clotures epsilon\n- 2 : ne pas afficher les clotures epsilon");
                            Scanner keyboardInputEpsilonTransitionsPrint = new Scanner(System.in);
                            int epsilonTransitionsPrint = keyboardInputEpsilonTransitionsPrint.nextInt();
                            if(epsilonTransitionsPrint == 1){
                                System.out.println("Vous avez choisi d'afficher les clotures epsilon\n");
                                deterministAutomaton = testedAutomaton.determinisationAsync(true);
                                goodInput = true;
                            }else if(epsilonTransitionsPrint == 2){
                                System.out.println("Vous avez choisi de ne pas afficher les clotures epsilon\n");
                                deterministAutomaton = testedAutomaton.determinisationAsync(false);
                                goodInput = true;
                            }else{
                                System.out.println("Veuillez saisir <1> ou <2> ");
                            }
                        }while(!goodInput);
                    }
                    System.out.println("Voici l'automate déterminise");
                    deterministAutomaton.printAutomaton();
                    System.out.println("\n\nVoici l'automate minimise");
                    minimalAutomaton = deterministAutomaton.completion().minimization();
                    System.out.println("\n");
                    minimalAutomaton.printAutomaton();
                }
                case "w" -> {
                    System.out.println("La fonctionnalite choisie est Reconnaissance de mots\n");
                    Automaton deterministAutomaton = new Automaton();
                    if(!testedAutomaton.getSync()){
                        boolean goodInput = false;
                        do{
                            System.out.println("- 1 : Afficher les clotures epsilon\n- 2 : ne pas afficher les clotures epsilon");
                            Scanner keyboardInputEpsilonTransitionsPrint = new Scanner(System.in);
                            int epsilonTransitionsPrint = keyboardInputEpsilonTransitionsPrint.nextInt();
                            if(epsilonTransitionsPrint == 1){
                                System.out.println("Vous avez choisi d'afficher les clotures epsilon\n");
                                deterministAutomaton = testedAutomaton.determinisationAsync(true);
                                goodInput = true;
                            }else if(epsilonTransitionsPrint == 2){
                                System.out.println("Vous avez choisi de ne pas afficher les clotures epsilon\n");
                                deterministAutomaton = testedAutomaton.determinisationAsync(false);
                                goodInput = true;
                            }else{
                                System.out.println("Veuillez saisir <1> ou <2> ");
                            }
                        }while(!goodInput);
                    }else{
                        deterministAutomaton = testedAutomaton.determinisationSync();
                    }

                    String idInit = deterministAutomaton.getEntries2().get(0);
                    User user = new User(idInit, deterministAutomaton);
                    user.testWordSequence();
                }
                case "c" -> {
                    System.out.println("La fonctionnalite choisie est Construction de l'Automate reconnaissant le langage complementaire\n\n\n");
                    Automaton complementaryAutomaton = testedAutomaton.complementarization();
                    System.out.println("Voici l'Automate reconnaissant le langage complementaire du langage reconnu par l'Automate donne");
                    complementaryAutomaton.printAutomaton();
                }
                case "s" -> {
                    System.out.println("La fonctionnalite choisie est Standardisation\n\n\n");
                    Automaton standardAutomaton = testedAutomaton.standardization();
                    System.out.println("Voici l'Automate standard reconnaissant le même langage que l'Automate donne");
                    standardAutomaton.printAutomaton();
                }
                default -> System.out.println("Le programme a rencontre une erreur, veuillez recommencer");
            }
            System.out.println("Souhaitez-vous tester un autre Automate ?\n- 1 : Oui\n- 2 : Non");
            Scanner keyboardInputEnd = new Scanner(System.in);
            int endAnswer = keyboardInputEnd.nextInt();
            end = endAnswer == 1;
        }while(end);
        System.out.println("Merci !");
    }
}
