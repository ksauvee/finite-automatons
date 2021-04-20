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
		s0_nei_a.add("0");
		s0_nei_b.add("0");
		s0_nei_b.add("1");
		s1_nei_a.add("2");
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
	public static void affichage(Automaton a) {
		for(State states : a.getStates()) {
			System.out.println("ID : "+states.getId());
			System.out.println("Init : "+states.isInit());
			System.out.println("Exit : "+states.isExit());
			for(Map.Entry<String, LinkedList<String>> entry : states.getNeighbours().entrySet()) {
				System.out.println(entry);
			}
			System.out.println("             fin Etat "+states.getId()+"\n\n");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//TestA();
		TestA();
		
	}

}
