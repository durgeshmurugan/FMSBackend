package com.fms.bean;

public class EMICalculator {

	public static double calculateEMI(double price, int months) {
		return price/months;
	}

}
