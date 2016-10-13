package iVoteSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultipleChoiceQuestion implements Question {

	private List<Character> possibleAnswers;
	private List<Character> correctAnswers;
	private char[] alphabetArray = new char[26];
	
	public MultipleChoiceQuestion(){
		this(0);
	}
	
	public MultipleChoiceQuestion(int numberOfChoices){
		this(numberOfChoices, 0);
	}
	
	public MultipleChoiceQuestion(int numberOfChoices, int numberOfAnswers){
		possibleAnswers = new ArrayList<Character>(numberOfChoices);
		correctAnswers= new ArrayList<Character>(numberOfAnswers);
		for(int i=0; i<26; i++){
			alphabetArray[i] = (char) (i+65);
		}
		for(int i=0; i<numberOfChoices; i++){
			//System.out.print("Adding possible answer: " + alphabetArray[i] + "\n");
			possibleAnswers.add(alphabetArray[i]);
		}
		generateAnswers(numberOfAnswers);
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
		generateAnswers(0);
	}
	
	//generate the an answer set that is the specified number of answers
	public void generateAnswers(int numCorrectAnswers){
		Random rand = new Random(); 
		for(int i=0; i<numCorrectAnswers; i++){
			int indexOfPossibleAnswer = rand.nextInt(possibleAnswers.size());
			if(!correctAnswers.contains(possibleAnswers.get(indexOfPossibleAnswer))){
				correctAnswers.add(possibleAnswers.get(indexOfPossibleAnswer));
			}
			else{
				i--;
			}
		 }
	}

	@Override
	public int numAnswersAllowedToSelect() {
		return correctAnswers.size();
	}
	

}
