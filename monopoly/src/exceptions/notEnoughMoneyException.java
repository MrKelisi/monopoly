package exceptions;

import java.lang.Exception;

public class notEnoughMoneyException extends Exception{

	public notEnoughMoneyException(){
		super();
	}
	
	public notEnoughMoneyException(String s){
		super(s);
	}
}
