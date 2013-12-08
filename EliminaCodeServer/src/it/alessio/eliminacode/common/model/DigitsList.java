package it.alessio.eliminacode.common.model;

import java.util.LinkedList;
import java.util.List;

public class DigitsList {
	public LinkedList<Integer> digits;

	public DigitsList() {
		this.digits = new LinkedList<Integer>();
	}

	/**
	 * Add a digit as long as the number has two digits max
	 * */
	public void addDigit(Integer digit) {
		if (this.digits.size() < 2) {
			this.digits.add(digit);
		}
	}

	public void clearList() {
		this.digits.clear();
	}

	public void removeLastDigit() {
		if (this.digits != null && this.digits.size() > 0) {
			this.digits.removeLast();
		}
	}
	
	public String toString(){
		String value = "";
		for(Integer digit : this.digits){
			value = value + digit;
		}
		return value;
	}

}
