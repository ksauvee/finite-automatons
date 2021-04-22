package automatons;
import java.util.*;

public class Main {

	public static void TestA() {
		//créer les listes de voisins pour chaque caractère de l'alphabet
		LinkedList<String> s0_nei_a = new LinkedList<String>();
		LinkedList<String> s0_nei_b = new LinkedList<String>();
		LinkedList<String> s1_nei_a = new LinkedList<String>();
		LinkedList<String> s1_nei_b = new LinkedList<String>();
		LinkedList<String> s2_nei_a = new LinkedList<String>();
		LinkedList<String> s2_nei_b = new LinkedList<String>();
		LinkedList<String> s3_nei_a = new LinkedList<String>();
		LinkedList<String> s3_nei_b = new LinkedList<String>();
		//Ajouter les états d'arrivée sous forme de String
		s0_nei_a.add("10");
		s0_nei_b.add("10");
		s0_nei_b.add("1");
		s1_nei_a.add("32");
		s2_nei_b.add("23");
		//Créer les HashMap (Neighbours)
		HashMap<String, LinkedList<String>> s0_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s1_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s2_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s3_map = new HashMap<String, LinkedList<String>>();
		//Ajouter une entrée ayant pour clé la lettre et pour valeur la liste d'états d'arrivée précédemment créee
		s0_map.put("a", s0_nei_a);
		s0_map.put("b", s0_nei_b);
		s1_map.put("a", s1_nei_a);
		s1_map.put("b", s1_nei_b);
		s2_map.put("a", s2_nei_a);
		s2_map.put("b", s2_nei_b);
		s3_map.put("a", s3_nei_a);
		s3_map.put("b", s3_nei_b);
		//Créer les états à l'aide du constructeur en leur donnant le HashMap Neighbours
		State s0 = new State("10", true, false, s0_map);
		State s1 = new State("1", false, false, s1_map);
		State s2 = new State("32", false, false, s2_map);
		State s3 = new State("23", false, true, s3_map);
		
		LinkedList<State> a_states = new LinkedList<State>();
		a_states.add(s0);
		a_states.add(s1);
		a_states.add(s2);
		a_states.add(s3);
		
		
		//Construire l'automate avec son constructeur (synchrone ou non ?, la taille de son alphabet" et la liste des états construite)
		Automaton a = new Automaton(a_states, true, 2);
		//Afficher Automate de départ
		System.out.println("Automate de départ");
		affichage(a);
		Automaton b = a.det_sync();
		affichage(b);
	}
	public static void TestB() {
		LinkedList<String> s0_nei_a = new LinkedList<String>();
		LinkedList<String> s0_nei_b = new LinkedList<String>();
		LinkedList<String> s1_nei_a = new LinkedList<String>();
		LinkedList<String> s1_nei_b = new LinkedList<String>();
		LinkedList<String> s2_nei_a = new LinkedList<String>();
		LinkedList<String> s2_nei_b = new LinkedList<String>();
		LinkedList<String> s3_nei_a = new LinkedList<String>();
		LinkedList<String> s3_nei_b = new LinkedList<String>();
		//Ajouter les états d'arrivée sous forme de String
		s0_nei_a.add("0");
		s0_nei_b.add("0");
		s0_nei_b.add("1");
		s1_nei_a.add("2");
		s2_nei_a.add("3");
		s2_nei_b.add("3");
		//Créer les HashMap (Neighbours)
		HashMap<String, LinkedList<String>> s0_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s1_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s2_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s3_map = new HashMap<String, LinkedList<String>>();
		//Ajouter une entrée ayant pour clé la lettre et pour valeur la liste d'états d'arrivée précédemment créee
		s0_map.put("a", s0_nei_a);
		s0_map.put("b", s0_nei_b);
		s1_map.put("a", s1_nei_a);
		s1_map.put("b", s1_nei_b);
		s2_map.put("a", s2_nei_a);
		s2_map.put("b", s2_nei_b);
		s3_map.put("a", s3_nei_a);
		s3_map.put("b", s3_nei_b);
		//Créer les états à l'aide du constructeur en leur donnant le HashMap Neighbours
		State s0 = new State("0", true, false, s0_map);
		State s1 = new State("1", false, false, s1_map);
		State s2 = new State("2", false, false, s2_map);
		State s3 = new State("3", false, true, s3_map);
		
		LinkedList<State> a_states = new LinkedList<State>();
		a_states.add(s0);
		a_states.add(s1);
		a_states.add(s2);
		a_states.add(s3);
		
		
		//Construire l'automate avec son constructeur (synchrone ou non ?, la taille de son alphabet" et la liste des états construite)
		Automaton a = new Automaton(a_states, true, 2);
		//Afficher Automate de départ
		System.out.println("Automate de départ");
		affichage(a);
		Automaton b = a.det_sync();
		affichage(b);
	}
	public static void TestC() {
		//créer les listes de voisins pour chaque caractère de l'alphabet
		LinkedList<String> s0_nei_a = new LinkedList<String>();
		LinkedList<String> s0_nei_b = new LinkedList<String>();
		LinkedList<String> s1_nei_a = new LinkedList<String>();
		LinkedList<String> s1_nei_b = new LinkedList<String>();
		LinkedList<String> s2_nei_a = new LinkedList<String>();
		LinkedList<String> s2_nei_b = new LinkedList<String>();
		LinkedList<String> s3_nei_a = new LinkedList<String>();
		LinkedList<String> s3_nei_b = new LinkedList<String>();
		//Ajouter les états d'arrivée sous forme de String
		s0_nei_a.add("1");
		s1_nei_b.add("2");
		s2_nei_a.add("3");
		//Créer les HashMap (Neighbours)
		HashMap<String, LinkedList<String>> s0_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s1_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s2_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s3_map = new HashMap<String, LinkedList<String>>();
		//Ajouter une entrée ayant pour clé la lettre et pour valeur la liste d'états d'arrivée précédemment créee
		s0_map.put("a", s0_nei_a);
		s0_map.put("b", s0_nei_b);
		s1_map.put("a", s1_nei_a);
		s1_map.put("b", s1_nei_b);
		s2_map.put("a", s2_nei_a);
		s2_map.put("b", s2_nei_b);
		s3_map.put("a", s3_nei_a);
		s3_map.put("b", s3_nei_b);
		//Créer les états à l'aide du constructeur en leur donnant le HashMap Neighbours
		State s0 = new State("0", true, false, s0_map);
		State s1 = new State("1", false, true, s1_map);
		State s2 = new State("2", true, false, s2_map);
		State s3 = new State("3", false, true, s3_map);
		
		LinkedList<State> a_states = new LinkedList<State>();
		a_states.add(s0);
		a_states.add(s1);
		a_states.add(s2);
		a_states.add(s3);
		
		
		//Construire l'automate avec son constructeur (synchrone ou non ?, la taille de son alphabet" et la liste des états construite)
		Automaton a = new Automaton(a_states, true, 2);
		//Afficher Automate de départ
		System.out.println("Automate de départ");
		affichage(a);
		Automaton b = a.det_sync();
		affichage(b);
	}
	public static void TestC2() {
		//créer les listes de voisins pour chaque caractère de l'alphabet
		LinkedList<String> s0_nei_a = new LinkedList<String>();
		LinkedList<String> s0_nei_b = new LinkedList<String>();
		LinkedList<String> s1_nei_a = new LinkedList<String>();
		LinkedList<String> s1_nei_b = new LinkedList<String>();
		LinkedList<String> s2_nei_a = new LinkedList<String>();
		LinkedList<String> s2_nei_b = new LinkedList<String>();
		LinkedList<String> s3_nei_a = new LinkedList<String>();
		LinkedList<String> s3_nei_b = new LinkedList<String>();
		//Ajouter les états d'arrivée sous forme de String
		s0_nei_a.add("1");
		s2_nei_b.add("3");
		//Créer les HashMap (Neighbours)
		HashMap<String, LinkedList<String>> s0_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s1_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s2_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s3_map = new HashMap<String, LinkedList<String>>();
		//Ajouter une entrée ayant pour clé la lettre et pour valeur la liste d'états d'arrivée précédemment créee
		s0_map.put("a", s0_nei_a);
		s0_map.put("b", s0_nei_b);
		s1_map.put("a", s1_nei_a);
		s1_map.put("b", s1_nei_b);
		s2_map.put("a", s2_nei_a);
		s2_map.put("b", s2_nei_b);
		s3_map.put("a", s3_nei_a);
		s3_map.put("b", s3_nei_b);
		//Créer les états à l'aide du constructeur en leur donnant le HashMap Neighbours
		State s0 = new State("0", true, false, s0_map);
		State s1 = new State("1", false, true, s1_map);
		State s2 = new State("2", true, false, s2_map);
		State s3 = new State("3", false, true, s3_map);
		
		LinkedList<State> a_states = new LinkedList<State>();
		a_states.add(s0);
		a_states.add(s1);
		a_states.add(s2);
		a_states.add(s3);
		//Construire l'automate avec son constructeur (synchrone ou non ?, la taille de son alphabet" et la liste des états construite)
		Automaton a = new Automaton(a_states, true, 2);
		//Afficher Automate de départ
		System.out.println("Automate de départ");
		affichage(a);
		Automaton b = a.det_sync();
		affichage(b);
	}
	public static void TestC3() {
		//créer les listes de voisins pour chaque caractère de l'alphabet
		LinkedList<String> s0_nei_a = new LinkedList<String>();
		LinkedList<String> s0_nei_b = new LinkedList<String>();
		LinkedList<String> s1_nei_a = new LinkedList<String>();
		LinkedList<String> s1_nei_b = new LinkedList<String>();
		LinkedList<String> s2_nei_a = new LinkedList<String>();
		LinkedList<String> s2_nei_b = new LinkedList<String>();
		LinkedList<String> s3_nei_a = new LinkedList<String>();
		LinkedList<String> s3_nei_b = new LinkedList<String>();
		//Ajouter les états d'arrivée sous forme de String
		s0_nei_a.add("1");
		s2_nei_a.add("3");
		//Créer les HashMap (Neighbours)
		HashMap<String, LinkedList<String>> s0_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s1_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s2_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s3_map = new HashMap<String, LinkedList<String>>();
		//Ajouter une entrée ayant pour clé la lettre et pour valeur la liste d'états d'arrivée précédemment créee
		s0_map.put("a", s0_nei_a);
		s0_map.put("b", s0_nei_b);
		s1_map.put("a", s1_nei_a);
		s1_map.put("b", s1_nei_b);
		s2_map.put("a", s2_nei_a);
		s2_map.put("b", s2_nei_b);
		s3_map.put("a", s3_nei_a);
		s3_map.put("b", s3_nei_b);
		//Créer les états à l'aide du constructeur en leur donnant le HashMap Neighbours
		State s0 = new State("0", true, false, s0_map);
		State s1 = new State("1", false, true, s1_map);
		State s2 = new State("2", true, false, s2_map);
		State s3 = new State("3", false, true, s3_map);
		
		LinkedList<State> a_states = new LinkedList<State>();
		a_states.add(s0);
		a_states.add(s1);
		a_states.add(s2);
		a_states.add(s3);
		//Construire l'automate avec son constructeur (synchrone ou non ?, la taille de son alphabet" et la liste des états construite)
		Automaton a = new Automaton(a_states, true, 2);
		//Afficher Automate de départ
		System.out.println("Automate de départ");
		affichage(a);
		Automaton b = a.det_sync();
		affichage(b);
	}
	public static void TestD() {
		//créer les listes de voisins pour chaque caractère de l'alphabet
		LinkedList<String> s0_nei_a = new LinkedList<String>();
		LinkedList<String> s0_nei_b = new LinkedList<String>();
		LinkedList<String> s1_nei_a = new LinkedList<String>();
		LinkedList<String> s1_nei_b = new LinkedList<String>();
		LinkedList<String> s2_nei_a = new LinkedList<String>();
		LinkedList<String> s2_nei_b = new LinkedList<String>();
		//Ajouter les états d'arrivée sous forme de String
		s0_nei_a.add("0");
		s0_nei_a.add("1");
		s0_nei_b.add("1");
		s1_nei_a.add("2");
		s1_nei_b.add("2");
		s2_nei_a.add("2");
		s2_nei_b.add("2");
		//Créer les HashMap (Neighbours)
		HashMap<String, LinkedList<String>> s0_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s1_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s2_map = new HashMap<String, LinkedList<String>>();
		//Ajouter une entrée ayant pour clé la lettre et pour valeur la liste d'états d'arrivée précédemment créee
		s0_map.put("a", s0_nei_a);
		s0_map.put("b", s0_nei_b);
		s1_map.put("a", s1_nei_a);
		s1_map.put("b", s1_nei_b);
		s2_map.put("a", s2_nei_a);
		s2_map.put("b", s2_nei_b);
		//Créer les états à l'aide du constructeur en leur donnant le HashMap Neighbours
		State s0 = new State("0", true, false, s0_map);
		State s1 = new State("1", false, true, s1_map);
		State s2 = new State("2", false, false, s2_map);
		
		LinkedList<State> a_states = new LinkedList<State>();
		a_states.add(s0);
		a_states.add(s1);
		a_states.add(s2);
		
		
		//Construire l'automate avec son constructeur (synchrone ou non ?, la taille de son alphabet" et la liste des états construite)
		Automaton a = new Automaton(a_states, true, 2);
		//Afficher Automate de départ
		System.out.println("Automate de départ");
		affichage(a);
		Automaton b = a.det_sync();
		System.out.println("Nouvel Automate");
		affichage(b);
	}
	public static void Test2_1(){
		LinkedList<String> s0_nei_a = new LinkedList<String>();
		LinkedList<String> s0_nei_b = new LinkedList<String>();
		LinkedList<String> s1_nei_a = new LinkedList<String>();
		LinkedList<String> s1_nei_b = new LinkedList<String>();
		LinkedList<String> s2_nei_a = new LinkedList<String>();
		LinkedList<String> s2_nei_b = new LinkedList<String>();
		LinkedList<String> s3_nei_a = new LinkedList<String>();
		LinkedList<String> s3_nei_b = new LinkedList<String>();
		LinkedList<String> s4_nei_a = new LinkedList<String>();
		LinkedList<String> s4_nei_b = new LinkedList<String>();
		LinkedList<String> s5_nei_a = new LinkedList<String>();
		LinkedList<String> s5_nei_b = new LinkedList<String>();
		LinkedList<String> s6_nei_a = new LinkedList<String>();
		LinkedList<String> s6_nei_b = new LinkedList<String>();
		LinkedList<String> s7_nei_a = new LinkedList<String>();
		LinkedList<String> s7_nei_b = new LinkedList<String>();
		LinkedList<String> s8_nei_a = new LinkedList<String>();
		LinkedList<String> s8_nei_b = new LinkedList<String>();
		LinkedList<String> s9_nei_a = new LinkedList<String>();
		LinkedList<String> s9_nei_b = new LinkedList<String>();
		LinkedList<String> s10_nei_a = new LinkedList<String>();
		LinkedList<String> s10_nei_b = new LinkedList<String>();
		LinkedList<String> s11_nei_a = new LinkedList<String>();
		LinkedList<String> s11_nei_b = new LinkedList<String>();
		LinkedList<String> s12_nei_a = new LinkedList<String>();
		LinkedList<String> s12_nei_b = new LinkedList<String>();
		LinkedList<String> s13_nei_a = new LinkedList<String>();
		LinkedList<String> s13_nei_b = new LinkedList<String>();
		LinkedList<String> s14_nei_a = new LinkedList<String>();
		LinkedList<String> s14_nei_b = new LinkedList<String>();
		
		LinkedList<String> s0_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s1_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s2_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s3_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s4_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s5_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s6_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s7_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s8_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s9_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s10_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s11_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s12_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s13_nei_epsilon = new LinkedList<String>();
		LinkedList<String> s14_nei_epsilon = new LinkedList<String>();
		
		
		//Ajouter les états d'arrivée sous forme de String
		s0_nei_a.add("1");
		s1_nei_epsilon.add("2");
		s1_nei_epsilon.add("4");
		s2_nei_b.add("3");
		s3_nei_epsilon.add("2");
		s3_nei_epsilon.add("4");
		s4_nei_epsilon.add("5");
		s4_nei_epsilon.add("13");
		s5_nei_epsilon.add("6");
		s5_nei_epsilon.add("9");
		s6_nei_b.add("7");
		s7_nei_a.add("8");
		s8_nei_epsilon.add("12");
		s9_nei_a.add("10");
		s10_nei_b.add("11");
		s11_nei_epsilon.add("12");
		s12_nei_epsilon.add("5");
		s12_nei_epsilon.add("13");
		s13_nei_b.add("14");
		//Créer les HashMap (Neighbours)
		HashMap<String, LinkedList<String>> s0_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s1_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s2_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s3_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s4_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s5_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s6_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s7_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s8_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s9_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s10_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s11_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s12_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s13_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s14_map = new HashMap<String, LinkedList<String>>();
		//Ajouter une entrée ayant pour clé la lettre et pour valeur la liste d'états d'arrivée précédemment créée
		s0_map.put("a", s0_nei_a);
		s0_map.put("b", s0_nei_b);
		s1_map.put("a", s1_nei_a);
		s1_map.put("b", s1_nei_b);
		s2_map.put("a", s2_nei_a);
		s2_map.put("b", s2_nei_b);
		s3_map.put("a", s3_nei_a);
		s3_map.put("b", s3_nei_b);
		s4_map.put("a", s4_nei_a);
		s4_map.put("b", s4_nei_b);
		s5_map.put("a", s5_nei_a);
		s5_map.put("b", s5_nei_b);
		s6_map.put("a", s6_nei_a);
		s6_map.put("b", s6_nei_b);
		s7_map.put("a", s7_nei_a);
		s7_map.put("b", s7_nei_b);
		s8_map.put("a", s8_nei_a);
		s8_map.put("b", s8_nei_b);
		s9_map.put("a", s9_nei_a);
		s9_map.put("b", s9_nei_b);
		s10_map.put("a", s10_nei_a);
		s10_map.put("b", s10_nei_b);
		s11_map.put("a", s11_nei_a);
		s11_map.put("b", s11_nei_b);
		s12_map.put("a", s12_nei_a);
		s12_map.put("b", s12_nei_b);
		s13_map.put("a", s13_nei_a);
		s13_map.put("b", s13_nei_b);
		s14_map.put("a", s14_nei_a);
		s14_map.put("b", s14_nei_b);
		
		s1_map.put("*", s1_nei_epsilon);
		s3_map.put("*", s3_nei_epsilon);
		s4_map.put("*", s4_nei_epsilon);
		s5_map.put("*", s5_nei_epsilon);
		s8_map.put("*", s8_nei_epsilon);
		s11_map.put("*", s11_nei_epsilon);
		s12_map.put("*", s12_nei_epsilon);
		//Créer les états à l'aide du constructeur en leur donnant le HashMap Neighbours
		State s0 = new State("0", true, false, s0_map);
		State s1 = new State("1", false, false, s1_map);
		State s2 = new State("2", false, false, s2_map);
		State s3 = new State("3", false, false, s3_map);
		State s4 = new State("4", false, false, s4_map);
		State s5 = new State("5", false, false, s5_map);
		State s6 = new State("6", false, false, s6_map);
		State s7 = new State("7", false, false, s7_map);
		State s8 = new State("8", false, false, s8_map);
		State s9 = new State("9", false, false, s9_map);
		State s10 = new State("10", false, false, s10_map);
		State s11 = new State("11", false, false, s11_map);
		State s12 = new State("12", false, false, s12_map);
		State s13 = new State("13", false, false, s13_map);
		State s14 = new State("14", false, true, s14_map);
		
		LinkedList<State> a_states = new LinkedList<State>();
		a_states.add(s0);
		a_states.add(s1);
		a_states.add(s2);
		a_states.add(s3);
		a_states.add(s4);
		a_states.add(s5);
		a_states.add(s6);
		a_states.add(s7);
		a_states.add(s8);
		a_states.add(s9);
		a_states.add(s10);
		a_states.add(s11);
		a_states.add(s12);
		a_states.add(s13);
		a_states.add(s14);
		
		//Construire l'automate avec son constructeur (synchrone ou non ?, la taille de son alphabet" et la liste des états construite)
		Automaton a = new Automaton(a_states, true, 2);
		//Afficher Automate de départ
		//System.out.println("Automate de départ");
		affichage(a);
		System.out.println("Automate déterminisé");
		//System.out.println("alphabet: "+a.aut_alph.toString());
		affichage(a.synchronize_automaton().det_sync());
		
	}
	public static void affichage(Automaton a) {
		for(State states : a.getStates()) {
			System.out.println("ID : "+states.getId());
			System.out.println("Init : "+states.isInit());
			System.out.println("Exit : "+states.isExit());
			for(Map.Entry<String, LinkedList<String>> entry : states.getNeighbours().entrySet()) {
				System.out.println(entry);
			}
			if(states.getEpsilon_transitions().size()>0) {
				System.out.print("Epsilon transitions : ");
				for(State epsilon_states : states.getEpsilon_transitions()) {
					System.out.print(epsilon_states.getId()+".");
				}
				System.out.println();
			}
			System.out.println("             fin Etat "+states.getId()+"\n\n");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//TestA();
		Test2_1();
		
	}

}
