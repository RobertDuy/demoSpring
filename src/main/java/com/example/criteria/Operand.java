package com.example.criteria;

public enum Operand {
	LESS_THAN("<="), 
	LESS("<"), 
	EQUAL("="), 
	NOT_EQUAL("<>"),
	GEATER_THAN(">="),
	GEATER(">"),
	LIKE("%");
	
	private String operand;

	Operand(String operand) {
		this.operand = operand;
	}

	public String getValue() {
		return operand;
	}
	
	public static Operand fromValue(String value){
        try {
             for (int i = 0; i < Operand.values().length; i++){
            	if (value.equals(Operand.values()[i].getValue())){
            		return Operand.values()[i];
            	}
             }
        } catch(Exception e) {
             System.out.println(e.getMessage());
        }
        return null;
    }
}
