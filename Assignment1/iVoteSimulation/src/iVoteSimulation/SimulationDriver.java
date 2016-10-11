package iVoteSimulation;

import java.util.ArrayList;

public class SimulationDriver {
	public static void main(String [] args){
		int numberOfStudents = 10;
		ArrayList<Student> students = new ArrayList<Student>();
		
		//generate questions
		Question q1 = new MultipleChoiceQuestion(5, 1);
		Question q2 = new MultipleChoiceQuestion(5, 2);
		//Question q2 = new TrueFalseQuestion();
		
		//generate list of students
		for(int i=0; i<numberOfStudents; i++){
			students.add(new Student(q1, String.valueOf(i)));
		}
		
		//create, iVote service
		IVoteService iVoteService = new IVoteService(q1, students);
		
		//print results from iVote simulator
		iVoteService.printSelectionResults();
		iVoteService.printAnswerResults();
		
	}
}
