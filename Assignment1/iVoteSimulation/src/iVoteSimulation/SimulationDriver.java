package iVoteSimulation;

import java.util.ArrayList;
import java.util.Random;

public class SimulationDriver {
	public static void main(String [] args){
		Random rand = new Random();
		
		//run 5 tests
		for(int i=5;i<10; i++){
			ArrayList<Student> students = new ArrayList<Student>();
			int numberOfStudents = rand.nextInt(15)+5; //5-20 students per test
			
			//generate question
			Question q = new MultipleChoiceQuestion(i, i-4);
			
			//generate students
			for(int j=0; j<numberOfStudents; j++){
				students.add(new Student(q, String.valueOf(j+1)));
			}
			
			//create iVote service
			IVoteService iVoteService = new IVoteService(q, students);
			
			//print results from iVote simulator
			iVoteService.printSelectionResults();
			iVoteService.printAnswerResults();
			
		}
		
	}
}
