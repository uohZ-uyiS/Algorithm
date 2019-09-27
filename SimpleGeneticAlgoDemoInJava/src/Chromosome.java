
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Chromosome {
	int[] chromosome;
	int fitScore = 0;
	
	public Chromosome(int genesNumber) {
		Random rn =new Random();
		this.chromosome = new int[genesNumber];
		for(int i = 0; i< genesNumber; i++) {
			chromosome[i] = Math.abs(rn.nextInt() %2 );
		}
		this.fitScore = 0;
	}
	//copy constructor
	public Chromosome(Chromosome chro,int genesNumber ) {
		chromosome = new int[genesNumber];
		for(int i=0; i<genesNumber; i++) {
			chromosome[i] = chro.chromosome[i];
		}
	}
	
	public int fitnessScore() {
		fitScore = IntStream.of(chromosome).sum();
		return fitScore;
	}
	

}
