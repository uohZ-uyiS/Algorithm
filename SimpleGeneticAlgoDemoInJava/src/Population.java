import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Population{
	Chromosome[] chromosomes;
	public Population(int chromosomesNumber, int genesNumber) {
		chromosomes = new Chromosome[chromosomesNumber];
		for(int i = 0; i < chromosomes.length; i++) {
			chromosomes[i] = new Chromosome(genesNumber);
		}
	}

	
	//put fitSocre of all chromosomes into a list
	public List<Integer> chromosomesScore() {
		List<Integer> scores = new ArrayList<Integer>();
		List<Chromosome> chromosomesList = Arrays.asList(chromosomes);
		chromosomesList.forEach(element -> scores.add(element.fitnessScore()));
		return scores;
	}
	
	//select the index of the best fit chromosome from the scores list 
	public int bestFitIndex(List<Integer> scores) {
		int max = Collections.max(scores);
		int index = scores.indexOf(max);
		return index;
	}
	
	
	public int secondBestFitIndex(List<Integer> scores, int bestindex) {
		List<Integer> scoresTemp = scores;
		scoresTemp.remove(bestindex);
		int index = bestFitIndex(scoresTemp);
		if(index < bestindex) {
			return index;
		}else {
			return index + 1;
		}
	}
	
	public int minFitnessIndex(List<Integer> scores) {
		int min = Collections.min(scores);
		int index = scores.indexOf(min);
		return index;
	}
}
