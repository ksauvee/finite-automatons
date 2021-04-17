package automatons;
//salut2
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

		//construire et afficher l'automate déterminisé
		/*Automaton a_det = a.det_sync();
		//a_det.det_sync(a);
		System.out.println("Automate déterminisé");
		affichage(a_det);*/
		
		/*State new_state = a.StringtoState("0.1.3");
		System.out.println("ID : "+new_state.getId());
		System.out.println("Init : "+new_state.isInit());
		System.out.println("Exit : "+new_state.isExit());
		for(Map.Entry<String, LinkedList<String>> entry : new_state.getNeighbours().entrySet()) {
			System.out.println(entry);
		}
		System.out.println("             fin Etat "+new_state.getId()+"\n\n");*/
	}
	public static void TestB() {
		//Salut
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
		
	}
	public static void TestC3() {
		
	}
	public static void TestD() {
		
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
		TestC();
		
		
		
		
		
		
		/*LinkedList<String> s1_nei_a = new LinkedList<String>();
		LinkedList<String> s1_nei_b = new LinkedList<String>();
		LinkedList<String> s2_nei_a = new LinkedList<String>();
		LinkedList<String> s2_nei_b = new LinkedList<String>();
		LinkedList<String> s3_nei_a = new LinkedList<String>();
		LinkedList<String> s3_nei_b = new LinkedList<String>();
		LinkedList<String> s4_nei_a = new LinkedList<String>();
		LinkedList<String> s4_nei_b = new LinkedList<String>();
		//LinkedList<String> s3_nei = new LinkedList<String>();
		s1_nei_a.add("3");
		s1_nei_b.add("5");

		s2_nei_a.add("6");
		s2_nei_a.add("5");
		s2_nei_b.add("3");
		s2_nei_b.add("4");
		
		s3_nei_a.add("3");
		s3_nei_b.add("4");
		
		s4_nei_a.add("5");
		s4_nei_b.add("3");
		
		HashMap<String, LinkedList<String>> s1_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s2_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s3_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s4_map = new HashMap<String, LinkedList<String>>();
		//HashMap<String, LinkedList<String>> s3_map = new HashMap<String, LinkedList<String>>();
		s1_map.put("a", s1_nei_a);
		s1_map.put("b", s1_nei_b);
		
		s2_map.put("a", s2_nei_a);
		s2_map.put("b", s2_nei_b);
		
		s3_map.put("a", s1_nei_a);
		s3_map.put("b", s1_nei_b);
		
		s4_map.put("a", s2_nei_a);
		s4_map.put("b", s2_nei_b);
		
		State s1 = new State("1", true, false, s1_map);
		State s2 = new State("2", true, false, s2_map);
		State s3 = new State("3", false, false, s3_map);
		State s4 = new State("4", false, true, s4_map);
		State s5 = new State("5", false, false, new HashMap<String, LinkedList<String>>());
		State s6 = new State("6", false, true, new HashMap<String, LinkedList<String>>());
		LinkedList<State> a_states = new LinkedList<State>();
		a_states.add(s1);
		a_states.add(s2);
		a_states.add(s3);
		a_states.add(s4);
		a_states.add(s5);
		a_states.add(s6);
		
		Automaton a = new Automaton(a_states, true, 2);
		
		Automaton new_a = new Automaton(new LinkedList<State>(), true, 2);
		new_a.det_sync(a);
		for(State states : a.getStates()) {
			System.out.println("ID : "+states.getId());
			System.out.println("Init : "+states.isInit());
			System.out.println("Exit : "+states.isExit());
			for(Map.Entry<String, LinkedList<String>> entry : states.getNeighbours().entrySet()) {
				String key = entry.getKey();
				LinkedList<String> list = entry.getValue();
				System.out.println("Lettre :" + key);
				for(String number : list) {
					System.out.println(number+", ");
				}
			}
			System.out.println("             fin\n\n");
		}
		System.out.println("Automate new_a");
		for(State states1 : new_a.getStates()) {
			System.out.println("ID : "+states1.getId());
			System.out.println("Init : "+states1.isInit());
			System.out.println("Exit : "+states1.isExit());
			for(Map.Entry<String, LinkedList<String>> entry : states1.getNeighbours().entrySet()) {
				String key = entry.getKey();
				LinkedList<String> list = entry.getValue();
				System.out.println("Lettre :" + key);
				for(String number : list) {
					System.out.println(number+", ");
				}
			}
			System.out.println("             fin\n\n");
		}
		
		/*System.out.println("Etat ");
		for(Map.Entry<String, LinkedList<String>> entry : s1.getNeighbours().entrySet()) {
			String key = entry.getKey();
			LinkedList<String> list = entry.getValue();
			System.out.println("Lettre :" + key);
			for(String number : list) {
				System.out.println(number+", ");
			}
		}

		System.out.println("Etat 1");
		for(Map.Entry<String, LinkedList<String>> entry : s1.getNeighbours().entrySet()) {
			String key = entry.getKey();
			LinkedList<String> list = entry.getValue();
			System.out.println("Lettre :" + key);
			for(String number : list) {
				System.out.println(number+", ");
			}
		}
		System.out.println("Etat 2");
		for(Map.Entry<String, LinkedList<String>> entry : s2.getNeighbours().entrySet()) {
			String key = entry.getKey();
			LinkedList<String> list = entry.getValue();
			System.out.println("Lettre :" + key);
			for(String number : list) {
				System.out.println(number+", ");
			}
		}
		
		/*State s12 = new State("", false, false, new HashMap<>());
		s12.concat(s1,  s2);
		
		System.out.println("Etat :" + s12.getId());
		System.out.println("Etat :" + s12.isInit());
		System.out.println("Etat :" + s12.isExit());
		for(Map.Entry<String, LinkedList<String>> entry : s12.getNeighbours().entrySet()) {
			String key = entry.getKey();
			LinkedList<String> list = entry.getValue();
			System.out.println("Lettre :" + key);
			for(String number : list) {
				System.out.println(number+", ");
			}
		}*/
	}

}
