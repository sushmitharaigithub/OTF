package com.steps_definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

	public static void main(String[] args) {
		
		String[] input = {"cat","act" ,"bat","cow","tab"};
	   
	    List<String> inputList = new ArrayList<String>();
	    for (String str : input)
	      inputList.add(str);

	    Set<String> list = new HashSet<String>();

	    int i = 0;
	    int j = 0;
	    for (; i < inputList.size() - 1; i++) {
	      System.out.println(list);
	      for (j = i + 1; j < inputList.size(); j++) {
	        if (checkAnagram(inputList.get(i), inputList.get(j))) {
	          list.add(inputList.get(i));
	          list.add(inputList.get(j));
	          inputList.remove(j--);
	        }
	      }
	    }

	    System.out.println(list);
	  }
	  
	  private static boolean checkAnagram(String str1, String str2) {

	    if (str1.length() != str2.length()) {
	      return false;
	    }

	    char[] s1Array = str1.toLowerCase().toCharArray();

	    char[] s2Array = str2.toLowerCase().toCharArray();

	    Arrays.sort(s1Array);

	    Arrays.sort(s2Array);

	    return Arrays.equals(s1Array, s2Array);

	  }

	
	
	
}