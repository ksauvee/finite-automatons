package automatons;

import java.util.*;

public class MyString{
	private String str;
	
    public MyString(String str) {
		super();
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String removeDuplicates() {//a corriger on split la string en tableau d id et on supprime les id en double
    	//remove the duplicates in the id of a state
		String[] array = str.split("\\.");
		LinkedList<String> list = new LinkedList<String>();
		for(String number : array) {
			list.add(number);
		}
		/*LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 0 ; i<array.length ; i++) {
			if(array[i] != "") {
        		list.add(Integer.parseInt(array[i]));
        	}
		}
		Collections.sort(list);
		LinkedHashSet<String> listHSet = new LinkedHashSet<String>();
		for(Integer number : list) {
			listHSet.add(String.valueOf(number));
		}*/
		Collections.sort(list, Comparator.comparingInt((String s) -> s.length()).thenComparing((String s) -> s));    						
				
				
				
				// For a better comprehension we need to sort the list. We use Comparator interface		// First we compare the size of all the ID
    	String result = "";
        for (String number : list) {
        	result += number + ".";
        }
        if(result.length() > 0) {
        	result = result.substring(0,result.length()-1);
        }
        
        return result;
    }
}
