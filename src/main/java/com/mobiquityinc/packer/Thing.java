package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ErrorCode;

/**
 * Class to represents the things that can be sent in the packages
 * @author yo
 *
 */
public class Thing {
	
	public static final float MAX_ALLOWED_WEIGHT = 100;
	public static final float MAX_ALLOWED_COST = 100;
	

	private int index;
	private float weight;
	private float cost;
	
	
	/**
	 * Main constructor
	 * @param index
	 * @param weight
	 * @param cost
	 */
	public Thing(int index, float weight, float cost) {
		this.index = index;
		this.weight = weight;
		this.cost = cost;
	}
	
	
	private static Thing createThing(String[] properties) throws APIException {
		Thing thing= null;
		if (properties.length == 3) {
			int index = Integer.parseInt(properties[0]);
			float weight = Float.parseFloat(properties[1]);
			float cost = Float.parseFloat(properties[2].substring(1));
			
			if (weight > MAX_ALLOWED_WEIGHT) {
				throw new APIException(properties.toString(),ErrorCode.NOT_ALLOWED_THING_WEIGHT);
			}
			if (cost > MAX_ALLOWED_COST) {
				throw new APIException(properties.toString(),ErrorCode.NOT_ALLOWED_THING_COST);
			}
			
			thing = 
					new Thing(Integer.parseInt(properties[0]),
							Float.parseFloat(properties[1]),
							Float.parseFloat(properties[2].substring(1))); 
		} else {
			throw new APIException(properties.toString(),ErrorCode.MAL_FORMED_THING);
		}
		return thing;
		
	}
	
	
	/**
	 * Convert a source string representing things to Java Object List<Thing>
	 * @param source
	 * 	The source string 
	 * 		(1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)
	 * @param limit
	 * 		max number of items allowed. If the string represent more valid things it will throw an APIException
	 * @return
	 * 		the java representation of List<Thing>
	 * 
	 * @throws com.mobiquityinc.exception.APIException
	 * 		throws exception on any malformed string or invalid values or exceeds the number of items
	 * 
	 * 	
	 */
	public static List<Thing> convertList(String source, int limit) throws com.mobiquityinc.exception.APIException  {
		List<Thing> result = new ArrayList<Thing>();
		Set<Integer> addedThings = new HashSet<Integer>();
		String[] thingsStringsArray = source.trim().split("\\(");
		// 1st element is ""
		if (thingsStringsArray.length<= limit+1) {
			for (int i=1;i<thingsStringsArray.length;i++) {
				String thingString = thingsStringsArray[i].trim();  
				if (thingString.endsWith(")")) {
					thingString = thingString.substring(0,thingString.length()-1);
					String[] thingElements = thingString.split(",");
					Thing thingToAdd = createThing(thingElements);
					if (addedThings.contains(thingToAdd.getIndex())) {
						throw new APIException(thingString,ErrorCode.REPEATED_THING_INDEX);
					} else {
						addedThings.add(thingToAdd.getIndex());
					}
					result.add(thingToAdd);
				} else {
					throw new APIException(thingString,ErrorCode.MAL_FORMED_THING);
				}
			}
		} else {
			throw new APIException(source,ErrorCode.TOO_MANY_ITEMS);
		}
		return result;
	}

	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(cost);
		result = prime * result + index;
		result = prime * result + Float.floatToIntBits(weight);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thing other = (Thing) obj;
		if (Float.floatToIntBits(cost) != Float.floatToIntBits(other.cost))
			return false;
		if (index != other.index)
			return false;
		if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
			return false;
		return true;
	}



	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	public float getWeight() {
		return weight;
	}



	public void setWeight(float weight) {
		this.weight = weight;
	}



	public float getCost() {
		return cost;
	}



	public void setCost(float cost) {
		this.cost = cost;
	}
	
	
	
	

}
