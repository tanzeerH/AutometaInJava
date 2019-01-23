package com.autometa;

import java.io.BufferedReader;
import java.io.FileReader;

import com.autometa.DFA.State;

/**
 * A simple autometa implementation of checking integers with power of 4
 * @author tanzeer
 *
 */
public class SimpleAutometa {
	public static enum State{
		START,ACCEPT, INTERMEDIATE, REJECT
	}
	// 0 means 0 and 1 means 1 as inputt
	static State transition [][] = new State[][] {
		{State.REJECT, State.ACCEPT},
		{State.INTERMEDIATE, State.REJECT},
		{State.ACCEPT, State.REJECT},
		{State.REJECT, State.REJECT}
		
	};
	public static void main(String[] args) {
		
		SimpleAutometa autometa = new SimpleAutometa();
		autometa.readfromFile();
	}
	private void evaluate(String input) {
		
		input = input.replaceAll(" ", "");
		int curState = State.START.ordinal();
		
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			curState= nextState(c, curState);
			}
		if(curState == State.ACCEPT.ordinal())
			System.out.println(input + "  " + "Accpet");
		else
			System.out.println(input + "  " + "Reject");
		
		
	}
	private int nextState(char c, int curState) {
	   
		int type = 0;
		if(c == '1')
			type = 1;
	
		return transition[curState][type].ordinal();
	}
	private void readfromFile() {
		
        // database_new
       
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader("pow4.txt");
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
