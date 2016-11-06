/*
 * Daniel Avetyan
 * CS 356 Assignment 2
 * Due November 8, 2016
 */

import java.util.ArrayList;

/**
 * User Interface
 * To be used with TwitterUser
 * extends {@link UserGroupComponent}
 */
public interface User extends UserGroupComponent{
	public void update(Tweet tweet);
	public String getName();
	public ArrayList<Integer> accept(ComponentElementVisitor visitor);
}
