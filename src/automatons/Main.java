package automatons;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList<String> s1_nei_a = new LinkedList<String>();
		LinkedList<String> s1_nei_b = new LinkedList<String>();
		LinkedList<String> s2_nei_a = new LinkedList<String>();
		LinkedList<String> s2_nei_b = new LinkedList<String>();
		//LinkedList<String> s3_nei = new LinkedList<String>();
		s1_nei_a.add("3");
		s1_nei_b.add("5");
		s1_nei_b.add("4");

		s2_nei_a.add("6");
		s2_nei_a.add("5");
		s2_nei_b.add("3");
		s2_nei_b.add("4");
		s2_nei_b.add("4");
		
		HashMap<String, LinkedList<String>> s1_map = new HashMap<String, LinkedList<String>>();
		HashMap<String, LinkedList<String>> s2_map = new HashMap<String, LinkedList<String>>();
		//HashMap<String, LinkedList<String>> s3_map = new HashMap<String, LinkedList<String>>();
		s1_map.put("a", s1_nei_a);
		s1_map.put("b", s1_nei_b);
		
		s2_map.put("a", s2_nei_a);
		s2_map.put("b", s2_nei_b);
		
		State s1 = new State("1", true, false, s1_map);
		State s2 = new State("2", true, false, s2_map);
		State s3 = new State("3", false, false, new HashMap<String, LinkedList<String>>());
		State s4 = new State("4", false, false, new HashMap<String, LinkedList<String>>());
		State s5 = new State("5", false, false, new HashMap<String, LinkedList<String>>());
		State s6 = new State("6", false, false, new HashMap<String, LinkedList<String>>());
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
