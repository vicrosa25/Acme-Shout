package forms;

import javax.validation.constraints.Pattern;

public class Calculator {
	
	private double x;
	private double y;
	private String operator;
	private double result;
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	@Pattern(regexp = "^[\\+\\-\\*\\/]$")
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	
	
	public void compute() {
		switch (this.getOperator()) {
		case "+":
			this.setResult(this.getX() + this.getY());
			break;
		case "-":
			this.setResult(this.getX() - this.getY());
			break;
		case "*":
			this.setResult(this.getX() * this.getY());
			break;
		case "/":
			this.setResult(this.getX() / this.getY());
			break;
		}
	}

	
	
}
