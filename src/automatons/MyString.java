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
		Collections.sort(list, Comparator.comparingInt((String s) -> s.length()).thenComparing((String s) -> s));    						
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
