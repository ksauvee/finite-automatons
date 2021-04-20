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
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 0 ; i<array.length ; i++) {
			if(array[i] != "") {
        		list.add(Integer.parseInt(array[i]));
        	}
		}
		Collections.sort(list);
		LinkedHashSet<String> listHSet = new LinkedHashSet<String>();
		for(Integer number : list) {
			listHSet.add(String.valueOf(number));
		}
    	String result = "";
        for (String number : listHSet) {
        	result= result + number + ".";
        }
        if(result.length() > 0) {
        	result = result.substring(0,result.length()-1);
        }
        
        return result;
    }
}
