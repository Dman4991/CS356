/*
 * Daniel Avetyan
 * CS 356 Assignment 1
 */

package iVoteSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultipleChoiceQuestion implements Question {

	private List<Character> possibleAnswers;
	private List<Character> correctAnswers;
	private char[] alphabetArray = new char[26];
	
	//creates a list of possible answers
	public MultipleChoiceQuestion(int numberOfChoices, int numberOfAnswers){
		if(numberOfAnswers>numberOfChoices){
			//default to equaling
			numberOfAnswers=numberOfChoices;
		}
		//limit number of choices to single letter alphabet A-Z
		if(numberOfChoices>26){
			numberOfChoices=26;
		}
		if(numberOfAnswers>26){
			numberOfAnswers=26;
		}
		possibleAnswers = new ArrayList<Character>(numberOfChoices);
		correctAnswers= new ArrayList<Character>(numberOfAnswers);
		
		//create alphabet array, I think Java already has this
		for(int i=0; i<26; i++){
			alphabetArray[i] = (char) (i+65);
		}
		
		//add all possible answers to numberOfChoices
		for(int i=0; i<numberOfChoices; i++){
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
	private void generateAnswers(int numCorrectAnswers){
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
