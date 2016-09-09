package com.example.criteria;

import java.util.ArrayList;
import java.util.List;

public class Criteria {
	private List<Operator> operators;
	
	public Criteria(){
		this.operators = new ArrayList<Operator>();
	}
	
	public Criteria setOperator(String key, Operand operand, String value){
		this.operators.add(new Operator(key, operand, value));
		return this;
	}
	
	public List<Operator> getListOperators(){
		return this.operators;
	}
}
