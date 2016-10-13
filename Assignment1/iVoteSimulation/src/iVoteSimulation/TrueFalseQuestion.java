package iVoteSimulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TrueFalseQuestion implements Question {
	
	private List<Character> possibleAnswers;
	private List<Character> correctAnswers;
	
	public TrueFalseQuestion(){
		possibleAnswers = new ArrayList<Character>(Arrays.asList('A', 'B'));
		generateAnswers();
	}

	@Override
	public ArrayList<Character> getPossibleAnswers() {
		return (ArrayList<Character>) possibleAnswers;
	}

	@Override
	public ArrayList<Character> getCorrectAnswers() {
		return (ArrayList<Character>) correctAnswers;
	}

	@Override
	public void generateAnswers() {
		Random rand = new Random(possibleAnswers.size());
		correctAnswers.add(possibleAnswers.get(rand.nextInt()));
	}

	@Override
	public int numAnswersAllowedToSelect() {
		return 1;
	}

}
