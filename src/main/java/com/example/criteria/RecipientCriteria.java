package com.example.criteria;

public class RecipientCriteria extends Criteria{
	
	private Operator email;
	private Operator lastName;
	private Operator firstName;
	
	public RecipientCriteria(){
	}
	
	public Operator getEmail() {
		return email;
	}

	public Operator getLastName() {
		return lastName;
	}

	public Operator getFirstName() {
		return firstName;
	}
}
