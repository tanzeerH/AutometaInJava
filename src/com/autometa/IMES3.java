package com.autometa;

public class IMES3 {
	public static void main(String[] args) {
		
		//for (int  i = 0; i < 100; i++)
			//imes3(i);
		imes3(23478);
		
	}
	private static void imes3(int x) {
		if(x < 0)
			return;
		while(x != 1) {
			if( x % 2 == 0)
				x /=2;
			else
				x = 3*x+1;
			System.out.println(x);
			
			if( (x & (x-1)) == 0)  // checking power of two
				{
				System.out.println("halt: "+ x);
				break;
				}
		}
		
		
	}
	
}
