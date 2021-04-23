package automatons;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class MyString {
    private String str;

    public MyString(String str) {
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
        LinkedList<String> list = new LinkedList<>();
        Collections.addAll(list, array);
        list.sort(Comparator.comparingInt(String::length).thenComparing((String s) -> s));
        StringBuilder result = new StringBuilder();

        for (String number : list) {
            result.append(number).append(".");
        }

        if (result.length() > 0) {
            result = new StringBuilder(result.substring(0, result.length() - 1));
        }

        return result.toString();
    }
}
