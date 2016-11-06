/*
 * Daniel Avetyan
 * CS 356 Assignment 1
 */

package iVoteSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student {
	private String studentID;
	private Question q;
	private List<Character> answers;
	
	public Student(Question q, String id){
		this.q = q;
		studentID = id;
		answers = new ArrayList<Character>();
		generateAnswers();
	}

	
	private void generateAnswers() {
		Random rand = new Random();
		
		int numberOfAnswers = 0;
		//for random amount of answers uncomment next line, and comment out the following line.
		//numberOfAnswers = rand.nextInt(q.getPossibleAnswers().size())+1;
		numberOfAnswers = q.numAnswersAllowedToSelect();
		
		//ensures correct answer set size
		while(answers.size()<numberOfAnswers){
			int answerIndexSelected = rand.nextInt(q.getPossibleAnswers().size());
			//forces a proper set of answers (no repeated answers)
			if(!answers.contains(q.getPossibleAnswers().get(answerIndexSelected))){
				answers.add(q.getPossibleAnswers().get(answerIndexSelected));
			}
		}
	}
	
	public ArrayList<Character> getAnswers(){
		return (ArrayList<Character>) answers;
	}
	
	public String getID(){
		return studentID;
	}
	
	public String toString(){
		return studentID + " " + answers.toString();
	}
}
