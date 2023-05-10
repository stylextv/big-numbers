package net.cash;

import net.cash.math.Number;

public class Cash {
	
	public static void main(String[] args) {
		Number n1 = Number.valueOf(25);
		Number n2 = Number.valueOf(100000);
		Number n3 = Number.multiply(n1, n2);
		Number n4 = Number.valueOf(-1000);
		Number n5 = Number.valueOf(0.001);
		
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(n3);
		System.out.println(n4);
		System.out.println(n5);
	}
	
}
