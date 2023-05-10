package net.bignumbers;

import net.bignumbers.math.Number;

public class BigNumbers {
	
	public static void main(String[] args) {
		Number n1 = Number.valueOf(25);
		Number n2 = Number.valueOf(100000);
		Number n3 = Number.multiply(n1, n2);
		Number n4 = Number.valueOf(-1000);
		Number n5 = Number.valueOf(0.001);
		Number n6 = Number.valueOf(1e300);
		Number n7 = Number.valueOf(1e200);
		Number n8 = Number.multiply(n6, n7);
		
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(n3);
		System.out.println(n4);
		System.out.println(n5);
		System.out.println(n6);
		System.out.println(n7);
		System.out.println(n8);
	}
	
}
