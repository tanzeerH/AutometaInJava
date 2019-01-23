package com.autometa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

public class DFA {
	
	public static enum State{
		START,IDENTIFIER, OPERATOR,INTEGER,TRAP
	}
	public static HashMap<String, String> hashMap = new HashMap<>();
	public static enum TYPE{
		OPERATOR,ALPHA,INTLIT
	}
	public static HashSet<Character> operators ;
	static {
		operators = new HashSet<>();
		operators.add('+');
		operators.add('-');
		operators.add('*');
		operators.add('/');
		operators.add('=');
	}
	// o == operator, 1 == alpha, 2 == int
	
	static State transition [][] = new State[][] {
		{State.OPERATOR, State.IDENTIFIER, State.INTEGER},
		{State.START, State.IDENTIFIER, State.IDENTIFIER},
		{State.START, State.START, State.START},
		{State.START, State.TRAP, State.INTEGER},
		{State.TRAP, State.TRAP, State.TRAP}
	};
	public static void main(String[] args) {
		
		DFA dfa = new DFA();
		String input="harpo=larry*curly+moe*groucho + 12+ 12ee";
		dfa.readfromFile();
		for ( String key : hashMap.keySet())
			System.out.println(key +"  "+hashMap.get(key));
		
	}
	private void evaluate(String input) {
		
		input = input.replaceAll(" ", "");
		int curState = State.START.ordinal();
		int lastIndex  = 0;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			int newState = nextState(c, curState);
			if(newState == State.START.ordinal())
			{
				String key= input.substring(lastIndex, i);
			    String val = State.values()[curState].toString();
			    hashMap.put(key,val);
				lastIndex = i;
				i--;
			}
			curState = newState;
		}
		
		 String key = input.substring(lastIndex, input.length());
		 String val = State.values()[curState].toString();
		 hashMap.put(key,val);
		//lastIndex = i;
			
		
	}
	private int nextState(char c, int curState) {
	   
		int type = charToType(c);
		return transition[curState][type].ordinal();
	}
	private int charToType(char c) {
		if(operators.contains(c))
			return TYPE.OPERATOR.ordinal();
		if(c >= '0' && c <= '9')
			return TYPE.INTLIT.ordinal();
		 if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
				return TYPE.ALPHA.ordinal();
		 return 0;
		
		
	}
	private void readfromFile() {
	
	        // database_new
	       
	        BufferedReader br = null;
	        FileReader fr = null;
	        try {
	            fr = new FileReader("test.txt");
	            br = new BufferedReader(fr);
	            String sCurrentLine;
	            while ((sCurrentLine = br.readLine()) != null) {
	               evaluate(sCurrentLine);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {

	            try {
	                if (br != null) {
	                    br.close();
	                }

	                if (fr != null) {
	                    fr.close();
	                }

	            } catch (Exception ex) {

	                ex.printStackTrace();

	            }

	      }
	}


}
