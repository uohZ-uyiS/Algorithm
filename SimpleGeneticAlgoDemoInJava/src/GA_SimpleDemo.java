import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class GA_SimpleDemo{
	Population population;
	int chromosomesNumber ;
	int genesNumber;
	Chromosome fittest;
	Chromosome secondFittest;
	int generation;
	String processInformation = "";
	
	public GA_SimpleDemo(int chromosomesNumber, int genesNumber) {
		this.chromosomesNumber = chromosomesNumber ;
		this.genesNumber = genesNumber;
		this.generation = 1;
		this.population = new Population(this.chromosomesNumber, this.genesNumber);
	}
	
	public String ToString() {
		String print = "The random generated chromosomes: " ;
		for(int i =0; i<this.chromosomesNumber; i++) {
			print += Arrays.toString(this.population.chromosomes[i].chromosome);
		}
		print += "\n";
		return print;
	}
	
	public String search() {
		Random rn = new Random();
		List<Integer> scores = this.population.chromosomesScore();
		int fittestIndex = this.population.bestFitIndex(scores);
		processInformation += "Generation: " + this.generation + " , The score of fittest chromosome: " + scores.get(fittestIndex) + 
				" , Chromosome: " + Arrays.toString(this.population.chromosomes[fittestIndex].chromosome) + "\n";
		while(scores.get(fittestIndex) < genesNumber && this.generation < 10) {
			this.generation++;
			processInformation += "Generation " + this.generation + " Calculation Process:"  + '\n';
			processInformation += this.selection();
			processInformation += this.crossover();
			if(rn.nextInt()%(2*genesNumber) < genesNumber) {
				processInformation += this.mutation();
			}
			processInformation += this.replaceByOffspring(scores) + '\n';
			scores = this.population.chromosomesScore();
			fittestIndex = this.population.bestFitIndex(scores);
			processInformation += "Generation " + this.generation+ " result: ";
			for(int i =0; i<this.chromosomesNumber; i++) {
				processInformation += Arrays.toString(this.population.chromosomes[i].chromosome);
			}
			processInformation +="\n";
		}
		processInformation += "Search Done";
		return processInformation;
	}
	
	public String selection() {
		String print = "Selection Process: " + "\n";
		List<Integer> scores = this.population.chromosomesScore();
		int index1 = this.population.bestFitIndex(scores);
		int index2 = this.population.secondBestFitIndex(scores, index1);
		this.fittest = new Chromosome(this.population.chromosomes[index1],this.genesNumber);
		this.secondFittest = new Chromosome(this.population.chromosomes[index2],this.genesNumber);
		print += "After selection, the Fittest chromosome : " + Arrays.toString(this.fittest.chromosome) +",";
		print += "After selection, the Second Fittest chromosome :" + Arrays.toString(this.secondFittest.chromosome) +"\n";
		return print;
	}
	
	public String crossover() {
		String print = "Crossover Process: " + "\n";
		Random rn = new Random();
		int point = rn.nextInt(this.population.chromosomes[0].chromosome.length);
		print += "The Cross point index is " + point + ",";
		for(int i = 0; i < point; i++){
			int temp = this.fittest.chromosome[i];
			this.fittest.chromosome[i] = this.secondFittest.chromosome[i];
			this.secondFittest.chromosome[i] = temp;
		}
		print += "After Crossover ,the Fittest chromosome : " + Arrays.toString(this.fittest.chromosome) + ",";
		print += "After Crossover ,the Second Fittest chromosome : " + Arrays.toString(this.secondFittest.chromosome) +"\n";
		return print;
	}
	
	public String mutation() {
		String print = "Mutation Process: " + "\n";
		Random rn = new Random();
		//mutation just modify the offspring chromosome
		int point = rn.nextInt(this.population.chromosomes[0].chromosome.length);
		print +=  "The mutation point for the Fittest chromosome in offspring index is " + point + "\n";
		modifyValue(point, this.fittest);
		point = rn.nextInt(this.population.chromosomes[0].chromosome.length);
		print +=  "The mutation point for the Second Fittest chromosome in offspring index is " + point + "\n";
		modifyValue(point, this.secondFittest);	
		print += "After mutation, the Fittest chromosome : " + Arrays.toString(this.fittest.chromosome);
		print += "After mutation, the Second Fittest chromosome : "  + Arrays.toString(this.secondFittest.chromosome) + "\n";
		return print;
	}
	
	//reverse value in chromosome(0->1, 1->0)
	public void modifyValue(int point, Chromosome chro) {
		chro.chromosome[point] ^= 1;
	}
	
	public String replaceByOffspring(List<Integer> scores) {
		String print = "Replace the parents chromosomes Process: " + "\n";
		int indexOfMin = this.population.minFitnessIndex(scores);
		Chromosome fittestOffspring;
		print += "The Least Fittest chromosome before selection phase: " + Arrays.toString(this.population.chromosomes[indexOfMin].chromosome);
		//select the fittest in the offspring
		if(this.fittest.fitnessScore() > this.secondFittest.fitnessScore()) {
			fittestOffspring= this.fittest;
		}else {
			fittestOffspring= this.secondFittest;
		}
		print += "The Fittest chromosome in the offspring " + Arrays.toString(fittestOffspring.chromosome);
		this.population.chromosomes[indexOfMin] = fittestOffspring;
		return print;
	}
	
}
