package com.ecommerce.application.eShopping.exceptions;



public abstract class BaseException extends Exception{
    //Each exception message will be held here
    private String message;
 
    public BaseException(String msg)
    {
        super(msg);
    	this.message = msg;
    }
    //Message can be retrieved using this accessor method
    public String getMessage() {
        return message;
    }
}