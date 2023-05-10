package net.bignumbers.math;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Number {
	
	private static final int DEFAULT_EXPONENT = 0;
	private static final int DEFAULT_BASE = 1000;
	
	private static final String[] FRIENDLY_EXPONENT_NAMES = { "", "K", "M", "B", "T" };
	private static final DecimalFormat FRIENDLY_VALUE_FORMAT = new DecimalFormat("0.#", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
	
	private double value;
	private int exponent;
	
	public Number(double value) {
		this(value, DEFAULT_EXPONENT);
		
		normalize();
	}
	
	public Number(double value, int exponent) {
		this.value = value;
		this.exponent = exponent;
	}
	
	@Override
	public String toString() {
		return exponent < DEFAULT_EXPONENT ? toScientificString() : toFriendlyString();
	}
	
	public String toFriendlyString() {
		String s1 = FRIENDLY_VALUE_FORMAT.format(value);
		String s2 = FRIENDLY_EXPONENT_NAMES[exponent];
		
		return String.format("%s%s", s1, s2);
	}
	
	public String toScientificString() {
		return String.format("%s * %s^%s", value, DEFAULT_BASE, exponent);
	}
	
	public Number divide(Number number) {
		number = number.invert();
		
		return multiply(number);
	}
	
	public Number multiply(Number number) {
		double v = value * number.getValue();
		int e = exponent + number.getExponent();
		
		Number n = new Number(v, e);
		
		n.normalize();
		
		return n;
	}
	
	public Number subtract(Number number) {
		number = number.negate();
		
		return add(number);
	}
	
	public Number add(Number number) {
		int e = number.getExponent();
		
		if(e < exponent) return number.add(this);
		e -= exponent;
		
		double v = number.getValue();
		for(int i = 0; i < e; i++) v *= DEFAULT_BASE;
		
		Number n = new Number(v + value, exponent);
		
		n.normalize();
		
		return n;
	}
	
	public Number invert() {
		Number n = new Number(1 / value, -exponent);
		
		n.normalize();
		
		return n;
	}
	
	public Number negate() {
		return new Number(-value, exponent);
	}
	
	private void normalize() {
		if(value == 0) {
			exponent = DEFAULT_EXPONENT;
			
			return;
		}
		
		while(Math.abs(value) >= DEFAULT_BASE) {
			
			value /= DEFAULT_BASE;
			exponent++;
		}
		
		while(Math.abs(value) < 1) {
			
			value *= DEFAULT_BASE;
			exponent--;
		}
	}
	
	public double getValue() {
		return value;
	}
	
	public int getExponent() {
		return exponent;
	}
	
	public static Number divide(Number number1, Number number2) {
		return number1.divide(number2);
	}
	
	public static Number multiply(Number number1, Number number2) {
		return number1.multiply(number2);
	}
	
	public static Number subtract(Number number1, Number number2) {
		return number1.subtract(number2);
	}
	
	public static Number add(Number number1, Number number2) {
		return number1.add(number2);
	}
	
	public static Number valueOf(Number number) {
		return number.invert();
	}
	
	public static Number negate(Number number) {
		return number.negate();
	}
	
	public static Number valueOf(double value) {
		return new Number(value);
	}
	
}
