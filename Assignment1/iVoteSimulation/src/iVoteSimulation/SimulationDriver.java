package iVoteSimulation;

import java.util.Random;

public class SimulationDriver {
	public static void main(String [] args){
		Random rand = new Random();
		
		//run 5 tests
		for(int i=5;i<10; i++){
			int numberOfStudents = rand.nextInt(15)+5; //5-20 students per test
			
			//generate question
			Question q = new MultipleChoiceQuestion(i, i-4);
			
			//create iVoteService
			IVoteService iVoteService = new IVoteService(q);
			
			//generate students and send responses to the iVoteService
			for(int j=0; j<numberOfStudents; j++){
				iVoteService.addStudentResponse(new Student(q, String.valueOf(j+1)));
			}
			//add duplicate response, iVote simulator should discard the first response
			iVoteService.addStudentResponse(new Student(q, String.valueOf(2)));

			//print results from iVote simulator
			iVoteService.printResults();
			
		}
		
	}
}
