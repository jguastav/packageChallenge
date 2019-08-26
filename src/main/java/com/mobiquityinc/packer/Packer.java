package com.mobiquityinc.packer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ErrorCode;

public class Packer {

	
	
	/**
	 * 
	 * @param packageDescription
	 * 		line with description for one package
	 * 		the line does not finish with EOL nor CR
	 * @return
	 * 		solution for the package
	 * 		if there is no solution returns - 		
	 * @throws com.mobiquityinc.exception.APIException
	 */
	public static String packOnePackage(String packageDescription) throws com.mobiquityinc.exception.APIException {
		Pack originalPack = Pack.convert(packageDescription);
		List<PackProposal> packageProposals = calculateBetterPackages(originalPack.getMaxWeight(),0,originalPack.getThings());
		String result ="-";
		if (packageProposals.size()>0) {
			result =packageProposals.get(0).toSummarizedString(); 
		}
		return result;
	}
	

	
	/**
	 * method to be called recursively
	 * The strategy is 
	 * 1.- To choose the first thing that can be included in the package
	 * 				thing.getWeight <= maxWeight
	 * 
	 * 2.- To choose in the rest of the list , a set of things which weight less than the allowed 
	 * 		substracting the thing already chosen 
	 * 
	 * 
	 * After getting the first element and the rest of items, it is selected the best set
	 * 			minimun Weight and maximum Cost
	 * 
	 * 
	 * 
	 * @param maxWeight
	 * @param minCost
	 * 			
	 * @param things
	 * 			The list of things that could be chosen
	 * @return
	 */
	
	private static List<PackProposal> calculateBetterPackages(float maxWeight, float minCost,List<Thing> things) {

		List<PackProposal> result = new ArrayList<PackProposal>();
		float bestCost = minCost;
		float bestWeight = -1;
		boolean firstTime = true;
		for (int i=0;i<things.size();i++) {
			Thing thing = things.get(i);
			if (thing.getWeight()<=maxWeight) {
				// this item can be sent
				List<PackProposal> packagesWithRestOfThings=
						calculateBetterPackages(
								maxWeight-thing.getWeight(),
								bestCost-thing.getCost(),
								things.stream().skip(i+1).collect(Collectors.toList()));
				// add rest empty list in case solution has unique solution with current thing
				packagesWithRestOfThings.add(new PackProposal());
				// add as solutions all lists formed by first element = thing and next elements packagesWithRestOfThings
				for (PackProposal packageProposal:packagesWithRestOfThings) {
					PackProposal packageProposalToAdd  = new PackProposal(thing,packageProposal);
					if (packageProposalToAdd.getTotalCost()>bestCost) {
						bestCost = packageProposalToAdd.getTotalCost();
						bestWeight = packageProposalToAdd.getTotalWeight();
						firstTime = false;
						// replace record
						result.clear();
						result.add(packageProposalToAdd);
						
					} else if (packageProposalToAdd.getTotalCost() ==bestCost && (firstTime || packageProposalToAdd.getTotalWeight() < bestWeight)) {
						bestWeight = packageProposalToAdd.getTotalWeight();
						// replace record
						result.clear();
						result.add(packageProposalToAdd);
					} else if (packageProposalToAdd.getTotalCost() ==bestCost && !firstTime && packageProposalToAdd.getTotalWeight() ==bestWeight) {
						// add record
						result.add(packageProposalToAdd);
					}
				}
				// add proposal with unique valid item
				
				
			}
		}
		return result;
	}


	/**
	 * 
	 * @param absoluteFileName
	 * 		absolute path to the input fileName
	 * @return
	 * 		
	 * @throws com.mobiquityinc.exception.APIException
	 */
	public static String pack(String absoluteFileName) throws APIException {
		String result="";  
		try {
			BufferedReader bufferedReader = new BufferedReader(
					   new InputStreamReader(
			                      new FileInputStream(absoluteFileName), "UTF8"));
			  String stringRead; 
			  while ((stringRead = bufferedReader.readLine()) != null) {
				  result += packOnePackage(stringRead)+"\n";
			  };
			  bufferedReader.close();
		} catch (FileNotFoundException e) {
			throw new APIException(absoluteFileName,e,ErrorCode.FILE_NOT_FOUND); 
		} catch (IOException e) {
			throw new APIException(absoluteFileName,e,ErrorCode.IO_ERROR); 
		} 
		  
		return result;
	}

}
