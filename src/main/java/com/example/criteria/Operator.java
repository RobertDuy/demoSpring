package com.example.criteria;

public class Operator {
	private String key;
	private Operand operand;
	private Object value;
	
	public Operator(String key, Operand operand, Object value){
		this.key = key;
		this.operand = operand;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Operand getOperand() {
		return operand;
	}

	public void setOperand(Operand operand) {
		this.operand = operand;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

		
	
}
