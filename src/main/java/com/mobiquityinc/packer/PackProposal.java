package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

public class PackProposal {

	float totalWeight;
	float totalCost;
	List<Thing> things;
	

	/**
	 * Create a new PackProposal with thing as first element and the rest of the elements from the given packProposal 
	 * @param thing
	 * @param packProposal
	 */
	public PackProposal(Thing thing, PackProposal packProposal) {
		super();
		this.totalWeight = packProposal.getTotalWeight()+thing.getWeight();
		this.totalCost = packProposal.getTotalCost()+thing.getCost();
		this.things = new ArrayList<Thing>();
		this.things.add(thing);
		this.things.addAll(packProposal.getThings());
	}
	
	
	public PackProposal() {
		this.totalWeight=0;
		this.totalCost=0;
		this.things=new ArrayList<Thing>();
	} 
	
	
	public float getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(float totalWeight) {
		this.totalWeight = totalWeight;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public List<Thing> getThings() {
		return things;
	}
	public void setThings(List<Thing> things) {
		this.things = things;
	}
	
	public String toSummarizedString() {
		String result = "";
		if (this.getThings().size()>0) {
			result = Integer.toString(this.getThings().get(0).getIndex());
			for (int i=1;i<this.getThings().size();i++) {
				result+=","+Integer.toString(this.getThings().get(i).getIndex());
			}
		}
		return result;
	}
	
	
}
