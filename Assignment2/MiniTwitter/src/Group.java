/*
 * Daniel Avetyan
 * CS 356 Assignment 2
 * Due November 8, 2016
 */

import java.util.ArrayList;

/**
 * Group Interface.
 * Allows for visitor and component pattern.
 */
public interface Group extends UserGroupComponent{
	
	public ArrayList<User> getUsers();
	public ArrayList<Group> getGroups();
	public void add(UserGroupComponent component);
	public String getName();
	public ArrayList<Integer> accept(ComponentElementVisitor visitor);
}
