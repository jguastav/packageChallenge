# Mobiquity Package Challenge 









##Strategy

The first approach I thought was to verify all possible combinations.
Then, to choose the best one (highest cost / lowest weight)

This strategy could be great for a distributed environment. 

But, this strategy would have a high cost either when ** calculating many unfeasible combinations** or by calculating the weight and cost of all combinations. 

A better approach would be to find a mechanism by which **many options are discarded** as soon as the information is available and without an additional cost to do so. 

Even better would be to** avoid calculating non-viable combinations.**

A strategy that meets these expectations is to create a** recursive method**. It could also express the solution like natural reasoning.

The reasoning basis for the recursive method is as follows.

1. Sort "things" in sequence (they are already sorted). But since the alternatives for each thing is to include it or not to include it in the package, then changing the order of things does not provide variants to the solution.

	At this moment I have an ordered list of "things", and a maximum weight that I can add to the package.
	In the beginning, the maximum weight is the one specified by the initial file 

2. For each of the "things" on the initial list

3. Test if the chosen thing has a weight less than or equal to the capacity.  (can be added to the package)

	If the weight is greater than the capacity
	I discard it. 

	If the weight is less than or equal to the capacity
	
	I try to get all the alternatives with the rest of the things, limiting them to the resulting weight of the current capacity subtracting the weight of the "thing" I've already chosen. 
	
    4.- Once the list of viable things is obtained
	
	The lists of possible things will be: all those that contain the selected thing + The list obtained
	
    5.- From this set of possible lists it is possible to choose which are the best ones (highest cost, lowest weight)

To improve even more this algorithm, I've added a "cost" paramater as a boundary for discarding early some of those alternatives are not the "best"



##Algorithm

Algorithm

The solution described is mainly implemented in the Packer class. 

- Method: String packOnePackage (String) class

	Given an "input line" indicating capacity and list of things, it returns the resulting line containing the list of things to be packaged.


- The recursive Method: List <PackProposal> calculateBetterPackages (
		float maxWeight, 
		float minCost,
		List <Thing> things)

	- maxWeight:
			-To determine 
				the maximum weight that can enter the box
				Compare the winner combination in case there is more than one with the same cost
	- minCost:
			To compare and choose the winning combination

	- List <Things>: the list of things

	- The result List <PackProposal>
	It is the list of solutions.
		I chose PackProposal to facilitate understanding and make explicit values. The only element used for the solution are the indexes of the things that make up the solution.

If there is more than one solution with the same values, only one is returned at the end. 

##Data structure and Design


The Data Structure is simply expressed primarily in the properties of the classes: 
- Pack
- Thing 

and the result in the PackProposal class




	
