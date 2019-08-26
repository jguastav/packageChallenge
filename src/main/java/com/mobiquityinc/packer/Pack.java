package com.mobiquityinc.packer;

import java.util.List;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ErrorCode;

public class Pack {
	
	public static final int MAX_NUMBER_OF_ITEMS = 15;
	public static final float MAX_PACK_WEIGHT = 100;
	
	
	private float maxWeight;
	private List<Thing> things;

	
	public Pack(float maxWeight, List<Thing> things) {
		this.maxWeight = maxWeight;
		this.things = things;
	}


	/**
	 * 
	 * Each line contains the weight that the package can take (before the colon) and the list of things you
	need to choose. Each thing is enclosed in parentheses where the 1st number is a thing's index number,
	the 2nd is its weight and the 3rd is its cost
	 * 
	 * @param stringPackage
	 * 		The string line representing the package
	 * 		Sample:
	 * 				81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)
	 * 
	 * @return
	 * 		the Java Pack object
	 * @throws com.mobiquityinc.exception.APIException
	 * 		throws exception on any malformed string or invalid values
	 * 
	 */
		
		public static Pack convert(String stringPackage)  throws com.mobiquityinc.exception.APIException  {
			Pack returnValue = null;
			if (stringPackage !=null) {
				String [] parsedString = stringPackage.split(":");
				if (parsedString.length==2) {
					List<Thing> things = Thing.convertList(parsedString[1], MAX_NUMBER_OF_ITEMS);
					float maxPackageWeight = Float.parseFloat(parsedString[0]);
					if (maxPackageWeight<= MAX_PACK_WEIGHT) {
						returnValue = new Pack(maxPackageWeight,things);
					} else {
						throw new APIException(stringPackage, ErrorCode.NOT_ALLOWED_PACKAGE_WEIGHT);
					}
				} else {
					throw new APIException(stringPackage, ErrorCode.MAL_FORMED_INPUT_STRING);
				} 
			}
			return returnValue;
		}

		
		
		

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(maxWeight);
		result = prime * result + ((things == null) ? 0 : things.hashCode());
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
		Pack other = (Pack) obj;
		if (Float.floatToIntBits(maxWeight) != Float.floatToIntBits(other.maxWeight))
			return false;
		if (things == null) {
			if (other.things != null)
				return false;
		} else if (!things.equals(other.things))
			return false;
		return true;
	}


	public float getMaxWeight() {
		return maxWeight;
	}


	public void setMaxWeight(float maxWeight) {
		this.maxWeight = maxWeight;
	}


	public List<Thing> getThings() {
		return things;
	}


	public void setThings(List<Thing> things) {
		this.things = things;
	}
		
		
		

	
	
}
