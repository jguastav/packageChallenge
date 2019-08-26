package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

public class PackerTestValues {
	
	private static final String EOL = "\n";
	
	public static final String[] THINGS_VALUES = {
			"(1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)",
			"(1,15.3,€34)",
			"(1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)",
			"(1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)",
			""
			
	};
	
	
	public static final String[] INPUT_PACKAGE = {
			"81 : "+THINGS_VALUES[0],
			"8 : "+THINGS_VALUES[1],
			"75 : "+THINGS_VALUES[2],
			"56 : "+THINGS_VALUES[3],
			"20 : "+THINGS_VALUES[4]};
	
	public static final String[] RESULT_PACKAGE = {
			"4", 
			"-",
			"2,7",
			"8,9",
			"-"};
	
	
	public static final String FILE_RESULT_PACKAGE = 
			RESULT_PACKAGE[0]+EOL+
			RESULT_PACKAGE[1]+EOL+
			RESULT_PACKAGE[2]+EOL+
			RESULT_PACKAGE[3]+EOL;
	
	
	
	public static List<Thing> createThings(int index) {
		List<Thing> returnValue = new ArrayList<Thing>();
		switch (index) {
			case 0: {
				returnValue.add(new Thing(1,53.38f,45));
				returnValue.add(new Thing(2,88.62f,98));
				returnValue.add(new Thing(3,78.48f,3));
				returnValue.add(new Thing(4,72.30f,76));
				returnValue.add(new Thing(5,30.18f,9));
				returnValue.add(new Thing(6,46.34f,48));
				break;
			}
			case 1: {
				returnValue.add(new Thing(1,15.3f,34));
				break;
			}
			case 2: {
				returnValue.add(new Thing(1,85.31f,29));
				returnValue.add(new Thing(2,14.55f,74));
				returnValue.add(new Thing(3,3.98f,16));
				returnValue.add(new Thing(4,26.24f,55));
				returnValue.add(new Thing(5,63.69f,52));
				returnValue.add(new Thing(6,76.25f,75));
				returnValue.add(new Thing(7,60.02f,74));
				returnValue.add(new Thing(8,93.18f,35));
				returnValue.add(new Thing(9,89.95f,78));
				break;
			}
			case 3: {
				returnValue.add(new Thing(1,90.72f,13));
				returnValue.add(new Thing(2,33.80f,40));
				returnValue.add(new Thing(3,43.15f,10));
				returnValue.add(new Thing(4,37.97f,16));
				returnValue.add(new Thing(5,46.81f,36));
				returnValue.add(new Thing(6,48.77f,79));
				returnValue.add(new Thing(7,81.80f,45));
				returnValue.add(new Thing(8,19.36f,79));
				returnValue.add(new Thing(9,6.76f,64));
				break;
			}
			case 4: {
				break;
			}
		}
		
		return returnValue;
	} 
	
	public static Pack createPack(int index) {
		Pack returnValue=null;
		switch (index) {
			case 0: {
				returnValue = new Pack(81,createThings(index));
				break;
			}
			case 1: {
				returnValue = new Pack(8,createThings(index));
				break;
			}
			case 2: {
				returnValue = new Pack(75,createThings(index));
				break;
			}
			case 3: {
				returnValue = new Pack(56,createThings(index));
				break;
			}
			case 4: {
				returnValue = new Pack(20, createThings(index)); 
			}
		}
		return returnValue;
	}
	
	

}
