/*
 * Daniel Avetyan
 * CS 356 Assignment 1
 */

package iVoteSimulation;

import java.util.ArrayList;

public interface Question {
	public ArrayList<Character> getPossibleAnswers();
	public ArrayList<Character> getCorrectAnswers();
	public void generateAnswers();
	public int numAnswersAllowedToSelect();
}
